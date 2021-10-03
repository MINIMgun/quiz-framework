package net.minimgun.quizframework.exceptions;

public class NicknameAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = -5708700456657595878L;

    public NicknameAlreadyInUseException(String nickname, String sessionId) {
        super("The nickname: " + nickname + " was already in use in session: " + sessionId);
    }
}
