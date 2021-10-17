package org.elsys.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SessionData {

    private final String id;
    private final Socket clientSocket;
    private PrintWriter out_;
    private BufferedReader in_;

    public SessionData(String uuid, Socket clientSocket) {
        this.id = uuid;
        this.clientSocket = clientSocket;

        // Lazy-load these
        this.out_ = null;
        this.in_ = null;
    }

    public BufferedReader in() {
        if (in_ == null) {
            try {
                in_ = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                return null;
            }
        }

        return in_;
    }

    public PrintWriter out() {
        if (out_ == null) {
            try {
                out_ = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                return null;
            }
        }
        return out_;
    }

    public String getId() {
        return id;
    }

}
