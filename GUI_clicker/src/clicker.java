import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;



public class clicker implements ActionListener{
	int c = 0;
    int seconds = 30;
    Timer timer;
    JFrame frame;
    JButton button;
    JLabel timerLabel;
    JLabel scoreLabel; // Declare scoreLabel as an instance variable
    JPanel panel;

    public clicker() {
        frame = new JFrame();
        button = new JButton("Click me");
        timerLabel = new JLabel("Time left: " + seconds + " seconds");
        scoreLabel = new JLabel("Clicks: " + c); // Initialize scoreLabel
        panel = new JPanel();
		
		///Panel:
		panel.setBorder(BorderFactory.createEmptyBorder(120, 120, 100, 120));
		panel.setLayout(new GridLayout(0,1));
		panel.setBackground(Color.WHITE);
		
		///Button: 
		panel.add(button);
		
	    button.setBackground(Color.WHITE);	
	    button.setForeground(Color.BLACK); 				
	    button.setFocusPainted(false); 					
        button.setOpaque(true); 						
        button.setPreferredSize(new Dimension(130, 30));
        Font buttonFont = new Font("San Francisco",Font.PLAIN, 20);
        button.setFont(buttonFont);
        button.setBorder(new RoundedBorder(15));
        button.setForeground(Color.BLACK);
        
        panel.add(timerLabel);
        
        
        
        
	    ///Label:
        panel.add(scoreLabel);
		scoreLabel.setForeground(Color.BLACK);
 		Font labelFont = new Font("San Francisco",Font.PLAIN, 20);
		scoreLabel.setFont(labelFont);
		
		button.addActionListener(this);
			
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clicker");
		frame.pack();
		frame.setVisible(true);
		
        // Create a timer to count down from 60 seconds
		timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        seconds--;
		        timerLabel.setText("Time left: " + seconds + " seconds");

		        if (seconds == 0) {
		            endGame();
		        }
		    }
		});
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




