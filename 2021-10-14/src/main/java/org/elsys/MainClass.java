package org.elsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.elsys.operations.Operation;
import org.elsys.operations.OperationFactory;
import org.elsys.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClass {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start(9999);
	}
}
