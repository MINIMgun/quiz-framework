package net.minimgun.quizframework.util;

import java.security.Principal;
import java.util.UUID;

public class StompPrincipal implements Principal {

    private String name;

    public StompPrincipal() {
        name = UUID.randomUUID().toString();
    }

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
