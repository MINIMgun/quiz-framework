package net.minimgun.quizframework.models.play.events;

import org.springframework.context.ApplicationEvent;

import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.Session;

public class ClientDisconnectEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7079002978635758801L;
    private Client client;
    private Session session;

    public ClientDisconnectEvent(Object source, Client client, Session session) {
        super(source);
        this.client = client;
        this.session = session;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

}
