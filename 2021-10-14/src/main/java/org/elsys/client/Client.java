package org.elsys.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;

    Client() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        Socket clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String id = in.readLine();
        System.out.println("client-id: " + id);
        new GetMessage().start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    class GetMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                String msg = null;
                try {
                    msg = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (msg != null) {
                    System.out.println(msg);
                }
            }
        }
    }

    public void initialize() throws IOException {
        startConnection("localhost", 9999);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message: ");
            String msg = scanner.nextLine();
            sendMessage(msg);
        }
    }
}