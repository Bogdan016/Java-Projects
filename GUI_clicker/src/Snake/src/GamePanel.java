import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (WIDTH * HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 4;
	int score;
	int fx;							//fruit's x coordonate
	int fy;							//fruit's y coordonate
	char direction = 'R';
	boolean running = false;
 	Timer timer;
	Random random;
	
	GamePanel()
	{
		random = new Random();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
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
		super.paint(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		if(running == true) {
		
			for (int i = 0; i < HEIGHT/UNIT_SIZE;i++)
			{
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, WIDTH, i*UNIT_SIZE);
			}
			g.setColor(Color.RED);						//fruit
			g.fillOval(fx, fy, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i <bodyParts;i++)
			{
				if(i==0)
				{
					g.setColor(Color.green);						//snake head
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
			 		g.setColor(new Color(45,180,0));
			 		g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
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
		g.drawString("Game Over", (WIDTH - metrics2.stringWidth("Game Over"))/2, HEIGHT/2);
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
		
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {			
					direction ='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {			
					direction ='R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {			
					direction ='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {			
					direction ='D';
				}
				break;
			case KeyEvent.VK_A:
				if(direction!='R') {			
					direction ='L';
				}
				break;
			case KeyEvent.VK_D:
				if(direction!='L') {			
					direction ='R';
				}
				break;
			case KeyEvent.VK_W:
				if(direction!='D') {			
					direction ='U';
				}
				break;
			case KeyEvent.VK_S:
				if(direction!='U') {			
					direction ='D';
				}
				break;
			}	
			
		}
	}
}
