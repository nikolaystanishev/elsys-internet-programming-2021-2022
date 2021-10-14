package org.elsys.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionManager {

    private static SessionManager sessionManager_ = null;
    private List<SessionData> sessions = new ArrayList<>();

    private SessionManager() {

    }

    static public SessionManager the() {
        if (sessionManager_ == null)
            sessionManager_ = new SessionManager();
        return sessionManager_;
    }

    public SessionData beginNew(Socket clientSocket) {
        // Use clientSocket;

        SessionData data = new SessionData(
                UUID.randomUUID().toString(),
                clientSocket
        );

        sessions.add(data);
        return data;
    }

    public static void end(String targetId) {
        //
    }

}
