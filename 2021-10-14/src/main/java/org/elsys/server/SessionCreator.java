package org.elsys.server;

import java.net.Socket;

/*
    TODO:

    Delete this!
    Use lambda instead!

 */

public class SessionCreator extends Thread {
    private final Socket clientSocket;

    public SessionCreator(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        SessionData data = SessionManager.the().beginNew(clientSocket);
        data.out().println(data.getId());
    }

}
