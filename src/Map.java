import java.io.*; 

public class Map {
	
	 private String filename;
	 private int[][] map;
	 private static final int WIDTH = 80;
	 private static final int HEIGHT = 24;
	 private int map_scrolled;
	 
	 public Map(String filename) {
		this.filename = filename;
		this.map_scrolled = 1;
		map = new int [HEIGHT][WIDTH + 120];
	}
	 
	 public void readMap() throws IOException{
		
		map_scrolled = 1;
		FileReader file_reader = new FileReader(filename);
		BufferedReader reader = new BufferedReader(file_reader);
		String s;
	
		int i=0;
		while ( (s=reader.readLine()) != null) {
			
			if(i>3){
				
				for(int j=0; j<WIDTH+119; j++) {
					if(s.charAt(j) == '#')
						map[i][j] = 1;
					else if(s.charAt(j) =='G')
						map[i][j] = 2;
					else
						map[i][j] = 0;
				}
			}
			i++;
		 }
		 
		reader.close();
		file_reader.close();
		   
	}
	 
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public void paint(ConsoleView view) {
		for (int y=0; y<HEIGHT; y++)
			for (int x=0; x<WIDTH; x++)
				if(map[y][x] == 1)
					view.put('#', x, y);
				else if(map[y][x] == 2)
					view.put('G', x, y);
				else
					view.put(' ', x, y);
	}


	public int[][] scrollMapRight (){
		
		int [][] temp_map = new int [HEIGHT][WIDTH];
		
		for(int y=0; y<HEIGHT; y++)
			for (int x=0; x<WIDTH; x++)
				temp_map[y][x]=map[y][x + map_scrolled];
		
		map_scrolled+=1;
		
		return temp_map;
		
	}
	
	public boolean canScroll() {
		if(WIDTH+map_scrolled>=200) {
			return false;
		}
		return true;
	}

	public boolean checkMap(int y, int x) {
		if(map[y][x]==1) {
		return false;
	}
		return true;
	}
	
}
