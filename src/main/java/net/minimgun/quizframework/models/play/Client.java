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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isConnected ? 1231 : 1237);
        result = prime * result + (isGameMaster ? 1231 : 1237);
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + points;
        result = prime * result + ((socketUsername == null) ? 0 : socketUsername.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (isConnected != other.isConnected)
            return false;
        if (isGameMaster != other.isGameMaster)
            return false;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        if (points != other.points)
            return false;
        if (socketUsername == null) {
            if (other.socketUsername != null)
                return false;
        } else if (!socketUsername.equals(other.socketUsername))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Client [nickname=" + nickname + ", points=" + points + ", isGameMaster=" + isGameMaster
                + ", isConnected=" + isConnected + ", socketUsername=" + socketUsername + "]";
    }

}
