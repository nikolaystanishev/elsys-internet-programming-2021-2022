package org.elsys.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionManager {

    private static SessionManager sessionManager_ = null;
    private List<SessionData> sessions = new ArrayList<>();

    private SessionManager() {
        new HandleCommunication().start();
    }

    class HandleCommunication extends Thread {

        @Override
        public void run() {
            try {
                handleMessages();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleMessages() throws IOException {
            while (true) {
                synchronized (sessions) {
                    for (SessionData session : sessions) {
                        String message = session.in().readLine();
                        if (message != null) {
                            sendToAll(message);
                        }
                    }
                }
            }
        }

    }

    private void sendToAll(String message) {
        for (SessionData session : sessions) {
            session.out().println(message);
        }
    }

    static public SessionManager the() {
        if (sessionManager_ == null) {
            sessionManager_ = new SessionManager();
        }
        return sessionManager_;
    }

    public SessionData beginNew(Socket clientSocket) {
        // Use clientSocket;

        SessionData data = new SessionData(
                UUID.randomUUID().toString(),
                clientSocket
        );

        synchronized (sessions) {
            sessions.add(data);
        }
        return data;
    }

    public static void end(String targetId) {
        //
    }

}
