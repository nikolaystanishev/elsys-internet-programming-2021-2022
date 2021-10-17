package org.elsys.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*

    Actually session

 */

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private List<SessionCreator> sessionCreators = new ArrayList<>();

    public Server() {

    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            clientSocket = serverSocket.accept();
            SessionCreator creator = new SessionCreator(clientSocket);
            creator.start();
            // use
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
