package net.minimgun.quizframework.models.play;

import java.util.ArrayList;
import java.util.List;

import net.minimgun.quizframework.models.quiz.QuizEntity;

public class Session {

    private String sessionId;
    private List<Client> clients = new ArrayList<>();
    private QuizEntity quiz;

    public Session(String sessionId, QuizEntity quiz) {
        super();
        this.sessionId = sessionId;
        this.quiz = quiz;
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
}
