import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnemyTest {

	@Test
	void 敵を自動で左右に移動する(){
		int x=3;
		int y=3;
		int w=10;
		int s=5;
		Enemy enemy = new Enemy(x,y,w,s);
		
		for(int i=0; i<30; i++) {
			enemy.update();
		}
		
		assertEquals(x+w, enemy.getX());
		
		for(int i=0; i<30; i++) {
			enemy.update();
		}
		
		assertEquals(x, enemy.getX());
		
	}
	
	void プレイヤーを捕まえることができる() {
		
		Player p = new Player(10,3);
		
		int x=3;
		int y=3;
		int w=10;
		int s=5;
		
		Enemy enemy = new Enemy(x,y,w,s);
		
		for(int i=0; i<3; i++) {
			enemy.update();
			if(i==2) {
				assertEquals(true, enemy.searchPlayer(p.getX(), p.getY()));
			}
		}
	}

}
