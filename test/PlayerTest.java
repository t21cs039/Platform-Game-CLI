import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void プレイヤーは指定された座標を保持している() {
		Player p = new Player(1, 1);
		assertEquals(1, p.getX());
		assertEquals(1, p.getY());
	}
	
	@Test
	void プレイヤーは移動できる() {
		Player p = new Player(2, 1);
		
		p.PlayerMove("LEFT");
		assertEquals(1, p.getX());
		assertEquals(1, p.getY());
		
		p.PlayerMove("RIGHT");
		assertEquals(2, p.getX());
		assertEquals(1, p.getY());
		
	}
	
	@Test
	void 敵を弾で倒すことができる() {
		
		Player p = new Player(1, 1);
		
		Enemy e1 = new Enemy(10, 1, 2, 2);
		Enemy e2 = new Enemy(10, 3, 3, 3);
		
		Bullet b = new Bullet(1, 1, 1);
		p.getBullet(b);
		p.setBullet(1);
		p.setShoot(true);
		
		assertEquals(true, p.killEnemy(e1, b));
		assertEquals(false, p.killEnemy(e2, b));
		
		assertEquals(1, p.getKilled());
		
	}

}
