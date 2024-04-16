package Frames;

import Firebase.FirebaseUtilities;
import Game.Player;
import User.Client;

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
        setTitle(Client.languageBundle.getString("LF_Title"));
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel(Client.languageBundle.getString("LF_Username"));
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel(Client.languageBundle.getString("LF_Password"));
        passwordField = new JPasswordField();
        signInButton = new JButton(Client.languageBundle.getString("LF_Sign_In"));
        signInButton.addActionListener(this);
        createAccountButton = new JButton(Client.languageBundle.getString("LF_Create_Account"));
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
                JOptionPane.showMessageDialog(this, Client.languageBundle.getString("LF_Incorrect_Login"), Client.languageBundle.getString("LF_Error_Title"), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == createAccountButton) {
            this.setVisible(false);
            this.dispose();
            new CreateAccountFrame();
        }
    }
}
