package net.minimgun.quizframework.play;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.minimgun.quizframework.enums.AnswerOptionType;
import net.minimgun.quizframework.enums.GameState;
import net.minimgun.quizframework.enums.QuestionState;
import net.minimgun.quizframework.exceptions.NoClientResponseFoundException;
import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.ClientResponse;
import net.minimgun.quizframework.models.play.Session;
import net.minimgun.quizframework.models.play.SessionState;
import net.minimgun.quizframework.models.play.events.ClientDisconnectEvent;
import net.minimgun.quizframework.models.play.events.ClientResponseEvent;
import net.minimgun.quizframework.models.play.events.SessionStateChangeEvent;
import net.minimgun.quizframework.models.play.events.TimerEvent;
import net.minimgun.quizframework.models.quiz.entities.QuestionImpl;

@Service
public class PlayService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void next(String sessionId, Principal principal) {
        Session session = sessionService.getSession(sessionId);
        if (principalIsGameMaster(session, principal)) {
            GameState gameState = session.getCurrentState().getGameState();
            if (gameState == GameState.LOBBY) {
                sortQuestions(session);
                getNextQuestion(session);
                clearClients(session);
                startTimer(session);
            } else {
                QuestionState questionState = session.getCurrentState().getCurrentQuestionState();
                switch (questionState) {
                case SHOWING_RESPONSES:
                    evaluate(session);
                    break;
                case RESULTS:
                    nextQuestion(session);
                    break;
                default:
                    break;
                }
            }
        }
    }

    public void respond(String sessionId, ClientResponse response, Principal principal) {
        Session session = sessionService.getSession(sessionId);
        SessionState currentState = session.getCurrentState();
        Client client = session.getClientByPrincipal(principal);
        try {
            ClientResponse currentResponse = currentState.getResponseByClient(client);
            currentResponse.setTextResponse(response.getTextResponse());
            currentResponse.setNumberResponse(response.getNumberResponse());
        } catch (NoClientResponseFoundException e) {
            response.setClient(client);
            currentState.getClientResponses().add(response);
        }
        eventPublisher.publishEvent(new ClientResponseEvent(this, session));
    }

    public void buzz(String sessionId, Principal principal) {
        Session session = sessionService.getSession(sessionId);
        Client client = session.getClientByPrincipal(principal);
        SessionState currentState = session.getCurrentState();
        if (currentState.getBuzzerClient() == null) {
            currentState.setBuzzerClient(client);
            eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
        }
    }

    public void correctBuzzerAnswer(String sessionId, Principal principal) {
        Session session = sessionService.getSession(sessionId);
        if (principalIsGameMaster(session, principal)) {
            SessionState currentState = session.getCurrentState();
            currentState.setCurrentQuestionState(QuestionState.RESULTS);
            Client client = session.getClientByNickname(currentState.getBuzzerClient().getNickname());
            client.setPoints(client.getPoints() + session.getQuiz().getQuizSettings().getPointsToAdd());
            currentState.setBuzzerClient(null);
            eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
        }
    }

    public void wrongBuzzerAnswer(String sessionId, Principal principal) {
        Session session = sessionService.getSession(sessionId);
        if (principalIsGameMaster(session, principal)) {
            SessionState currentState = session.getCurrentState();
            currentState.setCurrentQuestionState(QuestionState.RESULTS);
            Client client = session.getClientByNickname(currentState.getBuzzerClient().getNickname());
            if (session.getQuiz().getQuizSettings().isRemovePointsForInvalidAnswers()) {
                client.setPoints(client.getPoints() - session.getQuiz().getQuizSettings().getPointsToRemove());
            }
            currentState.setBuzzerClient(null);
            eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
        }
    }

    private void nextQuestion(Session session) {
        SessionState currentState = session.getCurrentState();
        if ((currentState.getCurrentQuestionIndex() + 1) < session.getQuiz().getQuestions().size()) {
            getNextQuestion(session);
            currentState.setCurrentQuestionState(QuestionState.ANSWERING);
            startTimer(session);
        } else {
            currentState.setGameState(GameState.RESULT);
        }
        eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
    }

    private void evaluate(Session session) {
        SessionState currentState = session.getCurrentState();
        if (currentState.getCurrentQuestion().getAnswerOption().getAnswerOptionType() == AnswerOptionType.OPTION) {
            for (ClientResponse response : currentState.getClientResponses()) {
                if (response.getTextResponse()
                    .equals(currentState.getCurrentQuestion()
                        .getAnswerOption()
                        .getOptionAnswerOption()
                        .getCorrectValue())) {
                    Client client = session.getClientByNickname(response.getClient().getNickname());
                    client.setPoints(client.getPoints() + session.getQuiz().getQuizSettings().getPointsToAdd());
                }
            }
        } else if (currentState.getCurrentQuestion()
            .getAnswerOption()
            .getAnswerOptionType() == AnswerOptionType.RANGE) {
            ClientResponse winner = currentState.getClientResponses().get(0);
            double delta = Math.abs(winner.getNumberResponse()
                    - currentState.getCurrentQuestion().getAnswerOption().getRangeAnswerOption().getCorrectValue());
            for (ClientResponse response : currentState.getClientResponses()) {
                double distance = Math.abs(response.getNumberResponse()
                        - currentState.getCurrentQuestion().getAnswerOption().getRangeAnswerOption().getCorrectValue());
                if (distance < delta) {
                    winner = response;
                }
            }
            Client client = session.getClientByNickname(winner.getClient().getNickname());
            client.setPoints(client.getPoints() + session.getQuiz().getQuizSettings().getPointsToAdd());
        }
        currentState.setCurrentQuestionState(QuestionState.RESULTS);
        eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
    }

    private void startSession(Session session) {
        eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
        startTimer(session);
    }

    private boolean principalIsGameMaster(Session session, Principal principal) {
        return session.getClients()
            .stream()
            .filter(e -> e.isGameMaster())
            .collect(Collectors.toList())
            .get(0)
            .getSocketUsername()
            .equals(principal.getName());
    }

    @Async
    private void startTimer(Session session) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                SessionState currentState = session.getCurrentState();
                int seconds = currentState.getGameState() == GameState.LOBBY ? 3
                        : currentState.getCurrentQuestion().getTime();
                for (int i = 0; i < seconds; i++) {
                    if (currentState.getCurrentQuestionState() != QuestionState.ANSWERING) {
                        return;
                    }
                    if (currentState.getBuzzerClient() != null) {
                        i--;
                    }
                    int remaining = seconds - i;
                    currentState.getTimer().setTime(remaining);
                    eventPublisher.publishEvent(new TimerEvent(this, session));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentState.getTimer().setTime(0);
                if (currentState.getGameState() == GameState.LOBBY) {
                    currentState.setCurrentQuestionState(QuestionState.ANSWERING);
                    currentState.setGameState(GameState.IN_GAME);
                    startSession(session);
                } else {
                    currentState.setCurrentQuestionState(QuestionState.SHOWING_RESPONSES);
                    eventPublisher.publishEvent(new SessionStateChangeEvent(this, session));
                }
            }
        }).start();
    }

    private void getNextQuestion(Session session) {
        SessionState currentState = session.getCurrentState();
        currentState.setCurrentQuestionIndex(currentState.getCurrentQuestionIndex() + 1);
        currentState.setCurrentQuestion(session.getQuiz().getQuestions().get(currentState.getCurrentQuestionIndex()));
    }

    private void sortQuestions(Session session) {
        Collections.sort(session.getQuiz().getQuestions(), Comparator.comparing(QuestionImpl::getQuestionIndex));
        System.out.println(session.getQuiz().getQuestions());
    }

    @Async
    private void clearClients(Session session) {
        List<Client> onlineClients = session.getClients()
            .stream()
            .filter(e -> e.isConnected())
            .collect(Collectors.toList());
        session.setClients(onlineClients);
        eventPublisher.publishEvent(new ClientDisconnectEvent(this, null, session));
    }
}
