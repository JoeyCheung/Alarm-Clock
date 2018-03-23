import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
public class AlarmClockGUI extends Songs{

	JFrame frame = new JFrame("Alarm Clock");
	JTextArea time = new JTextArea(1,5);
	JButton set = new JButton("setAlarm");
	TextField tf1, tf2, tf3;
	
	public void initialize() {
		setTime();
		JPanel panel = new JPanel();
		frame.setSize(200,175);
		frame.setLocation(750,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setBackground(Color.black);
		panel.add(time);
		panel.add(set);
		panel.add(getComponent());
		time.setBorder(BorderFactory.createLineBorder(Color.blue));
		time.setFont(new Font("arial",Font.BOLD,33));
		time.setForeground(Color.black);
		time.setLineWrap(true);
		frame.setVisible(true);
	}
	
	public void setTime() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while(true) {
						Calendar now = new GregorianCalendar();
						int hour = now.get(Calendar.HOUR_OF_DAY);
						int minute = now.get(Calendar.MINUTE);
						int second = now.get(Calendar.SECOND);
					
						time.setText(hour + ":" + minute + ":" + second);
					
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	protected JComponent getComponent() {
		JPanel panel = new JPanel();
		handler handler = new handler();
		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField();
		set.addActionListener(handler);
		tf1.addActionListener(handler);
		tf2.addActionListener(handler);
		tf3.addActionListener(handler);
		panel.add(tf1);
		panel.add(tf2);
		panel.add(tf3);
		return panel;
	}
	
	private class handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
		
			String cmd = e.getActionCommand();
			if(cmd.equals("setAlarm")) { 
				Date date = new Date();
				long diff = date.getTime();
				long hour = Long.parseLong(tf1.getText());
				hour = hour * 60;
				long minute = Long.parseLong(tf2.getText());
				minute = ((hour + minute)*60);
				long time = minute * 1000;
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					public void run() {
						String song = tf3.getText();
						song = (song + ".wav");
						soundClip(song);
					}
				};	
				timer.schedule(task,time);
			}
		}
	}
}
