package net.minimgun.quizframework.models.play;

public class JoinSessionInfo {

    private String nickname;
    private String sessionId;

    public JoinSessionInfo(String nickname, String sessionId) {
        super();
        this.nickname = nickname;
        this.sessionId = sessionId;
    }

    public JoinSessionInfo() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "JoinSessionInfo [nickname=" + nickname + ", sessionId=" + sessionId + "]";
    }

}
