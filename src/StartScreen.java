import java.io.*;

public class StartScreen {

	private boolean start;
	private boolean showInstruction, showStart, showRanking;
	private final static int center=30;

	public StartScreen() {
		this.start=true;
		this.showStart=true;
	}

	public void paint(ConsoleView view) {
		
		if(start) {
			
			if(showStart) {
				showStart(view);
			}
			else if(showInstruction) {
				showInstruction(view);
			}
			else if(showRanking) {
				showRanking(view);
			}
			
		}
		
	}
	
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	private void showRanking(ConsoleView view) {
		
		BufferedReader reader;
		int i=1;
		int l=11;
		view.drawString("Score ranking:", center-5, 10);
		try {
			reader = new BufferedReader(new FileReader("Highscore.txt"));
			String line = reader.readLine();

			while (line != null) {
				view.drawString(i+". "+line, center-5, l);
				l++;
				i++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		view.drawString("Return [r]", center, 18);
		
	}

	private void showStart(ConsoleView view) {
		view.drawString("ゲームスタート [s]", center, 12);
		view.drawString("操作説明 [q]", center, 13);
		view.drawString("スコアランキン [h]", center, 14);
		view.drawString("ゲームを終了する [e]", center, 15);	
		
	}
	
	private void showInstruction(ConsoleView view) {
		
	
		BufferedReader reader;
		int l=10;

		try {
			reader = new BufferedReader(new FileReader("Instruction.txt"));
			String line = reader.readLine();

			while (line != null) {
				view.drawString(line, center-20, l);
				l++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		view.drawString("Return [r]", center, 18);
		
	}

	public void menu(String s) {
		
		if(start) {
		switch (s) {
		
		case "s":
			if(showStart) {
			start = false;
			}
			break;
		case "q":
			if(showStart) {
			showStart = false;
			showInstruction = true;
			}
			break;
		case "h":
			if(showStart) {
			showStart = false;
			showRanking = true;
			}
			break;
		case "e":
			if(showStart) {
			start=false;
			System.exit(0);
			}
			break;
		case "r":
			if(!showStart) {
				showStart = true;
			}
			if(showRanking) {
				showRanking = false;
			}
			if(showInstruction) {
				showInstruction=false;
			}
			break;
		}
		}
	}
	
	
	
	
}
