import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PongGame {

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
    }

    static class GameFrame extends JFrame {
        GamePanel panel;	// Declare a GamePanel instance variable.


        GameFrame() {
        	
        	panel = new GamePanel();	// Create a new GamePanel object.
        	
        	this.add(panel);	// Add the GamePanel to the JFrame.
        	
        	this.setTitle("Pong Game");		// Set the title of the JFrame to "Pong Game".
        	
        	this.setBackground(Color.black);
        	
        	this.setResizable(false);		// Disable resizing of the JFrame.
        	
        	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Set the default close operation to exit the application when the window is closed.
        	
        	this.pack();	// Pack the JFrame to fit its components.
        	
        	this.setLocationRelativeTo(null);	// Center the JFrame on the screen.
        	
        	this.setVisible(true);		// Make the JFrame visible.
        }
    }

    //The game panel is the canvas on which we are painting
    //The frame is like the frame around the painting
    
    static class GamePanel extends JPanel implements Runnable {
        static final int WIDTH = 1000;
        static final int HEIGHT = 555;
        static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);	// The dimension of the game panel
        static final int BALL_SIZE = 20;	// Diameter of the ball
        static final int PADDLE_WIDTH = 15;		
        static final int PADDLE_HEIGHT = 100;
        Thread gameThread;		// Thread for running the game loop
        Image image;		// An image for double buffering
        Graphics graphics;		// Graphics context for rendering
        Random random;		
        Paddles p1;		// Player 1's paddle
        Paddles p2;		// Player 2's paddle
        Ball ball;		// The game ball
        Score score;

        GamePanel() {
            newPaddles();		// Initialize paddles
            
            newBall();		// Initialize the ball
            	
            score = new Score(WIDTH, HEIGHT);		// Initialize the score with the game panel dimensions
            
            this.setFocusable(true);		// Allow the panel to receive keyboard input
            
            this.addKeyListener(new ActionListener());		// Add a key listener for handling user input
            
            this.setPreferredSize(SCREEN_SIZE);		// Set the preferred size of the game panel
            
            gameThread = new Thread(this);		// Create a new thread for running the game loop
            
            gameThread.start();		// Start the game loop thread
        
        }

        public void newBall() {
        	// Create a new Ball object with initial position at the center of the game panel
            // The ball's x and y coordinates are adjusted to place it in the center
        	ball = new Ball((WIDTH/2) - (BALL_SIZE/2), (HEIGHT/2)-(BALL_SIZE/2),BALL_SIZE,BALL_SIZE);
        	
        }

        public void newPaddles() {
        	
        	// Create a new Paddles object for player 1 (left paddle)
            // Initialize its position on the left side of the game panel, vertically centered
        	
        	p1 = new Paddles(0,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        	
            // Create a new Paddles object for player 2 (right paddle)
            // Initialize its position on the right side of the game panel, vertically centered
            // (subtracting PADDLE_WIDTH and 20 to leave some space from the right edge)
        	
        	p2 = new Paddles(WIDTH-PADDLE_WIDTH-20,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
        }

        public void paint(Graphics g) {
        	// Create an off-screen image with the same dimensions as the game panel
            image = createImage(getWidth(),getHeight());
            
            // Get the graphics context of the off-screen image
            graphics = image.getGraphics();
            
            // Call the 'draw' method to draw game elements onto the off-screen image
            draw(graphics);
            
            // Draw the off-screen image onto the game panel at the specified position (10, 0)
            g.drawImage(image, 10, 0, this);
        }

        public void draw(Graphics g) {
        	
        	// Draw player 1's paddle
            p1.draw(g);
            
            // Draw player 2's paddle
            p2.draw(g);
            
            // Draw the ball
            ball.draw(g);
            
            // Draw the game score
            score.draw(g);
            
            // Ensure that the graphics state is synchronized (used for smooth rendering)
        	Toolkit.getDefaultToolkit().sync();
            
        	// Draw a white central dividing line to separate the playing area
            g.setColor(Color.white);
        	g.fillRect((WIDTH/2) -30, 0, 10, HEIGHT);
        
        }

        public void move() {
        	// Move player 1's paddle
            p1.move();

            // Move player 2's paddle
            p2.move();

            // Move the ball
            ball.move();
        }

        public void collision() {
            
            // Handle ball collisions with walls, paddles, and scoring

            // Ball collisions with the top wall
        	if(ball.y <=0) {
        		ball.setYDirection(- ball.ySpeed);		// Reverse the vertical direction of the ball
        	}
        	
        	// Ball collisions with the bottom wall
        	if(ball.y >= HEIGHT-BALL_SIZE) {
        		ball.setYDirection(- ball.ySpeed);		// Reverse the vertical direction of the ball
        	}
        	
        	// Ball collisions with player 1's paddle (p1)
        	if(ball.intersects(p1)) {
        		ball.xSpeed *= -1;		// Reverse the horizontal direction of the ball
        		if(ball.ySpeed<0) {
        			ball.ySpeed--;		// Modify the ball's vertical speed 
        		}
        		
        		// Update the ball's x and y directions
        		ball.setXDirection(ball.xSpeed);
        		ball.setYDirection(ball.ySpeed);
        	}
        	
        	// Ball collisions with player 2's paddle (p2)
        	if(ball.intersects(p2)) {
        		ball.xSpeed *= 1;		// Reverse the horizontal direction of the ball
        		if(ball.ySpeed<0) {
        			ball.ySpeed--;		// Modify the ball's vertical speed 
        		}
        		
        		// Update the ball's x and y directions
        		ball.setXDirection(-ball.xSpeed);
        		ball.setYDirection(ball.ySpeed);
        		
        	}
        	
        	// Ensure that paddles don't go beyond the top and bottom edges of the window
        	
        	if(p1.y<=0) {
        		p1.y = 0;	// Prevent the paddle from going above the top edge
        	}
        	if(p1.y >= (HEIGHT-PADDLE_HEIGHT)) {
        		p1.y = HEIGHT-PADDLE_HEIGHT;	// Prevent the paddle from going below the bottom edge
        	}
        	
        	if(p2.y<=0) {
        		p2.y = 0;	// Prevent the paddle from going above the top edge
        	}
        	if(p2.y >= (HEIGHT-PADDLE_HEIGHT)) {
        		p2.y = HEIGHT-PADDLE_HEIGHT;	// Prevent the paddle from going below the bottom edge
        	}
        	
        	// Handle scoring
        	if(ball.x <=0) {
        		
        		// Ball went beyond the left edge, player 2 scores
        		score.p2++;
        		
        		// Reset paddles and the ball for the next round
        		newPaddles();
        		newBall();	
        	}
        	
        	if(ball.x >= WIDTH - BALL_SIZE) {
        		// Ball went beyond the right edge, player 1 scores	
        		score.p1++;
        		
        		// Reset paddles and the ball for the next round
        		newPaddles();
        		newBall();
        	}

        }

        public void run() {	
        	// Game loop similar to Minecraft :))
    
        	long lastTime = System.nanoTime();		// Get the current time in nanoseconds
        	double aoT = 60.0;		// Desired frame rate 
        	double ns = 1000000000 / aoT;		// Nanoseconds per frame
        	double delta = 0;		// Accumulated time since the last frame
        	
        	while(true) {
        		long now = System.nanoTime();		// Get the current time in nanoseconds
        		delta = delta +(now - lastTime)/ns;		// Calculate the time passed since the last frame
        		lastTime = now;
        		
        		// If enough time has passed to update the game (1 frame or more)
        		if(delta >=1) {
        			move();		// Update the game logic (paddles and ball positions)
        			collision();		// Handle collisions between game elements
        			repaint();		// Request the game panel to repaint itself
        			delta--;	// Subtract 1 frame's worth of time from the accumulator
        		}
        	}
        }

        public class ActionListener extends KeyAdapter {
        	// This method is called when a key is pressed
            public void keyPressed(KeyEvent e) {
            	// Call the 'keyPressed' method for both player 1 (p1) and player 2 (p2) paddles
                // This allows each player's paddle to respond to key presses
                p1.keyPressed(e);
                p2.keyPressed(e);
            }
            
            // This method is called when a key is released
            public void keyReleased(KeyEvent e) {
            	// Call the 'keyReleased' method for both player 1 (p1) and player 2 (p2) paddles
                // This allows each player's paddle to respond to key releases
                p1.keyReleased(e);
                p2.keyReleased(e);
            }
        }
    }

    static class Paddles extends Rectangle {
    	
    	int id;
    	int ySpeed;
    	
        Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        	
            super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);		// Call the constructor of the Rectangle superclass
            this.id = id;		// Set the 'id' attribute to distinguish between player 1 and player 2 paddles
        }

        public void keyPressed(KeyEvent e) {
        		// Handle key presses for moving the paddles
        	
        		if(id==1) {		// Player 1
        			if(e.getKeyCode()==KeyEvent.VK_W) {
        				setYDirection(-10);		// Move paddle up when 'W' key is pressed
        				move();		// Update paddle position
        			}
        			
        			if(e.getKeyCode()==KeyEvent.VK_S) {
        				setYDirection(10);		// Move paddle down when 'S' key is pressed
        				move();		// Update paddle position
        			}
        		}
        		
        		if(id==2) {		// Player 2
        			if(e.getKeyCode()==KeyEvent.VK_UP) {
        				setYDirection(-10);		// Move paddle up when 'UP' arrow key is pressed
        				move();		// Update paddle position
        			}
        			
        			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
        				setYDirection(10);		// Move paddle down when 'DOWN' arrow key is pressed
        				move();		// Update paddle position
        			}	
        		}
        }

        public void keyReleased(KeyEvent e) {
            // Handle key releases to stop paddle movement

            if (id == 1) { // Player 1
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0); // Set the vertical direction to 0 to stop paddle movement
                    move(); // Update paddle position to stop it
                }
            }

            if (id == 2) { // Player 2
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0); // Set the vertical direction to 0 to stop paddle movement
                    move(); // Update paddle position to stop it
                }
            }
        }
        
        public void setYDirection(int yDirection) {
            ySpeed = yDirection; // Set the vertical speed of the paddle
        }


        public void move() {
            y += ySpeed; // Update the y-coordinate of the paddle based on its vertical speed
        }

        public void draw(Graphics g) {
            g.setColor(Color.white); // Set the drawing color to white
            g.fillRect(x, y, width, height); // Draw a white rectangle representing the paddle
        }

    }

    static class Ball extends Rectangle {
    	
    	Random random;
    	int xSpeed;
    	int ySpeed;
    	
        Ball(int x, int y, int width, int height) {
            
        	super(x,y,width,height);	// Call the constructor of the Rectangle superclass
        	random = new Random();		// Initialize the Random object for generating random directions
        	
        	// Initialize random X and Y directions for the ball's initial movement
        	int randomX = random.nextInt(2);		// Generate a random integer either 0 or 1
        	if(randomX == 0) {
        		randomX--;		// Convert 0 to -1, ensuring the ball starts moving left or right
        	}
        	setXDirection(randomX*3);		// Set the horizontal direction (speed) of the ball
        	
        	int randomY = random.nextInt(2);		// Generate a random integer either 0 or 1
        	if(randomY == 0) {
        		randomY--;		// Convert 0 to -1, ensuring the ball starts moving up or down
        	}
        	setYDirection(randomY*3);		// Set the vertical direction (speed) of the ball
        	
        }

        public void setXDirection(int randomXDirection) {
            xSpeed = randomXDirection; // Set the horizontal speed (direction) of the ball
        }

        public void setYDirection(int randomYDirection) {
            ySpeed = randomYDirection; // Set the vertical speed (direction) of the ball
        }


        public void move() {
            x += xSpeed; // Update the x-coordinate of the ball based on its horizontal speed
            y += ySpeed; // Update the y-coordinate of the ball based on its vertical speed
        }

        public void draw(Graphics g) {
            g.setColor(Color.white); // Set the drawing color to white
            g.fillOval(x, y, height, width); // Draw the ball as a white oval
        }

    }

    static class Score extends Rectangle {
        static int WIDTH;
        static int HEIGHT;
        int p1;
        int p2;

        Score(int WIDTH, int HEIGHT) {
            Score.WIDTH = WIDTH;
            Score.HEIGHT = HEIGHT;
        }

        public void draw(Graphics g) {
            g.setColor(Color.white); 
            g.setFont(new Font("Consolas", Font.PLAIN, 60)); 

            g.drawString(String.valueOf(p1), 200, 50);

            g.drawString(String.valueOf(p2), 700, 50);
        }
    }

}
