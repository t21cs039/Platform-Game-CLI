
public class Enemy {
	 private int x, y, width, _x, search, time;  
	 private boolean moveRight;
	 
	 public Enemy(int m, int n, int w, int s) {
		this.x=m;
		this.y=n;
		this.width=w;
		this.search=s;
		this._x=x;
		moveRight = true;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setW(int w) {
		this.width = w;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public void setS(int s) {
		this.search = s;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void update () {
		
		 if(time%3==0) {
			
		 if(moveRight) {
			 x++;
		 }
		 else {
			 x--;
		 }
		}
		 
		 time++;
		 if(x == _x + width)
			 moveRight=false;
		 else if(x == _x)
			 moveRight=true;
		 
	 }

	public void paint (ConsoleView view) {
		 
		 if(x>0) {
		 if(moveRight) {
			 view.put('>', x, y);
		 	 view.put('[', x+1, y);
		 	 view.put(']', x+search, y);
		 }
		 else { 
			 view.put('<', x, y);
		 	 view.put(']', x-1, y);
		 	 view.put('[', x-search, y);
		 }
		 }
	 }

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public boolean searchPlayer(int m, int n) {
		
		if(moveRight) {
			if(m >= x && m <=x+search && n==y) 
				return true;
		}else {
			if(m <= x && m >=x-search && n==y)
				return true;
		}
		return false;
			
	}


	public int get_x() {
		return _x;
	}

	public int getS() {
		return search;
	}

	
	public boolean isMoveRight() {
		return moveRight;
	}


	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}



}