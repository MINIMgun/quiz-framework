package net.minimgun.quizframework.models.play;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minimgun.quizframework.models.quiz.QuizEntity;

public class Session {

    private String sessionId;
    private List<Client> clients = new ArrayList<>();
    private QuizEntity quiz;
    private SessionState currentState;

    public Session(String sessionId, QuizEntity quiz, SessionState currentState) {
        super();
        this.sessionId = sessionId;
        this.quiz = quiz;
        this.currentState = currentState;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public SessionState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SessionState currentState) {
        this.currentState = currentState;
    }

    public Client getClientByNickname(String nickname) {
        return clients.stream()
            .filter(e -> e.getNickname().equals(nickname) && e.isConnected())
            .collect(Collectors.toList())
            .get(0);
    }

    public Client getClientByPrincipal(Principal principal) {
        return clients.stream()
            .filter(e -> e.getSocketUsername().equals(principal.getName()) && e.isConnected())
            .collect(Collectors.toList())
            .get(0);
    }
}
