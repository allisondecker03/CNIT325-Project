package Game.WarGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class War extends JFrame {

    private Deck deck;
    private List<WarPlayer> players;
    private JTextArea outputArea; //text area to display game

    //initialize the game
    public War() {
        promptForPlayers(); //Prompts for number of players and their names
        deck = new Deck(); //Creates new deck
        outputArea = new JTextArea(10, 30);

        initializeGUI();
    }

    //enter number of players and names
    private void promptForPlayers() {
        String numPlayersStr = JOptionPane.showInputDialog("Enter number of players:");
        int numPlayers = Integer.parseInt(numPlayersStr);

        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            String name = JOptionPane.showInputDialog("Enter name for Player " + i);
            players.add(new WarPlayer(name));
        }
    }

    private void initializeGUI() {
        setTitle("Card War"); //title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close operation

        JButton dealButton = new JButton("Deal"); //deal button
        dealButton.setBackground(Color.GREEN); //green background
        dealButton.addActionListener(new DealButtonListener()); //action listener for deal button

        JButton exitButton = new JButton("Exit"); //exit button
        exitButton.setBackground(Color.GREEN);
        exitButton.addActionListener(new ExitButtonListener()); //action listener for deal button

        JPanel buttonPanel = new JPanel(); // panel for buttons
        buttonPanel.setBackground(Color.GREEN);
        buttonPanel.add(dealButton);
        buttonPanel.add(exitButton);

        getContentPane().setBackground(Color.GREEN);

        outputArea.setBackground(Color.GREEN); //backgound color
        outputArea.setForeground(Color.BLACK); //text color

        add(outputArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); //center frame
        setVisible(true);
    }

    //dealing cards to each player
    private void dealCards() {
        for (WarPlayer player : players) {
            player.clearHand(); //clear hand of players
            for (int i = 0; i < 4; i++) { //deals only 4 cards each
                player.addCard(deck.dealCard());
            }
        }
    }

    //display the players and hands
    private void displayPlayers() {
        outputArea.setText(""); //clear output area
        for (WarPlayer player : players) {
            outputArea.append(player.getName() + ": ");
            for (Card card : player.getHand()) {
                outputArea.append(card.toString() + " ");
            }
            outputArea.append("\n"); //new line
        }
    }

    //total value in hand calculation
    private WarPlayer getWinner() {
        WarPlayer winner = players.get(0); //Assumes the first player is winner
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getHandValue() > winner.getHandValue()) {
                winner = players.get(i); //Updates the winner if someone else has higher card values
            }
        }
        return winner;
    }

    private class DealButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deck = new Deck(); //reset deck
            deck.shuffle(); //shuffle deck
            dealCards();
            displayPlayers();
            WarPlayer winner = getWinner(); //finds winner
            outputArea.append("\nWinner: " + winner.getName() + " with a total value of " + winner.getHandValue());
            getContentPane().setBackground(Color.GREEN);
        }
    }

    //exit button action listner
    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0); //exit
        }
    }
}