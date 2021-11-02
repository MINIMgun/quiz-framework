package net.minimgun.quizframework.models.play.events;

import org.springframework.context.ApplicationEvent;

import net.minimgun.quizframework.models.play.Session;

public class TimerEvent extends ApplicationEvent {

    private static final long serialVersionUID = 525080117757550033L;
    private Session session;

    public TimerEvent(Object source, Session session) {
        super(source);
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
