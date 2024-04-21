import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RockPaperScissors extends JFrame implements ActionListener {
    JButton rockButton, paperButton, scissorsButton;
    JLabel resultLabel;
    JPanel panel;

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        rockButton = new JButton("ROCK");
        paperButton = new JButton("PAPER");
        scissorsButton = new JButton("SCISSORS");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        JMenuBar mb = new JMenuBar();
        JMenu optionsMenu = new JMenu("Menu");
        setJMenuBar(mb);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));// Hotkey: Command + E
        exitItem.addActionListener(e -> System.exit(0));
        optionsMenu.add(exitItem);

        mb.add(optionsMenu);
        setJMenuBar(mb);

        resultLabel = new JLabel("You Chose:");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(resultLabel, BorderLayout.NORTH);
        add(panel);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent evt) {
        String userChoice = "";
        if (evt.getSource() == rockButton)
            userChoice = "Rock";
        else if (evt.getSource() == paperButton)
            userChoice = "Paper";
        else if (evt.getSource() == scissorsButton)
            userChoice = "Scissors";

        String computerChoice = generateComputerChoice();
        String result = determineWinner(userChoice, computerChoice);

        resultLabel.setText( "You chose: " + userChoice    +  "  \nComputer chose:  " +  computerChoice +  " \n "+ result);


    if (result.equals("Tie"))
        panel.setBackground(Color.lightGray);
    else if (result.equals("You win"))
        panel.setBackground(Color.green);
    else
        panel.setBackground(Color.red);
    }


    private String generateComputerChoice() {
        String[] options = {"Rock", "Paper", "Scissors"};
        int index = (int) (Math.random() * 3);
        return options[index];
    }

    private String determineWinner(String user, String computer) {
        if (user.equals(computer))
            return "Tie";
        else if ((user.equals("Rock") && computer.equals("Scissors")) ||
                (user.equals("Paper") && computer.equals("Rock")) ||
                (user.equals("Scissors") && computer.equals("Paper")))

            return "You win";
        else

            return "Computer won";
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissors ggu = new RockPaperScissors();
            ggu.setVisible(true);
        });
    }
}


