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
        static final int PADDLE_WIDTH = 15;
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
            
        	ball = new Ball((WIDTH/2) - (BALL_SIZE/2), (HEIGHT/2)-(BALL_SIZE/2),BALL_SIZE,BALL_SIZE);
        	
        }

        public void newPaddles() {
            
        	p1 = new Paddles(0,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        	p2 = new Paddles(WIDTH-PADDLE_WIDTH-20,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
        }

        public void paint(Graphics g) {
            image = createImage(getWidth(),getHeight());
            graphics = image.getGraphics();
            draw(graphics);
            g.drawImage(image, 10, 0, this);
        }

        public void draw(Graphics g) {
            p1.draw(g);
            p2.draw(g);
            ball.draw(g);
            score.draw(g);
          
        	Toolkit.getDefaultToolkit().sync();
            
            g.setColor(Color.white);
        	g.fillRect((WIDTH/2) -30, 0, 10, HEIGHT);
        	
        	
        }

        public void move() {
            p1.move();
            p2.move();
            ball.move();
        }

        public void collision() {
            
        	
        	//ball collisions
        	if(ball.y <=0) {
        		ball.setYDirection(- ball.ySpeed);
        	}
        	if(ball.y >= HEIGHT-BALL_SIZE) {
        		ball.setYDirection(- ball.ySpeed);
        	}
        	
        	if(ball.intersects(p1)) {
        		ball.xSpeed *= -1;
        		if(ball.ySpeed<0) {
        			ball.ySpeed--;
        		}
        		
        		ball.setXDirection(ball.xSpeed);
        		ball.setYDirection(ball.ySpeed);
        	}
        	
        	if(ball.intersects(p2)) {
        		ball.xSpeed *= 1;
        		if(ball.ySpeed<0) {
        			ball.ySpeed--;
        		}
        		
        		ball.setXDirection(-ball.xSpeed);
        		ball.setYDirection(ball.ySpeed);
        		
        	}
        	
        	//stops paddles at window edges
        	
        	if(p1.y<=0) {
        		p1.y = 0;
        	}
        	if(p1.y >= (HEIGHT-PADDLE_HEIGHT)) {
        		p1.y = HEIGHT-PADDLE_HEIGHT;
        	}
        	
        	if(p2.y<=0) {
        		p2.y = 0;
        	}
        	if(p2.y >= (HEIGHT-PADDLE_HEIGHT)) {
        		p2.y = HEIGHT-PADDLE_HEIGHT;
        	}
        	
        	
        	if(ball.x <=0) {
        		score.p2++;
        		newPaddles();
        		newBall();	
        	}
        	
        	if(ball.x >= WIDTH - BALL_SIZE) {
        		score.p1++;
        		newPaddles();
        		newBall();
        	}

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
                p1.keyPressed(e);
                p2.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                p1.keyReleased(e);
                p2.keyReleased(e);
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

        		if(id==1) {		//player 1
        			if(e.getKeyCode()==KeyEvent.VK_W) {
        				setYDirection(-10);
        				move();
        			}
        			if(e.getKeyCode()==KeyEvent.VK_S) {
        				setYDirection(10);
        				move();
        			}
        		}
        		
        		if(id==2) {		//player 2
        			if(e.getKeyCode()==KeyEvent.VK_UP) {
        				setYDirection(-10);
        				move();
        			}
        			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
        				setYDirection(10);
        				move();
        			}
        		}
        	
        }

        public void keyReleased(KeyEvent e) {
        	if(id==1) {		//player 1
    			if(e.getKeyCode()==KeyEvent.VK_W) {
    				setYDirection(0);
    				move();
    			}
    			if(e.getKeyCode()==KeyEvent.VK_S) {
    				setYDirection(0);
    				move();
    			}
    		}
    		
    		if(id==2) {		//player 2
    			if(e.getKeyCode()==KeyEvent.VK_UP) {
    				setYDirection(0);
    				move();
    			}
    			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
    				setYDirection(0);
    				move();
    			}
    		}
        }

        public void setYDirection(int yDirection) {
            
        	ySpeed = yDirection;
        }

        public void move() {
            
        	y += ySpeed;
        	
        }

        public void draw(Graphics g) {
            
        	g.setColor(Color.white);
        	g.fillRect(x, y, width, height);
        }
    }

    static class Ball extends Rectangle {
    	
    	Random random;
    	int xSpeed;
    	int ySpeed;
    	
        Ball(int x, int y, int width, int height) {
            
        	super(x,y,width,height);
        	random = new Random();
        	
        	int randomX = random.nextInt(2);
        	if(randomX == 0) {
        		randomX--;
        	}
        	setXDirection(randomX*3);
        	
        	int randomY = random.nextInt(2);
        	if(randomY == 0) {
        		randomY--;
        	}
        	setYDirection(randomY*3);
        	
        }

        public void setXDirection(int randomXDirection) {
            
        	xSpeed = randomXDirection;
        	
        }

        public void setYDirection(int randomYDirection) {
            
        	ySpeed = randomYDirection;
        	
        }

        public void move() {
            
        	x += xSpeed;
        	y += ySpeed;
        	
        }

        public void draw(Graphics g) {
            g.setColor(Color.white);
            g.fillOval(x, y, height, width);
        }
    }

    static class Score extends Rectangle {
    	
    	static int WIDTH;
    	static int HEIGHT;
    	int p1;
    	int p2;
    	
    	
        Score(int WIDTH, int GHEIGHT) {
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
