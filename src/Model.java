import java.util.LinkedList;
import java.util.Random;
import java.io.*; 

public class Model {
	private static final int WIDTH = 80;
	private static final int HEIGHT = 24;
	private static final int top = 5;
	private static final int floor = HEIGHT-2;
	private ConsoleView view;
	private ConsoleController controller;
	private Player player;
	private LinkedList<Enemy> enemys;
	private Bullet bullet;
	private LinkedList<Bullet> bullets;
	private StartScreen start;
	private Map main_map, view_map;
	Random random = new Random();

	public Model() {
		view = new ConsoleView(this, WIDTH, HEIGHT);
		controller = new ConsoleController(this);
		enemys = new LinkedList<Enemy>();
		bullets = new LinkedList<Bullet>();
		player = new Player(1, 5);
		start = new StartScreen();
		main_map = new Map("Map.txt"); 
		view_map = new Map(" ");
	}

	public synchronized void process(String event) throws IOException {
		
		LinkedList<Enemy> enemyss = new LinkedList<Enemy>();
		LinkedList<Bullet> bulletss = new LinkedList<Bullet>();
		
		if(event.equals("TIME_ELAPSED")) {
			
			if(player.isResult()) {
				player.result();
				enemys.clear();
				bullets.clear();
				bullet=null;
			}
			
			for (Enemy enemy: enemys) {
				if(enemy.searchPlayer(player.getX(), player.getY())) {
					player.setStart(false);
					player.setResult(true);
					player.setWin(false);
				}
				enemy.update();
			}
			
			if(player.isShoot()) {
				if(bullet==null) {
					bullet=new Bullet(player.getX(),player.getY(), 1);
				}
				else if((bullet.getX()>=0 && bullet.getX()<WIDTH-1) && 
						(bullet.getY()>=0 && bullet.getY()<HEIGHT) && view_map.checkMap(bullet.getY(),bullet.getX()+1)) {
						bullet.update();
				}
				else {
					player.setShoot(false); 
					bullet=null; 
				}
			}
			
			view.update();
		}
		else {
			
			if(start.isStart()) {
				start.menu(event);
				
				switch (event) {
				case "s":
					main_map.readMap();
					bullet=null;
					player = new Player(1, 5);
					player.setStart(true);
					view_map.setMap(main_map.getMap());
					player.setMap(view_map.getMap());
					Bullet b1= new Bullet();
					Bullet b2= new Bullet(random.nextInt(10)+126,top,1);
					Enemy e1= new Enemy(5,floor,10,5);
					Enemy e2= new Enemy(25,top+1,10, 10);
					Enemy e3= new Enemy(63,floor,7,5);
					Enemy e4 = new Enemy(52, top, 5,3);
					Enemy e5 = new Enemy(90, floor, 20, 8);
					Enemy e6 = new Enemy(168, top, 5,4);
					Enemy e7 = new Enemy(97, top, 5,6);
					Enemy e8 = new Enemy(134, top, 7,3);
					Enemy e9 = new Enemy(140, floor, 6,5);
					Enemy e10 = new Enemy(192, floor, 7,4);
					bullets.add(b1);
					bullets.add(b2);
					enemys.add(e1);
					enemys.add(e2);
					enemys.add(e3);
					enemys.add(e4);
					enemys.add(e5);
					enemys.add(e6);
					enemys.add(e7);
					enemys.add(e8);
					enemys.add(e9);
					enemys.add(e10);
					break;
				}
			}
			else {
				
				if(player.isResult()) {
					switch (event) {
					case "r":
						start.setStart(true);
					}
				}
				player.PlayerMove(event);
				
			}
		}
		
		player.setMap(view_map.getMap());
		
		if(player.scrollRight() && main_map.canScroll()) {
			
			LinkedList<Enemy> enemys_scrolled = new LinkedList<Enemy>();
			view_map.setMap(main_map.scrollMapRight());
			
			for (Bullet bullet : bullets) {
				bullet.setX(bullet.getX()-1);
			}
			player.setX(player.getX()-1);
			for(Enemy enemy: enemys) {
				enemy.setX(enemy.getX()- 1);
				enemy.set_x(enemy.get_x()-1);
				enemys_scrolled.add(enemy);
			}
			enemys=enemys_scrolled;
			player.setScroll(false);
		}
		
		for (Enemy enemy: enemys) {
			if(!player.killEnemy(enemy, bullet)) {
				enemyss.add(enemy);
			}
			else {
				player.setShoot(false); 
				bullet=null; 
			}
		}
		
		enemys=enemyss;
		
		for (Bullet bullet : bullets) {
			if(!player.getBullet(bullet)) {
				bulletss.add(bullet);
			}
		}
		
		bullets=bulletss;
		
	}
	
	public void run() throws IOException{
		controller.run();
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public static void main(String[] args) throws IOException  {
		Model model = new Model();
		model.run();
	}

	public LinkedList<Enemy> getEnemy() {
		return enemys;
	}

	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public StartScreen getStart() {
		return start;
	}

	public Map getMap() {
		return view_map;
	}

}