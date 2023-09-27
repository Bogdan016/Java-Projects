import java.awt.Color;

public class ITetromino extends Tetromino{
	public ITetromino() {
		create(new Color(32,178,170));
	}
	
	public void setPos(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = b[0].x ;
		b[1].y = b[0].y -  Block.SIZE;
		
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		
		b[3].x = b[0].x ;
		b[3].y = b[0].y + 2 * Block.SIZE;
		
		//	  _
		//   |_|			 b[1]
		//	 |_|			 b[0]
		// 	 |_|		     b[2] 		
		//   |_|		     b[3]
		
	}
}
