import java.awt.Color;

public class OTetromino extends Tetromino{
	
	public OTetromino() {
		create(new Color(204,204,0));
	}
	
	public void setPos(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = b[0].x + Block.SIZE;
		b[1].y = b[0].y ;
		
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		//	_ _				
		// |_|_|		b[0] b[1]			
		// |_|_|		b[2] b[3]
		
	}
	public void getD1() {

	}
	
	public void getD2() {
	
	}
	
	public void getD3() {

	}
	
	public void getD4() {
		
	}
}
