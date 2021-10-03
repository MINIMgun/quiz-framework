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
            /*
             * This abomination might need some explanation. First, I convert the List of
             * Clients to a List of Strings and check if the nickname is already in use
             * there. If that is the case I get the first Client where the Nickname is the
             * same ( there should ever only be one but ¯\_(ツ)_/¯ ). If the nickname is not
             * used yet I create a new Client and if there is no Client already registered,
             * I make him the game master.
             */
            Client client = clients.stream().map(Client::getNickname).collect(Collectors.toList()).contains(nickname)
                    ? clients.stream().filter(e -> e.getNickname().equals(nickname)).collect(Collectors.toList()).get(0)
                    : new Client(nickname, 0, true, session.getClients().isEmpty(), principal.getName());
            client.setSocketUsername(principal.getName());
            session.addClient(client);
        }
        return session.getClients();
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
