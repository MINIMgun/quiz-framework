package net.minimgun.quizframework.exceptions;

public class NoEntityInDatabaseListException extends Exception {

    private static final long serialVersionUID = 4414707914950579038L;

    public NoEntityInDatabaseListException() {
        super("The list returned from the database was empty.");
    }
}
