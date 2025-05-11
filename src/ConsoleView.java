
public class ConsoleView {

	private char[][] screen;
	private int height;
	private int width;
	private final static int HEIGHT = 24;
	private final static int WIDTH = 80;
	private Model model;
	
	public ConsoleView(Model model) {
		this(model, WIDTH, HEIGHT);
		clear();
	}
	public ConsoleView(Model model, int w, int h) {
		this.model = model;
		screen = new char [h][w];
		width = w;
		height = h;
		clear();
	}
	
	public void update ()  {
		
		clear();
		
		if(model.getStart().isStart()) {
		model.getStart().paint(this);
		}
		
		else if(model.getPlayer()!=null){
			
			if(!model.getPlayer().isResult()) {
			
			model.getMap().paint(this);
			
			for (Enemy enemy : model.getEnemy()) {
				enemy.paint(this);
			}
			
			for (Bullet bullet : model.getBullets()) {
				 bullet.paint(this);
			}
			
			if(model.getBullet()!=null)
				model.getBullet().paint(this);
			
			}
			model.getPlayer().paint(this);
		
			
		}
		paint();
	}
	
	public void clear() {
		for (int y=0; y< screen.length; y++)
			for (int x=0; x<screen[y].length; x++)
				screen[y][x] = ' ';
	}

	public void paint() {
		for (int y=0; y< screen.length; y++) {
			for (int x=0; x<screen[y].length; x++) {
				System.out.print(screen[y][x]);
			}
			System.err.println();
		}
	}

	void put(char c, int x, int y) {
		if((x>=0 && x<width) && (y>=0 && y<height))
			screen[y][x] = c;
	}
	
	private void drawRect(char c, int x, int y, int w, int h) {
		for (int i=y; i<y+h; i++) 
	        if (i==y || i==y+h-1){
	            for(int j=x; j<w+x; j++) {
	                put(c, j, i);
	            }
	        }
	        else 
	            for (int j=x; j<w+x; j++)
	                if(j==x || j==w+x-1) {
	                	put(c,j,i);
	                }
	}

	public void drawString(String s, int x, int y) {
		for(int i=0; i<s.length(); i++)
			put(s.charAt(i), x+i, y);
		
	}
	
	public void drawFramedString(String s, char c, int x, int y) {
		drawString(s, x, y);
		drawRect(c, x-2, y-1, s.length()+4, 3);
	}
	

	
}
