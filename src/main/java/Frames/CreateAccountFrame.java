package Frames;

import Firebase.FirebaseUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CreateAccountFrame extends JFrame implements ActionListener {
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton createAccountButton;
	
	public CreateAccountFrame() {
		setTitle("Create Account");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame on screen
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		JLabel nameLabel = new JLabel("Name:");
		nameField = new JTextField();
		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();
		JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordField = new JPasswordField();
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(this);
		
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordField);
		panel.add(new JLabel()); // Empty label for spacing
		panel.add(createAccountButton);
		
		add(panel);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createAccountButton) {
			// Get all fields
			String name = nameField.getText();
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			String confirmPassword = new String(confirmPasswordField.getPassword());
			
			// Passwords must match
			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Check if username already exists in the database
			if (FirebaseUtilities.getAllUsernames().contains(username)) {
				JOptionPane.showMessageDialog(this, "Username in use", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// Write new data to the database
				HashMap<String, String> newUserData = new HashMap<>();
				newUserData.put("username", username);
				newUserData.put("name", name);
				newUserData.put("password", String.valueOf(password.hashCode()));
				FirebaseUtilities.createNewUser(newUserData);
				
				this.dispose();
				new LoginFrame(username);
			}
		}
	}
}

