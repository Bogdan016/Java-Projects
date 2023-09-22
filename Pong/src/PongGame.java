import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class PongGame {

	public static void main(String[] args) {
		
		GameFrame frame = new GameFrame();
		
	}

}


public class GameFrame extends JFrame{
	
	GamePanel panel = new GamePanel();
	
	GameFrame(){
		
	}
	
}

public class GamePanel extends JPanel implements Runnable{
	
	static final int WIDTH = 1000;
	static final int HEIGHT = 555;
	static final Dimension SCREEN_SIZE = new Dimension(WIDTH,HEIGHT);
	static final int BALL_SIZE = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	THread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddles p1;
	Paddles p2;
	
	
	GamePanel(){
		
	}
	
	public void newBall() {
		
	}
	
	public void newPaddles() {
		
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void move() {
		
	}
	
	public void collision() {
		
	}
	
	public void run() {
		
	}
	
	public class ActionListener extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			
		}
		
		public void keyReleased(KeyEvent e) {
			
		}
	}
}

public class Paddles extends Rectangle{
	
	Paddles(){
		
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void setYDirection(int yDirection) {
		
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
}

public class Ball extends Rectangle{
	
	Ball(){
		
	}
	
	public void setXDirection(int randomXDirection) {
		
	}
	
	public void setYDirection(int randomYDirection) {
		
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
}

public class Score extends Rectangle{
	
	Score(){
		
	}
	
	public void draw(Graphics g) {
		
	}
	
}