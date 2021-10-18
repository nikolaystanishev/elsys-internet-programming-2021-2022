package org.elsys.server;

import java.net.Socket;

public class SessionCreator extends Thread {
    private final Socket clientSocket;

    public SessionCreator(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Session data = SessionManager.the().beginNew(clientSocket);
        data.out().println(data.getId());
    }

}
