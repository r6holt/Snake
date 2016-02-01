import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUIMenu {
	JFrame frame = new JFrame("Snake");
	JPanel buttons = new JPanel(new GridLayout(1,3));
	
	ArrayList<String> scores = new ArrayList<String>();
	ArrayList<String> gridSize = new ArrayList<String>();
	
	public GUIMenu() throws FileNotFoundException {
		frame.setSize(new Dimension(600,600));
		frame.setLocationByPlatform(false);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		JButton play = new JButton("PLAY");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI();
			}
		});
		JButton settings = new JButton("SETTINGS");
		
		getScores();
		JButton highscore = new JButton("HIGHSCORE");
		highscore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame score = new JFrame("HighScores");
				score.setSize(new Dimension(600,600));
				score.setLocationByPlatform(false);
				score.setVisible(true);
				score.setLayout(new GridLayout(11,3));
				
				score.add(new JLabel("PLACE"));
				score.add(new JLabel("SCORE"));
				score.add(new JLabel("GRID SIZE"));
				for(int i=1; i<=10; i++) {
					score.add(new JLabel(i+"."));
					
					if((i-1)<scores.size()) {
						score.add(new JLabel(scores.get(i-1)));
					}
					else {
						score.add(new JLabel(""));
					}
					
					if((i-1)<scores.size()) {
						score.add(new JLabel(gridSize.get(i-1)+"x"+gridSize.get(i-1)));
					}
					else {
						score.add(new JLabel(""));
					}
				}
				//score.add(new JLabel("SCORE"));
				/*for(int i=1; i<=10; i++) {
					if(i<scores.size()-1) {
						score.add(new JLabel(scores.get(i)));
					}
					else {
						score.add(new JLabel(""));
					}
					
				}*/
				//score.add(new JLabel("GRID SIZE"));
				/*for(int i=1; i<=11; i++) {
					if(i<scores.size()-1) {
						score.add(new JLabel(gridSize.get(i)+"x"+gridSize.get(i)));
					}
					else {
						score.add(new JLabel(""));
					}
					
				}*/
				
			}
		});
		
		buttons.add(highscore);
		buttons.add(play);
		buttons.add(settings);
		
		ImageIcon ic = new ImageIcon(new ImageIcon(getClass().getResource("snake.jpg")).getImage());
		JLabel l = new JLabel(ic);
		frame.add(BorderLayout.NORTH, l);
		frame.add(BorderLayout.CENTER, buttons);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void getScores() 
	throws FileNotFoundException {
		Scanner s = new Scanner(new File("scores.txt"));
		
		while(s.hasNextLine()) {
			scores.add(s.nextLine());
			gridSize.add(s.nextLine());
		}
		s.close();
	}
	
}
