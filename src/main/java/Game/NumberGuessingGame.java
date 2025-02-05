package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int correctNumber;
    private int attempts = 0;
    private JTextField inputField;
    private JButton guessButton;
    private JLabel promptLabel;
    private JLabel feedbackLabel;

    public NumberGuessingGame() {
        // Generate a random number between 1 and 20
        correctNumber = new Random().nextInt(20) + 1;

        // Set up the JFrame
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(240, 120);
        setLocationRelativeTo(null); // Center the window

        // Components
        promptLabel = new JLabel("Guess a number between 1 and 20:");
        inputField = new JTextField(5);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("You have 3 attempts.");

        // Adding components
        add(promptLabel);
        add(inputField);
        add(guessButton);
        add(feedbackLabel);

        // Button listener
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(inputField.getText());
                    if (guess < 1 || guess > 20) {
                        feedbackLabel.setText("Please enter a number between 1 and 20.");
                    } else {
                        attempts++;
                        checkGuess(guess);
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
            }
        });

        setVisible(true);
    }

    private void checkGuess(int guess) {
        if (guess == correctNumber) {
            feedbackLabel.setText("Correct! The number was " + correctNumber);
            inputField.setEnabled(false);
            guessButton.setEnabled(false);
        } else if (attempts >= 3) {
            feedbackLabel.setText("Game over! The number was " + correctNumber);
            inputField.setEnabled(false);
            guessButton.setEnabled(false);
        } else {
            if (guess < correctNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }
        }
    }
}