package org.elsys;

import java.io.IOException;

import org.elsys.server.Server;

public class MainClass {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start(9999);
	}
}
