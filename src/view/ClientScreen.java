package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel header;
	private JPanel body;
	private JLabel title;
	
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	private JLabel label;
	private JLabel likes;
	private JLabel dislikes;
	private JButton likeButton;
	private JButton dislikeButton;
	
	private int likesAmount = 0;
	private int dislikesAmount = 0;
	
	public ClientScreen() {
		this.initializeFrame();
		this.setHeader();
		this.setBody();
		
		this.updateButtons();
		
		this.setVisible(true);
	}
	
	private void initializeFrame() {
		this.setTitle("Client");
		this.setSize(720, 480);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setHeader() {
		header = new JPanel();
	
		title = new JLabel("Feed de Fulano");
		title.setFont(new Font("Serif", Font.BOLD, 36));
		header.add(title, BorderLayout.PAGE_START);
		
		this.add(header, BorderLayout.PAGE_START);
	}
	
	private void setBody() {
		body = new JPanel();
		body.setLayout(null);
		
		label = new JLabel("Fulano's Last Post:");
		label.setBounds(50, 35, 120, 25);
		
		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(18f));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		textAreaScroll = new JScrollPane(textArea);
		textAreaScroll.setBounds(50, 70, 620, 100);
		
		likes = new JLabel("Likes: " + likesAmount);
		likes.setBounds(340, 190, 60, 25);
		
		likeButton = new JButton("Like");
		likeButton.setBounds(405, 190, 60, 25);
		
		dislikes = new JLabel("Dislikes: " + dislikesAmount);
		dislikes.setBounds(510, 190, 75, 25);
		
		dislikeButton = new JButton("Dislike");
		dislikeButton.setBounds(590, 190, 80, 25);
		
		body.add(label);
		body.add(textAreaScroll);
		body.add(likes);
		body.add(likeButton);
		body.add(dislikes);
		body.add(dislikeButton);
		
		this.add(body, BorderLayout.CENTER);
	}
	
	public void updateButtons() {
		String text = textArea.getText();
		if(text.isEmpty()) {
			likeButton.setEnabled(false);
			dislikeButton.setEnabled(false);
		} else {
			likeButton.setEnabled(true);
			dislikeButton.setEnabled(true);
		}
		
		likesAmount = 0;
		likes.setText("Likes: " + likesAmount);
		
		dislikesAmount = 0;
		dislikes.setText("Dislikes: " + dislikesAmount);
	}
	
	public void updateLikesLabel() {
		likesAmount++;
		likes.setText("Likes: " + likesAmount);
	}
	
	public void updateDislikesLabel() {
		dislikesAmount++;
		dislikes.setText("Dislikes: " + dislikesAmount);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getLikeButton() {
		return likeButton;
	}

	public JButton getDislikeButton() {
		return dislikeButton;
	}

	public int getLikesAmount() {
		return likesAmount;
	}

	public int getDislikesAmount() {
		return dislikesAmount;
	}

}
