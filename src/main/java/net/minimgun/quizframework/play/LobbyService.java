package net.minimgun.quizframework.play;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.minimgun.quizframework.exceptions.NicknameAlreadyInUseException;
import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.JoinSessionInfo;
import net.minimgun.quizframework.models.play.Session;
import net.minimgun.quizframework.models.quiz.QuizEntity;
import net.minimgun.quizframework.models.quiz.repository.QuizEntityRepositoryService;

@Service
public class LobbyService {

    @Autowired
    SessionService sessionService;

    @Autowired
    QuizEntityRepositoryService quizEntityRepositoryService;

    public JoinSessionInfo createSession(String quizId) {
        QuizEntity quiz = quizEntityRepositoryService.getQuiz(quizId);
        Session session = sessionService.createNewSession(quiz);
        return new JoinSessionInfo(null, session.getSessionId());
    }

    public void availableSession(JoinSessionInfo joinSessionInfo) throws NicknameAlreadyInUseException {
        Session session = sessionService.getSession(joinSessionInfo.getSessionId());
        String nickname = joinSessionInfo.getNickname();
        if (nicknameAlreadyInUse(nickname, session)) {
            throw new NicknameAlreadyInUseException(nickname, joinSessionInfo.getSessionId());
        }
    }

    public List<Client> joinSession(String sessionId, JoinSessionInfo joinSessionInfo, Principal principal) {
        Session session = sessionService.getSession(joinSessionInfo.getSessionId());
        String nickname = joinSessionInfo.getNickname();
        if (!nicknameAlreadyInUse(nickname, session)) {
            List<Client> clients = session.getClients();
            if(clientWithNicknameExists(clients, nickname)) {
                Client client = clients.stream().filter(e -> e.getNickname().equals(nickname)).collect(Collectors.toList()).get(0);
                client.setSocketUsername(principal.getName());
                client.setConnected(true);
            } else {
                Client client = new Client(nickname, 0, session.getClients().isEmpty(), true, principal.getName());
                session.addClient(client);
            }
        }
        return session.getClients();
    }

    private boolean clientWithNicknameExists(List<Client> clients, String nickname) {
        return clients.stream().map(Client::getNickname).collect(Collectors.toList()).contains(nickname);
    }

    private boolean nicknameAlreadyInUse(String nickname, Session session) {
        return session.getClients()
            .stream()
            .filter(e -> e.isConnected())
            .map(Client::getNickname)
            .collect(Collectors.toList())
            .contains(nickname);
    }
}
