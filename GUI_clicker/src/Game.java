import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private Score score;
    private Timer timer;
    private Integer seconds;
    private GameCallback callback;

    Game(int seconds, GameCallback callback) {
        this.score = new Score();
        this.seconds = new Integer(seconds);
        this.callback = callback;
        this.timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.this.seconds--;
                callback.timeUpdated(Game.this.seconds);
                if (Game.this.seconds == 0) {
                    endGame();
                }
            }
        });
    }

    void start() {
        this.timer.start();
    }

    void click() {
        this.score.increment();
    }

    void endGame() {
        this.timer.stop();
        JOptionPane.showMessageDialog(null, "Game over! Your score is: " + score.getValue(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public Score getScore() {
        return score;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getSeconds() {
        return this.seconds;
    }
}