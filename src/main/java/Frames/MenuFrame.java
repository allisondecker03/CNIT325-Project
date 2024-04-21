package Frames;

import Game.NumberGuessingGame;
import Game.Player;
import User.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class MenuFrame extends JFrame implements ActionListener {
	private Player player;
	
	private JButton game1Button;
	private JButton game2Button;
	private JButton game3Button;
	private JButton game4Button;
	private JButton game5Button;
	private JButton changeLanguageButton;
	private JMenu menu;
	
	public MenuFrame(Player player) {
		this.player = player;
		
		setTitle(String.format("%s's %s", player.getUsername(), Client.languageBundle.getString("MF_Title")));
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame on screen
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		game1Button = new JButton("Game.Game 1");
		game1Button.addActionListener(this);
		game2Button = new JButton("Game.Game 2");
		game2Button.addActionListener(this);
		game3Button = new JButton("Number Guesser");
		game3Button.addActionListener(this);
		game4Button = new JButton("Game.Game 4");
		game4Button.addActionListener(this);
		game5Button = new JButton("Game.Game 5");
		game5Button.addActionListener(this);
		
		panel.add(game1Button);
		panel.add(game2Button);
		panel.add(game3Button);
		panel.add(game4Button);
		panel.add(game5Button);
		
		JMenuBar menuBar = new JMenuBar();
		this.menu = new JMenu(Client.languageBundle.getString("MF_Menu_Options"));
		this.changeLanguageButton = new JButton(Client.languageBundle.getString("MF_Change_Language"));
		changeLanguageButton.addActionListener(this);
		menu.add(changeLanguageButton);
		menuBar.add(menu);
		panel.add(menuBar);
		// add(menuBar);
		
		add(panel);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == game1Button) {
			// Open Game.Game 1
			JOptionPane.showMessageDialog(this, "Opening Game.Game 1");
		} else if (e.getSource() == game2Button) {
			// Open Game.Game 2
			JOptionPane.showMessageDialog(this, "Opening Game.Game 2");
		} else if (e.getSource() == game3Button) {
			NumberGuessingGame numberGuessingGame = new NumberGuessingGame();
		} else if (e.getSource() == game4Button) {
			// Open Game.Game 4
			JOptionPane.showMessageDialog(this, "Opening Game.Game 4");
		} else if (e.getSource() == game5Button) {
			// Open Game.Game 5
			JOptionPane.showMessageDialog(this, "Opening Game.Game 5");
		} else if (e.getSource() == changeLanguageButton) {
			if (Client.languageBundle.getBaseBundleName().equals("languages/english")) {
				Client.languageBundle = ResourceBundle.getBundle("languages/spanish");
				changeLanguage();
			} else {
				Client.languageBundle = ResourceBundle.getBundle("languages/english");
				changeLanguage();
			}
		}
	}
	
	private void changeLanguage() {
		this.changeLanguageButton.setText(Client.languageBundle.getString("MF_Change_Language"));
		this.setTitle(Client.languageBundle.getString("MF_Title"));
		this.menu.setText(Client.languageBundle.getString("MF_Menu_Options"));
	}
}

