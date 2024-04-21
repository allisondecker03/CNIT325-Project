package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WordleGame extends JFrame {
    private static final String[] WORDS = {"apple", "bread", "crane", "dwell", "earth", "flock", "grape", "house", "input", "joker",
            "knife", "light", "mango", "night", "olive", "pride", "quilt", "robin", "sugar", "truck"};
    private String correctWord;
    private JTextField guessField;
    private JTextArea usedLettersArea;
    private JPanel guessPanel;
    private Set<Character> usedLetters = new HashSet<>();
    private int attempt = 0;

    public WordleGame() {
        correctWord = WORDS[new Random().nextInt(WORDS.length)];
        setTitle("Wordle Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        guessField = new JTextField(5);
        JButton guessButton = new JButton("Guess");

        guessPanel = new JPanel(new GridLayout(6, 1, 2, 2));  // Changed to display each guess on a single line
        usedLettersArea = new JTextArea(2, 25);
        usedLettersArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        JPanel usedLettersPanel = new JPanel();
        usedLettersPanel.add(new JLabel("Used Letters:"));
        usedLettersPanel.add(usedLettersArea);

        add(guessPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(usedLettersPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText().toLowerCase();
                if (guess.length() != 5) {
                    JOptionPane.showMessageDialog(null, "Enter exactly a 5-letter word!");
                } else {
                    updateGuesses(guess);
                    guessField.setText("");
                }
            }
        });
    }

    private void updateGuesses(String guess) {
        JPanel row = new JPanel(new GridLayout(1, 5, 2, 2));  // Create a new row for each guess
        char[] guessChars = guess.toCharArray();
        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel(String.valueOf(guessChars[i]), SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            usedLetters.add(guessChars[i]);
            if (correctWord.charAt(i) == guessChars[i]) {
                label.setBackground(Color.GREEN);
            } else if (correctWord.contains(String.valueOf(guessChars[i]))) {
                label.setBackground(Color.YELLOW);
            } else {
                label.setBackground(Color.LIGHT_GRAY);
            }
            row.add(label);
        }
        guessPanel.add(row);
        updateUsedLetters();
        attempt++;
        if (attempt == 6 || guess.equals(correctWord)) {
            JOptionPane.showMessageDialog(null, "Game Over! The word was: " + correctWord);
            guessField.setEnabled(false);
        }
        guessPanel.revalidate();
        guessPanel.repaint();
    }

    private void updateUsedLetters() {
        StringBuilder usedLettersText = new StringBuilder();
        for (char letter : usedLetters) {
            usedLettersText.append(letter).append(" ");
        }
        usedLettersArea.setText(usedLettersText.toString());
    }
}