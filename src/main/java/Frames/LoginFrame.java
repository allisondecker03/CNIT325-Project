package Frames;

import Firebase.FirebaseUtilities;
import Game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton createAccountButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        signInButton = new JButton("Sign In");
        signInButton.addActionListener(this);
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signInButton);
        panel.add(createAccountButton);

        add(panel);

        setVisible(true);
    }
    
    public LoginFrame(String username) {
        this();
        this.usernameField.setText(username);
        this.usernameField.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            // Handle sign in button click
            String username = usernameField.getText().toLowerCase().trim();
            String password = String.valueOf(new String(passwordField.getPassword()).hashCode());
            
            if (FirebaseUtilities.userExists(username, password)) {
                this.setVisible(false);
                this.dispose();
                new MenuFrame(new Player(username));
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect login information", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == createAccountButton) {
            this.setVisible(false);
            this.dispose();
            new CreateAccountFrame();
        }
    }
}
