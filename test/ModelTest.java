import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void 最初の状態() {
		
		Model model = new Model();
		assertEquals( 0, model.getEnemy().size());
		assertEquals(0, model.getBullets().size());
		assertEquals(false, model.getPlayer().isStart());
	}
	
	void ゲームが始める状態() {
		
		Model model = new Model();
		model.getPlayer().setStart(false);
		assertEquals(10, model.getEnemy().size());
		assertEquals(2, model.getBullets().size());
		
	}
	
	void プレイヤーはシューティングできる() {
		
		Player p = new Player(1, 1);
		Bullet bullet=new Bullet(p.getX(),p.getY(), 1);
		
		p.getBullet(bullet);
		assertEquals(2, p.getBullet());
		
		p.setShoot(true);
		assertEquals(1, p.getBullet());
		
		for(int i=0; i<3; i++) {
			bullet.update();
		}
		
		assertEquals(4, bullet.getX());
		assertEquals(1, bullet.getY());
	}
	
	
}
