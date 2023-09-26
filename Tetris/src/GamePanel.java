import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final int FPS = 60;
	Thread gameThread;	//used to run the game loop
	GamePlay game;
	
	public GamePanel(){
		
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		game = new GamePlay();
	}

	public void lunchGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	//Game loop ( update the screen at regular intervals )
	public void run() {
		double interval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta+= (currentTime - lastTime) / interval;
			lastTime = currentTime;
			
			if (delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
		
	}
	
	public void update() {
		game.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
	}
	

}
 