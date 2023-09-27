import java.awt.*;
import java.awt.event.*;

public class GamePlay {
	
		final int WIDTH = 250;
		final int HEIGHT = 450;
		public static int LEFT;
		public static int RIGHT;
		public static int TOP;
		public static int BOTTOM;
		
		Tetromino current;
		final int STARTPOSX;
		final int STARTPOSY;
		
		public static int dropInt = 60;		//drop the tetromino every 60 frames
		
		
		
		
		public GamePlay() {
			
			// Main Play Area Frame
			LEFT = (GamePanel.WIDTH/4) - (WIDTH/2);
			RIGHT = LEFT + WIDTH;
			TOP = 70;
			BOTTOM = TOP + HEIGHT;	
			
			STARTPOSX = LEFT + (WIDTH/2) - Block.SIZE;
			STARTPOSY = TOP + Block.SIZE;
			
			current = new LTetromino();
			current.setPos(STARTPOSX, STARTPOSY);
			
		}
		
		public void update() {
			current.update();
		}
		
		public void draw(Graphics2D g2) {
			
			//Play area
			g2.setColor(Color.gray);
			g2.setStroke(new BasicStroke(8f));

			g2.drawRect(LEFT-4, TOP-4, WIDTH+8, HEIGHT+8);		// there is 1 pixel for the width of the frame
				
			
			//Next tetromino area
			g2.setFont(new Font ("Tetris", Font.PLAIN, 25));
			g2.drawString("NEXT:", TOP + WIDTH + 110, TOP +25);
			g2.drawRect(RIGHT + 50, TOP +50 , 100, 70);
			
			//Draw the current tetromino
			
			if(current!=null) {
				current.draw(g2);
			}
			
			g2.setStroke(new BasicStroke(3f));
			g2.setColor(Color.white);
			  int x = 25 + LEFT;
			  	for (int i = 0; i < 9; i++) {
		            g2.drawLine(x, TOP, x, HEIGHT+70);
		            x += 25; 
		        }
			
			  int y = TOP+25; 

		        for (int i = 0; i < 17; i++) {
		        	g2.drawLine(LEFT, y, RIGHT, y);
		            y += 25; 
		        }

		}
}

//block size 25 * 25
//18 blocuri inaltime, 10 latime