import java.awt.*;
import java.awt.event.*;

public class GamePlay {
	
		final int WIDTH = 250;
		final int HEIGHT = 450;
		public static int LEFT;
		public static int RIGHT;
		public static int TOP;
		public static int BOTTOM;
		
		public GamePlay() {
			
			// Main Play Area Frame
			LEFT = (GamePanel.WIDTH/4) - (WIDTH/2);
			RIGHT = LEFT + WIDTH;
			TOP = 70;
			BOTTOM = TOP + HEIGHT;	
		}
		
		public void update() {
			
		}
		
		public void draw(Graphics2D g2) {
			
			//Play area
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(1f));
			g2.drawRect(LEFT-1, TOP-1, WIDTH+2, HEIGHT+2);		// there are 4 pixels for the width of the frame
			
			//Next tetromino area
			g2.setFont(new Font ("Tetris", Font.PLAIN, 25));
			g2.drawString("NEXT:", TOP + WIDTH + 110, TOP +25);
			g2.drawRect(RIGHT + 50, TOP +50 , 100, 70);
			
		}
}

//block size 25 * 25
//18 blocuri inaltime, 10 latime