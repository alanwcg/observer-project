package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.ServerController;
import interfaces.Observer;
import interfaces.Subject;
import view.ServerScreen;

public class Server implements Subject, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final int PORT = 9090;

	private ServerScreen screen;
	
	private Set<Observer> observers = new HashSet<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public Server() {
		screen = new ServerScreen();
		new ServerController(screen);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server();
		
		ServerSocket listener = new ServerSocket(PORT);
		
		try {
			while (true) {
				System.out.println("[SERVER] Waiting for client connection...");
				Socket client = listener.accept();
				System.out.println("[SERVER] Connected to client!");

				ClientHandler clientThread = new ClientHandler(client, server);

				pool.execute(clientThread);
			}
		} finally {
			listener.close();
		}
		
	}
	
	public ServerScreen getScreen() {
		return screen;
	}

	@Override
	public void addObserver(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void notifyObservers(PrintWriter out, String notification) {
		for(Observer client: observers) {
			client.update(out, notification);
		}
	}

}
