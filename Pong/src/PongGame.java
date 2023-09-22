import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PongGame {

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
    }

    static class GameFrame extends JFrame {
        GamePanel panel;

        GameFrame() {
        	panel = new GamePanel();
        	this.add(panel);
        	this.setTitle("Pong Game");
        	this.setBackground(Color.black);
        	this.setResizable(false);
        	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	this.pack();
        	this.setLocationRelativeTo(null);
        	this.setVisible(true);
        }
    }

    //The game panel is the canvas on which we are painting
    //The frame is like the frame around the painting
    
    static class GamePanel extends JPanel implements Runnable {
        static final int WIDTH = 1000;
        static final int HEIGHT = 555;
        static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
        static final int BALL_SIZE = 20;
        static final int PADDLE_WIDTH = 25;
        static final int PADDLE_HEIGHT = 100;
        Thread gameThread;
        Image image;
        Graphics graphics;
        Random random;
        Paddles p1;
        Paddles p2;
        Ball ball;
        Score score;

        GamePanel() {
            newPaddles();
            newBall();
            score = new Score(WIDTH, HEIGHT);
            this.setFocusable(true);
            this.addKeyListener(new ActionListener());
            this.setPreferredSize(SCREEN_SIZE);
            
            gameThread = new Thread(this);
            gameThread.start();
        }

        public void newBall() {
            // Implement this method
        }

        public void newPaddles() {
            
        	p1 = new Paddles(0,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        	p2 = new Paddles(WIDTH-PADDLE_WIDTH,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
        }

        public void paint(Graphics g) {
            image = createImage(getWidth(),getHeight());
            graphics = image.getGraphics();
            draw(graphics);
            g.drawImage(image, 0, 0, this);
        }

        public void draw(Graphics g) {
            p1.draw(g);
            p2.draw(g);
        }

        public void move() {
            // Implement this method
        }

        public void collision() {
            
        	
        }

        public void run() {	
        	//game loop similar to Minecraft :))
    
        	long lastTime = System.nanoTime();		
        	double aoT = 60.0;
        	double ns = 1000000000 / aoT;
        	double delta = 0;
        	while(true) {
        		long now = System.nanoTime();
        		delta = delta +(now - lastTime)/ns;
        		lastTime = now;
        		if(delta >=1) {
        			move();
        			collision();
        			repaint();
        			delta--;
        		}
        	}
        }

        public class ActionListener extends KeyAdapter {
            public void keyPressed(KeyEvent e) {
                // Implement this method
            }

            public void keyReleased(KeyEvent e) {
                // Implement this method
            }
        }
    }

    static class Paddles extends Rectangle {
    	
    	int id;
    	int ySpeed;
    	
        Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
            super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);			
            this.id = id;
        }

        public void keyPressed(KeyEvent e) {
            // Implement this method
        }

        public void keyReleased(KeyEvent e) {
            // Implement this method
        }

        public void setYDirection(int yDirection) {
            // Implement this method
        }

        public void move() {
            // Implement this method
        }

        public void draw(Graphics g) {
            
        	if(id==1) {
        		g.setColor(Color.white);
            }
        	else {
        		g.setColor(Color.white);
        	}
        	g.fillRect(x, y, width, height);
        }
    }

    static class Ball extends Rectangle {
    	
    	Random random;
    	int xSpeed;
    	int ySpeed;
    	
        Ball() {
            // Add initialization code here if needed
        }

        public void setXDirection(int randomXDirection) {
            // Implement this method
        }

        public void setYDirection(int randomYDirection) {
            // Implement this method
        }

        public void move() {
            // Implement this method
        }

        public void draw(Graphics g) {
            // Implement this method
        }
    }

    static class Score extends Rectangle {
    	
    	static int WIDTH;
    	static int HEIGHT;
    	int p1;
    	int p2;
    	
    	
        Score(int WIDTH, int HEIGHT) {
            // Add initialization code here if needed
        }

        public void draw(Graphics g) {
            // Implement this method
        }
    }
}
