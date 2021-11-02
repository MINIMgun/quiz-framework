package net.minimgun.quizframework.models.play.events;

import org.springframework.context.ApplicationEvent;

import net.minimgun.quizframework.models.play.Session;

public class SessionStateChangeEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3231747559929193286L;
    private Session session;

    public SessionStateChangeEvent(Object source, Session session) {
        super(source);
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
