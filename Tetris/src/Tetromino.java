import java.awt.*;

public class Tetromino {
	
	
	int dropcounter = 0;
	int direction = 1;
	public Block b[] = new Block[4];
	public Block t[] = new Block[4];
	boolean LC, RC, TC, BC;	//collisions
	public boolean active = true;
	
	public void create(Color color) {
		b[0] = new Block(color);
		b[1] = new Block(color);
		b[2] = new Block(color);
		b[3] = new Block(color);
		
		t[0] = new Block(color);
		t[1] = new Block(color);
		t[2] = new Block(color);
		t[3] = new Block(color);
	}
	
	public void setPos(int x, int y) {
		
	}
	
	public void updatePos(int direction){
		
		RCollision();
		
		if(LC == false && RC == false && BC == false ) {
			this.direction = direction;
			b[0].x = t[0].x;
			b[0].y = t[0].y;
			b[1].x = t[1].x;
			b[1].y = t[1].y;
			b[2].x = t[2].x;
			b[2].y = t[2].y;
			b[3].x = t[3].x;
			b[3].y = t[3].y;
		}

	}
	
	public void getD1() {
		
	}
	
	public void getD2() {
		
	}

	public void getD3() {
	
	}

	public void getD4() {
	
	}
	
	//movement collision
	public void MCollision() {
		LC = false;
		RC = false;
		BC = false;
		
		
		BCollision();
		//check playground collision
		//left wall
		
		for(int i = 0; i<b.length;i++) {
			if(b[i].x == GamePlay.LEFT) {
				LC = true;
			}
		}
		//right wall
		for(int i = 0; i<b.length;i++) {
			if(b[i].x +Block.SIZE== GamePlay.RIGHT) {
				RC = true;
			}
		}
		
		//bottom wall
		for(int i = 0; i<b.length;i++) {
			if(b[i].y + Block.SIZE == GamePlay.BOTTOM) {
				BC = true;
			}
		}
	}
	
	//rotation collision
	public void RCollision() {
		LC = false;
		RC = false;
		BC = false;
		
		BCollision();
		//check playground collision
		//left wall
		
		for(int i = 0; i<b.length;i++) {
			if(t[i].x < GamePlay.LEFT) {
				LC = true;
			}
		}
		//right wall
		for(int i = 0; i<b.length;i++) {
			if(t[i].x + Block.SIZE > GamePlay.RIGHT) {
				RC = true;
			}
		}
		
		//bottom wall
		for(int i = 0; i<b.length;i++) {
			if(t[i].y + Block.SIZE > GamePlay.BOTTOM) {
				BC = true;
			}
		}
	}
	
	//Blocks collision
	private void BCollision() {
		for(int i = 0; i<GamePlay.staticBlocks.size(); i++) {
			int xt = GamePlay.staticBlocks.get(i).x;
			int yt = GamePlay.staticBlocks.get(i).y;
			//bottom
			for(int j = 0; j<b.length; j++) {
				if(b[j].y +Block.SIZE == yt && b[j].x == xt) {
					BC=true;
				}
			}
			//left
			for(int j = 0; j<b.length; j++) {
				if(b[j].x -Block.SIZE == xt && b[j].y == yt) {
					LC = true;
				}
			}
			//right
			for(int j = 0; j<b.length; j++) {
				if(b[j].x +Block.SIZE == xt && b[j].x == xt) {
					RC = true;
				}
			}
		}
	}
	
	public void update(){
		
		if(KeyInput.UP) {
			switch(direction) {
			case 1: getD2(); 
				    break;
			case 2: getD3(); 
		    		break;
			case 3: getD4(); 
		    		break;
			case 4: getD1(); 
					break;
			}
			KeyInput.UP = false;
		}
		
		MCollision();
		
		if(KeyInput.DOWN) {
			
			if(BC == false) {
				b[0].y += Block.SIZE;
				b[1].y += Block.SIZE;
				b[2].y += Block.SIZE;
				b[3].y += Block.SIZE;
				dropcounter = 0;
			}

			KeyInput.DOWN = false;
		}

		if(KeyInput.LEFT) {
			
			if(LC == false) {
				b[0].x -= Block.SIZE;
				b[1].x -= Block.SIZE;
				b[2].x -= Block.SIZE;
				b[3].x -= Block.SIZE;
			}

			
			KeyInput.LEFT = false;
		}
		
		if(KeyInput.RIGHT) {
			if(RC==false) {
				b[0].x += Block.SIZE;
				b[1].x += Block.SIZE;
				b[2].x += Block.SIZE;
				b[3].x += Block.SIZE;
			}

			
			KeyInput.RIGHT = false;
		}
		
		if(BC) {
			active = false;
		}
		else {
			dropcounter++;
			if (dropcounter == GamePlay.dropInt) {
				b[0].y += Block.SIZE;
				b[1].y += Block.SIZE;
				b[2].y += Block.SIZE;
				b[3].y += Block.SIZE;
				dropcounter = 0;
			}
		}
		

	}
	

	
	public void draw(Graphics2D g2) {
		
		g2.setColor(b[0].color);
		
		int m = 2;
		g2.fillRect(b[0].x + m, b[0].y + m, Block.SIZE-(2*m), Block.SIZE-(2*m));
		g2.fillRect(b[1].x + m, b[1].y + m, Block.SIZE-(2*m), Block.SIZE-(2*m));
		g2.fillRect(b[2].x + m, b[2].y + m, Block.SIZE-(2*m), Block.SIZE-(2*m));
		g2.fillRect(b[3].x + m, b[3].y + m, Block.SIZE-(2*m), Block.SIZE-(2*m));
		
	}
}
