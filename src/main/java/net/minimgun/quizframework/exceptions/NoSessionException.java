package net.minimgun.quizframework.exceptions;

import net.minimgun.quizframework.models.play.Session;

public class NoSessionException extends EntityNotFoundException{

    private static final long serialVersionUID = -6451963282183499088L;

        public NoSessionException(String sessionId) {
            super(Session.class, "sessionId", sessionId);
        }
}
