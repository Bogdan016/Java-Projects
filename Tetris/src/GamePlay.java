import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

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
		Tetromino next;
		final int NEXTMINOX;
		final int NEXTMINOY;
		
		public static ArrayList<Block> staticBlocks = new ArrayList();
		
		public static int dropInt = 60;		//drop the tetromino every 60 frames
		
		
		
		
		public GamePlay() {
			
			// Main Play Area Frame
			LEFT = (GamePanel.WIDTH/4) - (WIDTH/2);
			RIGHT = LEFT + WIDTH;
			TOP = 70;
			BOTTOM = TOP + HEIGHT;	
			
			STARTPOSX = LEFT + (WIDTH/2) - Block.SIZE;
			STARTPOSY = TOP + Block.SIZE;
			
			NEXTMINOX = 470;
			NEXTMINOY = 130;
			
			current = choose();
			current.setPos(STARTPOSX, STARTPOSY);
			
			next = choose();
			next.setPos(NEXTMINOX, NEXTMINOY);
			
		}
		
		private Tetromino choose() {
			Tetromino tetromino = null;
			int x = new Random().nextInt(7);
			
			switch(x) {
			case 0:
				tetromino = new LTetromino();
				break;
			case 1:
				tetromino = new L2Tetromino();
				break;
			case 2:
				tetromino = new OTetromino();
				break;
			case 3:
				tetromino = new ITetromino();
				break;
			case 4:
				tetromino = new TTetromino();
				break;
			case 5:
				tetromino = new Z1Tetromino();
				break;
			case 6:
				tetromino = new Z2Tetromino();
				break;
			}
			return tetromino;
			
		}
		
		
		public void update() {
			current.update();
		}
		
		public void draw(Graphics2D g2) {
			
			//Play area
			g2.setColor(Color.gray);
			g2.setStroke(new BasicStroke(2f));

			g2.drawRect(LEFT-4, TOP-4, WIDTH+8, HEIGHT+8);		// there is 1 pixel for the width of the frame
				
			
			//Next tetromino area
			g2.setFont(new Font ("Tetris", Font.PLAIN, 25));
			g2.drawString("NEXT:", TOP + WIDTH + 110, TOP +25);
			g2.drawRect(RIGHT + 30, TOP  , 150, 150);
			
			//Draw the current tetromino
			
			if(current!=null) {
				current.draw(g2);
			}
			
			next.draw(g2);
			
			
			g2.setStroke(new BasicStroke(3f));
			/*g2.setColor(Color.white);
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
*/
		        if(KeyInput.PAUSE) {
		        	g2.setColor(Color.white);
		        	//g2.drawRect(600, 70, 300, 150);
		        	g2.fillRect(600, 70, 310, 150);
		        	g2.setColor(Color.gray);
		        	g2.setStroke(new BasicStroke(5f));
		        	g2.drawRect(600, 70, 310, 150);
		        	g2.setColor(Color.black);
		        	g2.setFont(g2.getFont().deriveFont(50f));
		        	g2.drawString("PAUSED",650,160);
		        }
		        
		}
}

//block size 25 * 25
//18 blocuri inaltime, 10 latime