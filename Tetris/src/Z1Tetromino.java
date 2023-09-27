import java.awt.Color;

public class Z1Tetromino extends Tetromino{
	public Z1Tetromino() {
		create(new Color(154,205,50));
	}
	
	public void setPos(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = b[0].x - Block.SIZE;
		b[1].y = b[0].y ;
		
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		//	_ _				
		// |_|_|_		b[1] b[0]			
		// 	 |_|_|			 b[2] b[3]
		
	}
}
