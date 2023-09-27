import java.awt.*;

public class Tetromino {
	
	
	int dropcounter = 0;
	int direction = 1;
	public Block b[] = new Block[4];
	public Block t[] = new Block[4];
	
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
		
	}
	
	public void getD1() {
		
	}
	
	public void getD2() {
		
	}

	public void getD3() {
	
	}

	public void getD4() {
	
	}

	
	public void update(){
		
		if(KeyInput.UP) {
			
		}
		
		if(KeyInput.DOWN) {
			b[0].y += Block.SIZE;
			b[1].y += Block.SIZE;
			b[2].y += Block.SIZE;
			b[3].y += Block.SIZE;
			dropcounter = 0;
			KeyInput.DOWN = false;
		}

		if(KeyInput.LEFT) {
			b[0].x -= Block.SIZE;
			b[1].x -= Block.SIZE;
			b[2].x -= Block.SIZE;
			b[3].x -= Block.SIZE;
			
			KeyInput.LEFT = false;
		}
		
		if(KeyInput.RIGHT) {
			b[0].x += Block.SIZE;
			b[1].x += Block.SIZE;
			b[2].x += Block.SIZE;
			b[3].x += Block.SIZE;
			
			KeyInput.RIGHT = false;
		}
		
		dropcounter++;
		if (dropcounter == GamePlay.dropInt) {
			b[0].y += Block.SIZE;
			b[1].y += Block.SIZE;
			b[2].y += Block.SIZE;
			b[3].y += Block.SIZE;
			dropcounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(b[0].color);
		
		int margin = 2;
		g2.fillRect(b[0].x, b[0].y, Block.SIZE, Block.SIZE);
		g2.fillRect(b[1].x, b[1].y, Block.SIZE, Block.SIZE);
		g2.fillRect(b[2].x, b[2].y, Block.SIZE, Block.SIZE);
		g2.fillRect(b[3].x, b[3].y, Block.SIZE, Block.SIZE);
		
	}
}
