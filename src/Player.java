import java.io.*;

public class Player {
	
	private int x;
	private int y;
	private int bullet, time, killed, score;
	private boolean shoot, result, start, win, highscore, up, scroll;
	private int[][] map;
	private final static int HEIGHT = 24;
	private final static int WIDTH = 80;
	private final static int center= 35;
	private static final int top = 5;
	private static final int floor = HEIGHT-2;
	
	public Player(int x, int y) {
		this.x=x;
		this.y=y;
		this.shoot=false;
		this.result=false;
		this.start=false;
		this.time=3000;
		this.win=false;
		this.highscore=false;
		this.up=true;
		this.bullet=1;
		this.map=new int [HEIGHT][WIDTH];
		this.scroll=false;
	}


	public void UpdatePos(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void PlayerMove(String s) {
		
		switch(s) {
		case "LEFT":
			if( (x>1) && (map[y][x-1]!=1)) {
				x--;
			}
			break;
		case "RIGHT":
			if((x<WIDTH-2)  && (map[y][x+1]!=1)) {
				x++;
				scroll=true;
			}
			break;
		case "UP":
			up=true;
			if(map[top][x]!=1) {
				y=top;
			}
			else {
				y=top+1;
			}
			break;
		case "DOWN":
			up=false;
			if(map[floor][x]!=1) {
				y=floor;
			}
			else {
				y=floor-1;
			}
			break;
		case "s":
			if(bullet>0) {
			shoot=true;
			bullet--;
			}
			break;
		case "r":
			if(result==true) {
				time=3000;
				killed=0;
				bullet=0;
				shoot=false;
				result=false;
				win=false;
				highscore=false;
			}
			break;
		case "e":
			if(start==true || result==true) {
				System.exit(0);
			}
		}
		
		checkWin(x,y);
		
	}

	public void paint(ConsoleView view) {
		
		if(start&&time!=0){
			view.put('P', x, y);
			int t=time;
			t=t/100;
			view.drawString("Time = " + t, 1, 2);
			time = time-2;
			view.drawString("倒した敵 = " + killed, WIDTH-15, 2);
			view.drawString("* × " + bullet, 1, 3);
			checkFall();
		}
		else{
			result=true;
			if(win) {
				view.drawString("Clear!", center, 10);
			}
			else {
				view.drawString("Game Over...", center, 10);
			}
			view.drawString("残り時間: " + time/100 , center, 11);
			view.drawString("倒した敵: " + killed, center, 12);
			view.drawString("-------------------------------", center-10, 13);
			view.drawString("スコア: " + score, center, 14);
			if(highscore) {
				view.drawString("ハイスコア更新！ ", center, 15);
			}
			view.drawString("もう一度プレイする  [r]", 2, 17);
			view.drawString("ゲームを終了する [e]", center+10, 17);
		}
	}
	 
	public void checkWin(int x, int y) {
		
		if(map[y][x]==2) {
			win=true;
			start=false;
		}
	}
	
	public void checkFall() {
		
		if(y>0){
		
			if(up) {
				if(map[y-1][x]!=1) {
					y--;
				}
			}
			else {
				if(map[y+1][x]!=1) {
					y++;
				}
			}
		}	
	}


	public boolean getBullet(Bullet b) {
		if(b.getX()==x && b.getY()==y) {
			bullet++;
			return true;
		}
		else return false;		
	}


	public boolean killEnemy(Enemy e, Bullet b) {
		if(b!=null) {
			if(e.getX()-1==b.getX() && e.getY()==b.getY()) {
				killed++;
				return true;
			}
			else if(e.getX()==b.getX() && e.getY()==b.getY()) {
				killed++;
				return true;
			}
		}
		return false;
	}

	public void result() {
		
		if(win) {
			score = (100 *(time/100 )) + (killed * 50) + 100;}
		else {
			score = killed * 50;}
		
		BufferedReader reader;
		BufferedWriter writer;
		int [] s = new int [6];
		
		try {
			
			int i=0;
			reader = new BufferedReader(new FileReader("Highscore.txt"));
			String line = reader.readLine();

			while (line != null) {
				s[i]=Integer.parseInt(line); 
				i++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int j=score;
		int k=0;
		for(int i=0; i<5; i++) {
			
			if(j==s[i]) {
				j=s[i+1];
			}
			else if(j>s[i]) {
				k=s[i];
				s[i]=j;
				j=k;
				
				if(i==0) {
					highscore=true;
				}
			}
		}
		
		try {
			
			writer = new BufferedWriter(new FileWriter("Highscore.txt"));
			
			for(int i=0; i<5; i++) {
				writer.write(""+s[i]);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean scrollRight() {
		if(scroll) {
			if(WIDTH - x == 20) {
				scroll=false;
				return true;
			}
		}
		return false;
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
	
	public int getBullet() {
		return bullet;
	}
	
	public int getKilled() {
		return killed;
	}

	public void setKilled() {
		killed++;
	}

	public boolean isStart() {
		
		return start;
	}


	public boolean isWin() {
		return win;
	}


	public void setWin(boolean win) {
		this.win = win;
	}


	public void setStart(boolean start) {
		this.start = start;
		
	}
	
	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {

		this.result = result;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}


	public void setMap(int[][] map) {
		this.map=map;
		
	}

	public boolean isScroll() {
		return scroll;
	}

	public void setScroll(boolean scroll) {
		this.scroll = scroll;
	}

}
