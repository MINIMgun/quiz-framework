package net.minimgun.quizframework.models.play;

import java.util.ArrayList;
import java.util.List;

import net.minimgun.quizframework.enums.GameState;
import net.minimgun.quizframework.enums.QuestionState;
import net.minimgun.quizframework.exceptions.NoClientResponseFoundException;
import net.minimgun.quizframework.models.quiz.entities.QuestionImpl;

public class SessionState {

    private GameState gameState = GameState.LOBBY;
    private QuestionImpl currentQuestion;
    private QuestionState currentQuestionState = QuestionState.ANSWERING;
    private Timer timer = new Timer();
    private List<ClientResponse> clientResponses = new ArrayList<>();
    private int currentQuestionIndex = -1;
    private Client buzzerClient;

    public SessionState() {
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public QuestionImpl getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionImpl currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public List<ClientResponse> getClientResponses() {
        return clientResponses;
    }

    public void setClientResponses(List<ClientResponse> clientResponses) {
        this.clientResponses = clientResponses;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public QuestionState getCurrentQuestionState() {
        return currentQuestionState;
    }

    public void setCurrentQuestionState(QuestionState currentQuestionState) {
        this.currentQuestionState = currentQuestionState;
    }

    public Client getBuzzerClient() {
        return buzzerClient;
    }

    public void setBuzzerClient(Client buzzerClient) {
        this.buzzerClient = buzzerClient;
    }

    public ClientResponse getResponseByClient(Client client) throws NoClientResponseFoundException {
        for (ClientResponse clientResponse : clientResponses) {
            if (clientResponse.getClient().getNickname().equals(client.getNickname())) {
                return clientResponse;
            }
        }
        throw new NoClientResponseFoundException(client);
    }
}
