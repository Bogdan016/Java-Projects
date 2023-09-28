import java.awt.Color;

public class L2Tetromino extends Tetromino{
	public L2Tetromino() {

		create(new Color(255,127,80));
	}
	
	public void setPos(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		
		b[1].x = b[0].x;
		b[1].y = b[0].y - Block.SIZE;
		
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		
		b[3].x = b[0].x - Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		//	  _
		//   |_|			 b[1]
		//  _|_|			 b[0]			so we could rotate it
		// |_|_|		b[2] b[3]
	}
	
	
public void getD1() {
		
		t[0].x = b[0].x;
		t[0].y = b[0].y;
		
		t[1].x = b[0].x;
		t[1].y = b[0].y - Block.SIZE;
		
		t[2].x = b[0].x;
		t[2].y = b[0].y + Block.SIZE;

		t[3].x = b[0].x - Block.SIZE;
		t[3].y = b[0].y + Block.SIZE;
		
		
		updatePos(1);
		
		//	  _			Default Direction:
		//   |_|			 b[1]
		//  _|_|			 b[0]			
		// |_|_|		b[2] b[3]
	}
	
	public void getD2() {
		t[0].x = b[0].x;
		t[0].y = b[0].y;
		
		t[1].x = b[0].x + Block.SIZE;
		t[1].y = b[0].y ;
		
		t[2].x = b[0].x - Block.SIZE;
		t[2].y = b[0].y ;

		t[3].x = b[0].x - Block.SIZE;
		t[3].y = b[0].y - Block.SIZE;
		
		updatePos(2);
		//				Next Direction:
		// 	_	
		// |_|_ _	   b[3]	
		// |_|_|_|	   b[2] b[0] b[1]	
	}

	public void getD3() {
		t[0].x = b[0].x;
		t[0].y = b[0].y;
		
		t[1].x = b[0].x ;
		t[1].y = b[0].y + Block.SIZE;
		
		t[2].x = b[0].x ;
		t[2].y = b[0].y - Block.SIZE;

		t[3].x = b[0].x + Block.SIZE;
		t[3].y = b[0].y - Block.SIZE;
		
		updatePos(3);
		
		//    _ _		Default Direction:
		// 	 |_|_|		 	 b[2] b[3]
		//   |_|			 b[0]			
		//   |_|		     b[1]
	}

	public void getD4() {
		t[0].x = b[0].x;
		t[0].y = b[0].y;
		
		t[1].x = b[0].x - Block.SIZE;
		t[1].y = b[0].y ;
		
		t[2].x = b[0].x + Block.SIZE;
		t[2].y = b[0].y ;

		t[3].x = b[0].x + Block.SIZE;
		t[3].y = b[0].y + Block.SIZE;
		
		updatePos(4);
		
		//		 		Next Direction:
		// 	_ _	_		   
		// |_|_|_|	 b[1] b[0] b[2]			
		// 	   |_| 	 		   b[3]
	}
}