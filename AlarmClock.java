import javax.swing.*;
public class AlarmClock {

	public static void main (String[] args) {
	
		AlarmClockGUI gui = new AlarmClockGUI();
		gui.initialize();	
		JOptionPane.showMessageDialog(null, 
				"First box is how many hours you want to pass by, "
				+ "second box is how many minutes, "
				+ "and third is for the song track you want to play.");
	}
}
