import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;


public class clicker implements ActionListener{
	
	int c = 0;				//clicker counter
    int seconds = 30;		//seconds counter
    Timer timer;
    JFrame frame;
    JButton button;
    JLabel timerLabel;
    JLabel scoreLabel; 
    JPanel panel;

    public clicker() {
        frame = new JFrame(); 				// Create a new JFrame to hold the GUI components
        button = new JButton("Click me"); 
        timerLabel = new JLabel("Time left: " + seconds + " seconds"); // Label for displaying the time left
        scoreLabel = new JLabel("Clicks: " + c); // Label for displaying the click count
        panel = new JPanel(); // Create a JPanel to hold the components

		
		///Panel:
        panel.setBorder(BorderFactory.createEmptyBorder(120, 120, 100, 120));	//(top, left, bottom, right).
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.WHITE);
        
	    ///Labels:
        panel.add(timerLabel);
        
        ///Button: 
      	panel.add(button);
      		
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK); 		// Set the foreground (text) color of the button to black.
        button.setFocusPainted(false); 			// Disable the default focus painting effect when the button is clicked.
        button.setOpaque(true); 						
        button.setPreferredSize(new Dimension(130, 30));	//width of 130 pixels and a height of 30 pixels.

        Font buttonFont = new Font("San Francisco", Font.PLAIN, 18);
        button.setFont(buttonFont);
        button.setBorder(new RoundedBorder(15));	// Set a custom rounded border for the button with a radius of 15.
        button.setForeground(Color.BLACK);			// Set the foreground (text) color of the button to black.

        
        panel.add(scoreLabel);
		scoreLabel.setForeground(Color.BLACK);
		
 		Font labelFont = new Font("San Francisco",Font.PLAIN, 20);
 		timerLabel.setFont(labelFont);
		scoreLabel.setFont(labelFont);
		
		button.addActionListener(this);
			
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clicker");
		frame.pack();
		frame.setVisible(true);
		
        // Create a new Timer that fires an action event every 1000 milliseconds (1 second).
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds--;
                timerLabel.setText("Time left: " + seconds + " seconds");
                if (seconds == 0) {
                    endGame();
                }
            }
        });

        // Start the timer, causing it to begin counting down.
        timer.start();

		
	}
	
	public static void main(String[] args) {
		new clicker();
	}
	
	public void actionPerformed(ActionEvent e) {
		c++;
		scoreLabel.setText("Clicks: " + c);
	}
	
    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "Game over! Your score is: " + c, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
	
	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }

	    public boolean isBorderOpaque() {
	        return true;
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

	
}




