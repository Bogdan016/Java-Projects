import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		GamePanel panel = new GamePanel();
		frame.add(panel);
		frame.pack();	//the size of the GamePanel becomes the size of the window
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel.lunchGame();
	}

}
