package org.example;

import org.elsys.operations.OperationFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        while(true) {
            String operation = reader.readLine();
            if (operation.equals("stop")) {
                break;
            }

            System.out.println(client.sendMessage(operation));
        }

        client.stopConnection();
    }
}
