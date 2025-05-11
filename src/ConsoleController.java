import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.Timer;

public class ConsoleController implements ActionListener{
	
	private final static int DELAY = 60;
	private Model model;
	 private Timer timer;
	 
	public ConsoleController(Model m) {
		model = m;
		timer = new Timer(DELAY, this);
	}
	 
	public void run() throws IOException {
		timer.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = reader.readLine()) != null)
			model.process(line);
	}
	public void actionPerformed(ActionEvent e) {
		
			try {
				model.process("TIME_ELAPSED");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		
	}
}
