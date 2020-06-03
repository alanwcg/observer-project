package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ServerScreen;

public class ServerController implements ActionListener {
	
	private ServerScreen screen;
	
	public ServerController(ServerScreen screen) {
		this.screen = screen;
		screen.getPostButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == screen.getPostButton()) {
			String postText = screen.getTextArea().getText();
			
			if(!postText.isEmpty()) {
				screen.getLastPost().setText(postText);
				screen.getTextArea().setText("");
			}
		}
	}

}
