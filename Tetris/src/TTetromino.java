import java.awt.Color;

public class TTetromino extends Tetromino{
	public TTetromino() {
		create(new Color(128,0,128));
	}
	
	public void setPos(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = b[0].x - Block.SIZE;
		b[1].y = b[0].y ;
		
		b[2].x = b[0].x+ Block.SIZE;
		b[2].y = b[0].y ;
		
		b[3].x = b[0].x ;
		b[3].y = b[0].y + Block.SIZE;
		
		//	_ _	_			
		// |_|_|_|		b[1] b[0] b[2]			
		//   |_|		     b[3]
		
	}
}
