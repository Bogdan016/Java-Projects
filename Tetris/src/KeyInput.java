import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
public static boolean UP, DOWN, LEFT, RIGHT, PAUSE;
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			UP = true;
		}
		
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			LEFT = true;
		}
		
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			DOWN = true;
		}
		
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			RIGHT = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			if(PAUSE) {
				PAUSE = false;
			}
			else {
				PAUSE = true;
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
