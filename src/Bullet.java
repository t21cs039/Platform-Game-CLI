import java.util.Random;   

public class Bullet {
	 private int x, y, vx, vy;
	 private char s;
	 Random random = new Random();  
	 

	 public Bullet(int x, int y, int vx) {
		 this.x=x;
		 this.y=y;
		 this.vx=vx;
		 this.vy=0;
		 this.s='*';
	 }
	 

	public Bullet() {
		 this.x=random.nextInt(10)+63;
		 this.y=6;
		 this.vx=1;
		 this.vy=0;
		 this.s='*';
	 }
	 
	 public void update () {
		 
		 x += vx;
		 y += vy;
		 
	 }
	 
	 public void paint (ConsoleView view) {
		 view.put( s, x, y);
	 }

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	 
	 
}
