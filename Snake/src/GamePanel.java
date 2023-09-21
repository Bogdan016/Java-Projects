import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int WIDTH = 600;            // Width of the game panel
	static final int HEIGHT = 600;           // Height of the game panel
	static final int UNIT_SIZE = 25;         // Size of each snake unit
	static final int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;  // Total number of game units
	static final int DELAY = 100;             // Delay between game updates (milliseconds)

	final int x[] = new int[GAME_UNITS];     // Array to store x-coordinates of snake segments
	final int y[] = new int[GAME_UNITS];     // Array to store y-coordinates of snake segments
	int bodyParts = 4;                       // Initial number of snake body parts
	int score;                               // Current player's score
	int fx;                                  // x-coordinate of the fruit
	int fy;                                  // y-coordinate of the fruit
	char direction = 'R';                    // Initial direction of the snake (Right)
	boolean running = false;                 // Flag to track if the game is running
	Timer timer;                             // Timer object for game loop
	Random random;                           // Random object for generating random numbers

	
	GamePanel() {
	    // Initialize a Random object to generate random numbers.
	    random = new Random();
	    
	    // Set the preferred size of the GamePanel to WIDTH and HEIGHT.
	    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    
	    // Set the background color of the GamePanel to black.
	    this.setBackground(Color.black);
	    
	    // Allow the GamePanel to receive keyboard input focus.
	    this.setFocusable(true);
	    
	    // Add a KeyListener (MyKeyAdapter) to the GamePanel to handle keyboard input.
	    this.addKeyListener(new MyKeyAdapter());
	    
	    // Start the game by calling the start() method.
	    start();
	}

	
	public void start()
	{
		newFruit();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
	    // This method is called by the Swing framework to render the component.
	    // Call the superclass (JPanel) version of the paint method to perform any necessary default painting operations, such as clearing the component.
	    super.paint(g);
	    
	    // Call the custom draw method to perform the game-specific rendering and drawing operations.
	    draw(g);
	}

	
	public void draw(Graphics g) {
		// This method is responsible for rendering the game components.
		
		if(running == true) {
		
			// Draw grid lines for the game board.
			for (int i = 0; i < HEIGHT/UNIT_SIZE;i++)
			{
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, WIDTH, i*UNIT_SIZE);
			}
			
			g.setColor(Color.RED);						 // Set color to red for the fruit.
			g.fillOval(fx, fy, UNIT_SIZE, UNIT_SIZE);	 // Draw the fruit.
		
			boolean isLightGreen = true;
			for (int i = 0; i < bodyParts; i++) {
	            // Toggle between light green and dark green.
	            if (isLightGreen) {
	                g.setColor(new Color(49, 195, 8));  // Light green
	            } else {
	                g.setColor(new Color(41, 160, 8));      // Dark green
	            }

	            // Draw the snake segment.
	            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

	            // Toggle the flag for the next iteration.
	            isLightGreen = !isLightGreen;
	        }
		}
		else {
			gameOver(g);
		}
	}
	
	public void newFruit() {
		fx = random.nextInt((int)(WIDTH/UNIT_SIZE))*UNIT_SIZE;
		fy = random.nextInt((int)(HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void move(){
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
			if(direction=='U')				//UP
			{
				y[0] = y[0] - UNIT_SIZE;
			}
			else if(direction=='D')			//Down
			{
				y[0] = y[0] +  UNIT_SIZE;
			}
			else if(direction=='L')			//Left
			{
				x[0] = x[0] - UNIT_SIZE;
			}
			else if(direction=='R')			//Right
			{
				x[0] = x[0] + UNIT_SIZE;
			}		
	}
	
	public void fruit() {
		if((x[0] == fx) && (y[0] == fy)) {
			bodyParts++;
			score++;
			newFruit();
		}
	}
	
	public void wall() {
		
		for(int i = bodyParts; i>0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i]))			//check if the head collided with the body
				running = false;
		}
		if(x[0] < 0)
		{
			running = false;								//left border
		}
		
		if(x[0] > WIDTH)
		{
			running = false;								//right border
		}
		
		if(y[0] < 0)
		{	
			running = false;								//top border
		}
		
		if(y[0] > HEIGHT)
		{
			running = false;								//bottom border
		}
		
		if(running == false)
		{
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("San Francisco", Font.PLAIN, 100));
		//font metrics to center the text
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over"))/2, HEIGHT/2);
		
		g.setColor(Color.white);
		g.setFont(new Font("San Francisco", Font.PLAIN, 40));
		
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: "+ score, (WIDTH - metrics2.stringWidth("Score: "+ score))/2, HEIGHT/4);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(running == true)
		{
			move();
			fruit();			//check if there is an fruit
			wall();				//check if there is an wall or the snake itself
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		 // This method is called when a key is pressed.
	    public void keyPressed(KeyEvent e) {
	        // Check which key was pressed using KeyEvent's VK_ constants.
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT:
	                // If the current direction is not right ('R'), change it to left ('L').
	                if (direction != 'R') {
	                    direction = 'L';
	                }
	                break;
	            case KeyEvent.VK_RIGHT:
	                // If the current direction is not left ('L'), change it to right ('R').
	                if (direction != 'L') {
	                    direction = 'R';
	                }
	                break;
	            case KeyEvent.VK_UP:
	                // If the current direction is not down ('D'), change it to up ('U').
	                if (direction != 'D') {
	                    direction = 'U';
	                }
	                break;
	            case KeyEvent.VK_DOWN:
	                // If the current direction is not up ('U'), change it to down ('D').
	                if (direction != 'U') {
	                    direction = 'D';
	                }
	                break;
	            case KeyEvent.VK_A:
	                // This is an alternative control for left ('A' key).
	                // If the current direction is not right ('R'), change it to left ('L').
	                if (direction != 'R') {
	                    direction = 'L';
	                }
	                break;
	            case KeyEvent.VK_D:
	                // This is an alternative control for right ('D' key).
	                // If the current direction is not left ('L'), change it to right ('R').
	                if (direction != 'L') {
	                    direction = 'R';
	                }
	                break;
	            case KeyEvent.VK_W:
	                // This is an alternative control for up ('W' key).
	                // If the current direction is not down ('D'), change it to up ('U').
	                if (direction != 'D') {
	                    direction = 'U';
	                }
	                break;
	            case KeyEvent.VK_S:
	                // This is an alternative control for down ('S' key).
	                // If the current direction is not up ('U'), change it to down ('D').
	                if (direction != 'U') {
	                    direction = 'D';
	                }
	                break;
			}	
			
		}
	}
}
