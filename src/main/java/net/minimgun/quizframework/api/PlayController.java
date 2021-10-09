package net.minimgun.quizframework.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import net.minimgun.quizframework.models.play.ClientResponse;
import net.minimgun.quizframework.models.play.SessionState;
import net.minimgun.quizframework.models.play.events.ClientResponseEvent;
import net.minimgun.quizframework.models.play.events.SessionStateChangeEvent;
import net.minimgun.quizframework.models.play.events.TimerEvent;
import net.minimgun.quizframework.play.PlayService;
import net.minimgun.quizframework.play.SessionService;

@RestController
@RequestMapping(value = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Operation(summary = "Get the current state of the session", description = "This method gets the current session state of the session with the specified sessionId.")
    @GetMapping("/get/session/{sessionId}/current-state")
    public ResponseEntity<SessionState> getCurrentSessionState(@PathVariable String sessionId) {
        return new ResponseEntity<>(sessionService.getSession(sessionId).getCurrentState(), HttpStatus.OK);
    }

    @MessageMapping("/session/{sessionId}/next")
    public void next(@DestinationVariable String sessionId, Principal principal) {
        playService.next(sessionId, principal);
    }

    @MessageMapping("/session/{sessionId}/respond")
    public void respond(@DestinationVariable String sessionId, ClientResponse clientResponse, Principal principal) {
        playService.respond(sessionId, clientResponse, principal);
    }

    @MessageMapping("/session/{sessionId}/buzz")
    public void buzz(@DestinationVariable String sessionId, Principal principal) {
        playService.buzz(sessionId, principal);
    }

    @MessageMapping("/session/{sessionId}/correct")
    public void correctBuzzerAnswer(@DestinationVariable String sessionId, Principal principal) {
        playService.correctBuzzerAnswer(sessionId, principal);
    }

    @MessageMapping("/session/{sessionId}/wrong")
    public void wrongBuzzerAnswer(@DestinationVariable String sessionId, Principal principal) {
        playService.wrongBuzzerAnswer(sessionId, principal);
    }

    @EventListener
    @Async
    public void handleClientResponse(ClientResponseEvent clientResponseEvent) {
        simpMessagingTemplate.convertAndSend(
                "/topic/session/" + clientResponseEvent.getSession().getSessionId() + "/responses",
                clientResponseEvent.getSession().getCurrentState().getClientResponses());
    }

    @EventListener
    @Async
    public void handleSessionStateChange(SessionStateChangeEvent sessionStateChangeEvent) {
        simpMessagingTemplate.convertAndSend(
                "/topic/session/" + sessionStateChangeEvent.getSession().getSessionId() + "/current-state",
                sessionStateChangeEvent.getSession().getCurrentState());
    }

    @EventListener
    @Async
    public void handleTimerEvent(TimerEvent timerEvent) {
        simpMessagingTemplate.convertAndSend("/topic/session/" + timerEvent.getSession().getSessionId() + "/timer",
                timerEvent.getSession().getCurrentState().getTimer());
    }
}
