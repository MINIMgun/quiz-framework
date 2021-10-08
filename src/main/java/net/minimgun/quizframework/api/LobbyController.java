package net.minimgun.quizframework.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.JoinSessionInfo;
import net.minimgun.quizframework.models.play.events.ClientDisconnectEvent;
import net.minimgun.quizframework.play.LobbyService;

@RestController
@RequestMapping(value = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
public class LobbyController {

    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Operation(summary = "Create a new Session", description = "This method creates a new Session with the specified Quiz by quizId.")
    @PostMapping("/create-session/{quizId}")
    public ResponseEntity<JoinSessionInfo> createSession(@PathVariable String quizId) {
        return new ResponseEntity<>(lobbyService.createSession(quizId), HttpStatus.CREATED);
    }

    @Operation(summary = "Checks if the JoinSessionInfo is valid", description = "This method checks if the provided JoinSessionInfo is valid, if it is returns a 200 - OK status code a 4xx otherwise.")
    @PostMapping("/available-session")
    public ResponseEntity<?> availableSession(@RequestBody JoinSessionInfo joinSessionInfo) {
        lobbyService.availableSession(joinSessionInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/session/{sessionId}/join")
    public List<Client> joinSession(@DestinationVariable String sessionId, JoinSessionInfo joinSessionInfo,
            Principal principal) {
        return lobbyService.joinSession(sessionId, joinSessionInfo, principal);
    }

    @EventListener
    public void handleClientDisconnect(ClientDisconnectEvent clientDisconnectEvent) {
        simpMessagingTemplate.convertAndSend(
                "/topic/session/" + clientDisconnectEvent.getSession().getSessionId() + "/join",
                clientDisconnectEvent.getSession().getClients());
    }
}
