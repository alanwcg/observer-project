package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import interfaces.Observer;

public class ClientHandler implements Runnable {
	
	private Socket client;
	private ObjectInputStream in;
	private BufferedReader reader;
	private PrintWriter out;
	
	private Server server;
	
	public ClientHandler(Socket clientSocket, Server server) throws IOException {
		client = clientSocket;
		this.server = server;
		
		in = new ObjectInputStream(client.getInputStream());
		reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
	}

	@Override
	public void run() {
		Object client = null;
		try {
			client = in.readObject();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		} finally {
			if (client instanceof Observer) {
				server.addObserver((Observer) client);
			}
		}
		
		try {
			String notification = "";
			String aux = "";
			while(true) {
				notification = server.getScreen().getLastPost().getText();
				
				if (!notification.isEmpty() && !aux.contentEquals(notification)) {
					server.notifyObservers(out, notification);
					aux = notification;
				}
				
			}
		} finally {
			System.out.println("[SERVER] Closing.");
			out.close();
			try {
				in.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
