package org.elsys.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SessionManager {

    private static SessionManager sessionManager_ = null;
    private final List<Session> sessions = Collections.synchronizedList(new ArrayList<>());

    private SessionManager() {
    }

    class HandleCommunication extends Thread {

        private final Session session;

        HandleCommunication(Session session) {
            this.session = session;
        }

        @Override
        public void run() {
            handleMessages();
        }

        private void handleMessages() {
            while (true) {
                try {
                    String message = session.in().readLine();
                    if (message != null) {
                        if (message.equals("exit")) {
                            sessions.remove(session);
                            session.closeConnection();
                            break;
                        }
                        sendToAll(session.getId(), message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendToAll(String sender, String message) {
            for (Session session : sessions) {
                if (session.getId().equals(sender)) {
                    continue;
                }
                session.out().println(sender + ": " + message);
            }
        }
    }

    static public SessionManager the() {
        if (sessionManager_ == null) {
            sessionManager_ = new SessionManager();
        }
        return sessionManager_;
    }

    public Session beginNew(Socket clientSocket) {
        Session data = new Session(
                UUID.randomUUID().toString(),
                clientSocket
        );

        new HandleCommunication(data).start();
        sessions.add(data);

        return data;
    }

}
