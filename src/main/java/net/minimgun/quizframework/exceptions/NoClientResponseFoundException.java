package net.minimgun.quizframework.exceptions;

import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.ClientResponse;

public class NoClientResponseFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 498922233325596476L;

    public NoClientResponseFoundException(Client client) {
        super(ClientResponse.class, "client", client);
    }
}
