package net.minimgun.quizframework.models.play;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Client {

    private String nickname;
    private int points;
    private boolean isGameMaster;
    private boolean isConnected;
    @JsonIgnore
    private String socketUsername;

    public Client(String nickname, int points, boolean isGameMaster, boolean isConnected, String socketUsername) {
        super();
        this.nickname = nickname;
        this.points = points;
        this.isGameMaster = isGameMaster;
        this.isConnected = isConnected;
        this.socketUsername = socketUsername;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isGameMaster() {
        return isGameMaster;
    }

    public void setGameMaster(boolean isGameMaster) {
        this.isGameMaster = isGameMaster;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public String getSocketUsername() {
        return socketUsername;
    }

    public void setSocketUsername(String socketUsername) {
        this.socketUsername = socketUsername;
    }

}
