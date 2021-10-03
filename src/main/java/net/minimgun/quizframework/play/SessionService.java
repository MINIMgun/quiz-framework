package net.minimgun.quizframework.play;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;

import net.minimgun.quizframework.exceptions.NoSessionException;
import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.Session;
import net.minimgun.quizframework.models.play.events.ClientDisconnectEvent;
import net.minimgun.quizframework.models.quiz.QuizEntity;
import net.minimgun.quizframework.util.TokenGenerator;

@Component
public class SessionService implements SessionServiceInterface {

    private HashMap<String, Session> sessionMap = new HashMap<>();

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Session createNewSession(QuizEntity quiz) {
        Session session = new Session(getValidSessionId(), quiz);
        sessionMap.put(session.getSessionId(), session);
        registerSession(session);
        return session;
    }

    @Override
    public Session getSession(String sessionId) throws NoSessionException {
        if (sessionMap.containsKey(sessionId)) {
            return sessionMap.get(sessionId);
        }
        throw new NoSessionException(sessionId);
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    private String getValidSessionId() {
        String id = TokenGenerator.generateSessionId();
        if (sessionMap.containsKey(id)) {
            getValidSessionId();
        }
        return id;
    }

    private void registerSession(Session session) {
        new Thread(getSessionDeleteRunner(session)).start();
        new Thread(getClientConnectionStateRunner(session)).start();
    }

    @Override
    public boolean clientIsOnline(Client client) {
        SimpUser user = userRegistry.getUser(client.getSocketUsername());
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public void onClientDisconnect(Client client, Session session) {
        applicationEventPublisher.publishEvent(new ClientDisconnectEvent(this, client, session));
    }
}
