import java.io.IOException;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) throws IOException {
		Dot_Character ch = new Dot_Character();
		Title tl = new Title();
		GameWindow gw = new GameWindow("テストウィンドウ", 1920, 1080, ch, tl);

		gw.setVisible(true);

		TimerTask aTimerTask = new TimerTask() {
			public void run() {
				gw.repaint();
			}
		};
		ch.aTimer.scheduleAtFixedRate(aTimerTask, 0, 80);
		
	}
}

