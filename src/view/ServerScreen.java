package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel header;
	private JPanel body;
	private JLabel title;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	private JScrollPane postScroll;
	private JButton postButton;
	private JLabel label;
	private JTextArea lastPost;
	
	public ServerScreen() {
		this.initializeFrame();
		this.setHeader();
		this.setBody();
		this.setVisible(true);
	}
	
	private void initializeFrame() {
		this.setTitle("Server");
		this.setSize(720, 480);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setHeader() {
		header = new JPanel();
	
		title = new JLabel("Social Media");
		title.setFont(new Font("Serif", Font.BOLD, 36));
		header.add(title, BorderLayout.PAGE_START);
		
		this.add(header, BorderLayout.PAGE_START);
	}
	
	private void setBody() {
		body = new JPanel();
		body.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(18f));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textAreaScroll = new JScrollPane(textArea);
		textAreaScroll.setBounds(50, 30, 620, 100);
		
		postButton = new JButton("Post");
		postButton.setBounds(600, 140, 70, 25);
		
		label = new JLabel("Last Post:");
		label.setBounds(50, 180, 100, 25);
		
		lastPost = new JTextArea();
		lastPost.setFont(textArea.getFont().deriveFont(18f));
		lastPost.setLineWrap(true);
		lastPost.setWrapStyleWord(true);
		lastPost.setEditable(false);
		
		postScroll = new JScrollPane(lastPost);
		postScroll.setBounds(50, 210, 620, 100);
		
		body.add(textAreaScroll);
		body.add(postButton);
		body.add(label);
		body.add(postScroll);
		
		this.add(body, BorderLayout.CENTER);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JButton getPostButton() {
		return postButton;
	}
	
	public JTextArea getLastPost() {
		return lastPost;
	}

}
