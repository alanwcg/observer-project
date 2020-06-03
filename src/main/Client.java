package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import controller.ClientController;
import interfaces.Observer;
import view.ClientScreen;

public class Client implements Observer, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	
	private ClientScreen screen;
	
	public Client() {
		screen = new ClientScreen();
		new ClientController(screen);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Client client = new Client();
		
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(client);
		out.flush();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		try {
			while(true) {
				String serverNotification = in.readLine();
				client.getScreen().getTextArea().setText(serverNotification);
				client.getScreen().updateButtons();
			}
		} finally {
			socket.close();
		}
		
	}
	
	public ClientScreen getScreen() {
		return screen;
	}

	@Override
	public void update(Object o, String notification) {
		PrintWriter out = (PrintWriter) o;
		out.println(notification);
	}

}
