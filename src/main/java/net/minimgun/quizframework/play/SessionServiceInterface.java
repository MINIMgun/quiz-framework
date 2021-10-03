package net.minimgun.quizframework.play;

import java.util.concurrent.TimeUnit;

import net.minimgun.quizframework.exceptions.NoSessionException;
import net.minimgun.quizframework.models.play.Client;
import net.minimgun.quizframework.models.play.Session;
import net.minimgun.quizframework.models.quiz.QuizEntity;

public interface SessionServiceInterface {

    /**
     * Creates a new {@link Session} from the specified {@link QuizEntity} and
     * stores it.
     * 
     * @param quiz The {@link QuizEntity} for this {@link Session}.
     * @return The created {@link Session}
     * @since 0.1.2
     */
    Session createNewSession(QuizEntity quiz);

    /**
     * Returns the {@link Session} with the specified sessionId.
     * 
     * @param sessionId The sessionId of the {@link Session}.
     * @return the {@link Session} with the specified sessionId.
     * @throws NoSessionException if no {@link Session} with the specified sessionId
     *                            exists.
     * @since 0.1.2
     */
    Session getSession(String sessionId) throws NoSessionException;

    /**
     * Deletes the {@link Session} with this sessionId.
     * 
     * @param sessionId The sessionId of the {@link Session} to delete.
     * @since 0.1.2
     */
    void deleteSession(String sessionId);

    /**
     * Returnes true if the specified cliend is currently connected to the backend
     * via websocked.
     * 
     * @param client The {@link Client}
     * @return true if the specified cliend is currently connected
     * @since 0.1.2
     */
    boolean clientIsOnline(Client client);

    void onClientDisconnect(Client client, Session session);

    default Runnable getSessionDeleteRunner(Session session) {
        return new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    if (session.getClients().isEmpty() || allClientsDisconnected()) {
                        deleteSession(session.getSessionId());
                        break;
                    }

                }
            }

            private boolean allClientsDisconnected() {
                for (Client client : session.getClients()) {
                    if (client.isConnected()) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    default Runnable getClientConnectionStateRunner(Session session) {
        return new Runnable() {

            @Override
            public void run() {
                while (sessionExists()) {
                    for (Client client : session.getClients()) {
                        boolean clientIsOnline = clientIsOnline(client);
                        if (client.isConnected() && !clientIsOnline) {
                            client.setConnected(clientIsOnline);
                            onClientDisconnect(client, session);
                            continue;
                        }
                        client.setConnected(clientIsOnline);
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException e) {

                    }
                }
            }

            private boolean sessionExists() {
                try {
                    getSession(session.getSessionId());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }
}
