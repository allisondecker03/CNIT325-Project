import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayerSymbol = 'X'; // Initialize with 'X'
    private Random random;

    public TicTacToeGame() {
        random = new Random();
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3)); // Remove the bottom row for the status label

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty()) {
                button.setText(Character.toString(currentPlayerSymbol)); // Set the button text to the current player's symbol
                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayerSymbol + " wins!");
                    resetGame();
                } else if (checkForDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X'; // Switch players after each move
                    if (currentPlayerSymbol == 'O') {
                        computerMove(); // If it's the computer's turn, make its move
                    }
                }
            }
        }
    }

    private void computerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!buttons[row][col].getText().isEmpty());
        buttons[row][col].setText(Character.toString(currentPlayerSymbol)); // Set the button text to the current player's symbol
        if (checkForWin()) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayerSymbol + " wins!");
            resetGame();
        } else if (checkForDraw()) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        } else {
            currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X'; // Switch players after each move
        }
    }

    private boolean checkForWin() {
        String symbol = Character.toString(currentPlayerSymbol);
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol) && buttons[i][1].getText().equals(symbol) && buttons[i][2].getText().equals(symbol)) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(symbol) && buttons[1][j].getText().equals(symbol) && buttons[2][j].getText().equals(symbol)) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(symbol) && buttons[1][1].getText().equals(symbol) && buttons[2][2].getText().equals(symbol)) {
            return true;
        }
        if (buttons[0][2].getText().equals(symbol) && buttons[1][1].getText().equals(symbol) && buttons[2][0].getText().equals(symbol)) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false; // There's an empty cell, game is not draw yet
                }
            }
        }
        return true; // All cells are filled, it's a draw
    }

    private void resetGame() {
        // Reset all buttons and player turn
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayerSymbol = 'X'; // Reset to 'X' for next game
    }

    public static void main(String[] args) {
        // Create GUI for Player X
        SwingUtilities.invokeLater(TicTacToeGame::new);
    }
}
