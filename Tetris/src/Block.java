import java.awt.*;


public class Block extends Rectangle{
	public int x,y;
	public static final int SIZE = 25;
	public Color color;
	
	public Block(Color color){
		this.color = color;
	}
	
	public void draw(Graphics2D g2) {
		int m = 2;
		g2.setColor(color);
		g2.fillRect(x+m, y+m, SIZE-2*m, SIZE-2*m);
	}
}
