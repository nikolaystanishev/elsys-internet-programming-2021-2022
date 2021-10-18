package org.elsys.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String id;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        id = in.readLine();
        System.out.println("client-id: " + id);
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
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

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startConnection("localhost", 9999);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message: ");
            String msg = scanner.nextLine();
            System.out.println(client.sendMessage(msg));
        }
    }
}