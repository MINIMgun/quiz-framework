package net.minimgun.quizframework.exceptions.handling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to model exceptions in JSON.
 * 
 * @author MINIMgun
 */
public class ExceptionEntity {

    /**
     * The type of the thrown exception
     */
    private final String type;
    /**
     * The message of the exception
     */
    private final String message;
    /**
     * The time at which the exception was thrown
     */
    private final String time;

    /**
     * 
     * Creates a new {@link ExceptionEntity}.
     * 
     * @param e The thrown {@link Exception}.s
     */
    public ExceptionEntity(Exception e) {
        super();
        this.type = e.getClass().getSimpleName();
        this.message = e.getMessage();
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS"));
    }

    /**
     * The {@link #type}.
     *
     * @return The {@link #type}.
     */
    public String getType() {
        return type;
    }

    /**
     * The {@link #message}.
     *
     * @return The {@link #message}.
     */
    public String getMessage() {
        return message;
    }

    /**
     * The {@link #time}.
     *
     * @return The {@link #time}.
     */
    public String getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        ExceptionEntity other = (ExceptionEntity) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExceptionEntity [type=" + type + ", message=" + message + ", time=" + time + "]";
    }
}
