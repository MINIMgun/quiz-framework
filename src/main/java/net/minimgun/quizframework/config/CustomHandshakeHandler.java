package net.minimgun.quizframework.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import net.minimgun.quizframework.util.StompPrincipal;

public class CustomHandshakeHandler extends DefaultHandshakeHandler{

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        return new StompPrincipal();
    }
}
