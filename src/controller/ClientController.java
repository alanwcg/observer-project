package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ClientScreen;

public class ClientController implements ActionListener {
	
	private ClientScreen screen;
	
	public ClientController(ClientScreen screen) {
		this.screen = screen;
		screen.getLikeButton().addActionListener(this);
		screen.getDislikeButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == screen.getLikeButton()) {
			screen.updateLikesLabel();
			screen.getLikeButton().setEnabled(false);
			screen.getDislikeButton().setEnabled(false);
		} else if(e.getSource() == screen.getDislikeButton()) {
			screen.updateDislikesLabel();
			screen.getDislikeButton().setEnabled(false);
			screen.getLikeButton().setEnabled(false);
		}
	}

}
