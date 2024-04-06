import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class Clicker implements ActionListener, GameCallback {
    Game game;
    JFrame frame;
    JButton button;
    JLabel timerLabel;
    JLabel scoreLabel;
    JPanel panel;

    public Clicker() {
        this.game = new Game(30, this);
        frame = new JFrame();
        button = new JButton("Click me");
        timerLabel = new JLabel("Time left: " + game.getSeconds() + " seconds");
        scoreLabel = new JLabel("Clicks: " + game.getScore().getValue());
        panel = new JPanel();


        ///Panel:
        panel.setBorder(BorderFactory.createEmptyBorder(120, 120, 100, 120));    //(top, left, bottom, right).
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.WHITE);

        ///Labels:
        panel.add(timerLabel);

        ///Button:
        panel.add(button);

        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(120, 30));

        Font buttonFont = new Font("San Francisco", Font.PLAIN, 18);
        button.setFont(buttonFont);
        button.setForeground(Color.BLACK);

        panel.add(scoreLabel);
        scoreLabel.setForeground(Color.BLACK);

        Font labelFont = new Font("San Francisco", Font.PLAIN, 20);
        timerLabel.setFont(labelFont);
        scoreLabel.setFont(labelFont);

        button.addActionListener(this);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clicker");
        frame.pack();
        frame.setVisible(true);

        game.start();
    }

    @Override
    public void timeUpdated(int newTime) {
        timerLabel.setText("Time left: " + newTime + " seconds");
    }

    public JLabel getTimerLabel() {
        return this.timerLabel;
    }

    public static void main(String[] args) {
        new Clicker();
    }

    public void actionPerformed(ActionEvent e) {
        game.click();
        scoreLabel.setText("Clicks: " + game.getScore().getValue());
    }

}