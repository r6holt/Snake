import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import javax.swing.Timer;

public class GUI {
	int BOARD_LENGTH=12;
	int BOARD_WIDTH=12;
	
	JFrame game = new JFrame();
	JPanel board = new JPanel(new GridLayout(BOARD_LENGTH,BOARD_WIDTH));
	
	int[][] spots = new int[BOARD_LENGTH][BOARD_WIDTH];
	Snake snake = new Snake();
	int direction = -1;
	Timer timer;
	
	boolean key = false;
	boolean over = false;
	
	File file = new File("scores.txt");
	
	public GUI() {
		game.setDefaultCloseOperation(game.DISPOSE_ON_CLOSE);
		init();	
		play();
		timer = new Timer(250, new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			move();
    			key=false;
    			if(over!=true) {
    				update();
    			}
    			if(game.isVisible()==false) {
    				timer.stop();
    			}
    		}
		});
		timer.start();
	}
	
	public void init() {
		for(int i=0; i<BOARD_LENGTH; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(!(i==BOARD_LENGTH/2 && j==BOARD_WIDTH/2)) {
					spots[i][j]=0;
				}
				else {
					spots[i][j]=11;
					snake.getR().add(i);
					snake.getC().add(j);
				}
			}
		}
		addFood();
		game.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			    int keyCode = e.getKeyCode();
			    if(key==false) {
			    	key=true;
				    switch( keyCode ) { 
				        case KeyEvent.VK_UP:
				        	System.out.println("UP");
				        	direction=1;
				            // handle up 
				            break;
				        case KeyEvent.VK_DOWN:
				        	direction=3;
				            // handle down 
				            break;
				        case KeyEvent.VK_LEFT:
				        	direction=2;
				            // handle left
				            break;
				        case KeyEvent.VK_RIGHT :
				        	direction=4;
				            // handle right
				            break;
				        case KeyEvent.VK_P :
				        	if(timer.isRunning()) {
				        		timer.stop();
				        	}
				        	else {
				        		timer.start();
				        	}
				     }
			    }
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	System.out.println(game.getKeyListeners());	 
	}
	
	public void play () {
		game.add(board);
		game.setVisible(true);
		game.setSize(700,700);
		/*game.addWindowListener(new WindowAdapter() {			
      	  public void windowClosing(WindowEvent e) {
      		  
      	  }
		});*/
		
		for(int i=0; i<BOARD_LENGTH; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(spots[i][j]==0) {
					JButton jb = new JButton();
					jb.setBackground(Color.WHITE);
					board.add(jb);
				}
				else if(spots[i][j]==11) {
					JButton jb = new JButton();
					jb.setBackground(Color.GREEN);
					jb.setText(":)");
					board.add(jb);
				}
				else if(spots[i][j]==1) {
					JButton jb = new JButton();
					jb.setBackground(Color.GREEN);
					board.add(jb);
				}
				else {
					JButton jb = new JButton();
					jb.setBackground(Color.RED);
					board.add(jb);
				}
			}
		}
		game.pack();
		
	}
	
	public void update() {
		for(int i=0; i<BOARD_LENGTH; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(spots[i][j]==2) {
					
				}
				else {
				spots[i][j]=0;
				}
			}
		}
		for(int i=0; i<snake.getR
				().size(); i++) {
			int r=snake.getR().get(i);
			int c=snake.getC().get(i);
			if(spots[r][c]!=2) {
				spots[r][c]=1;
			}
		}
		int row = snake.getR().get(0);
		int col = snake.getC().get(0);
		spots[row][col]=11;
		
		board.removeAll();
		for(int i=0; i<BOARD_LENGTH; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(spots[i][j]==0) {
					JButton jb = new JButton();
					jb.setBackground(Color.WHITE);
					board.add(jb);
				}
				else if(spots[i][j]==11) {
					JButton jb = new JButton();
					jb.setBackground(Color.GREEN);
					jb.setText(":)");
					board.add(jb);
				}
				else if(spots[i][j]==1) {
					JButton jb = new JButton();
					jb.setBackground(Color.GREEN);
					board.add(jb);
				}
				else {
					JButton jb = new JButton();
					jb.setBackground(Color.RED);
					board.add(jb);
				}
			}
		}
		game.pack();
	}
	
	public void move() {
	//throws FileNotFoundException{
		int row = snake.getR().get(0);
		int col = snake.getC().get(0);
		if(direction==1) {
			snake.add(row-1, col);
		}
		else if(direction==2) {
			snake.add(row, col-1);
		}
		else if(direction==3) {
			snake.add(row+1, col);
		}
		else if(direction==4) {
			snake.add(row, col+1);
		}
		row = snake.getR().get(0);
		col = snake.getC().get(0);
		
		if(row>BOARD_LENGTH-1 || col>BOARD_WIDTH-1) {
			timer.stop();
			endGame();
		}
		else if(row<0 || col<0) {
			timer.stop();
			endGame();
		}
		else {
			if(spots[row][col]==1) {
				timer.stop();
				endGame();
			}
			if(spots[row][col]==2) {
				snake.food();
				addFood();
			}
		}
	}
	
	public void addFood() {
		int r1;
		int r2;
		r1=randomR();
		r2=randomC();
		if(spots[r1][r2]!=1 && spots[r1][r2]!=11) {
			spots[r1][r2]=2;
		}
		else {
			addFood();
		}
	}
	
	public int randomR() {
		int n;
		Random r = new Random();
		n=r.nextInt(BOARD_LENGTH);
		return n;
	}
	
	public int randomC() {
		int n;
		Random r = new Random();
		n=r.nextInt(BOARD_WIDTH);
		return n;
	}
	
	public void endGame (){
	  //throws FileNotFoundException {
		over=true;
		JFrame lose = new JFrame();
		lose.setVisible(true);
		lose.setSize(200,200);
		lose.setLayout(new GridLayout(3,1));
		
		lose.add(new JLabel("YOU LOST"));
		lose.add(new JLabel("Your Score was: "+snake.getLength()));
		JButton again = new JButton("Okay");
		
		try {
			ArrayList<Integer> tops = new ArrayList<Integer>();
			ArrayList<Integer> size = new ArrayList<Integer>();
			int score = snake.getLength();
			Scanner s = new Scanner(file);
			while(s.hasNextInt()){
				tops.add(s.nextInt());
				size.add(s.nextInt());
			}
			int place;
			if(score>tops.get(tops.size()-1)){
				place=top(tops, score);
				
				tops.add(place, score);
				size.add(place, BOARD_LENGTH);
			}
			
			PrintStream out = new PrintStream(file);
			if(tops.get(tops.size()-1)==0) {
				for(int i=0; i<tops.size()-1; i++) {
					out.println(tops.get(i));
					out.println(size.get(i));
				}
			}
			else{
				for(int i=0; i<tops.size(); i++) {
					out.println(tops.get(i));
					out.println(size.get(i));	
					}
			}	
			new GUIMenu();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		again.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lose.dispose();
				game.dispose();
			}
		});
		lose.add(again);
		lose.pack();
	}
	
	public int top(ArrayList<Integer> tops, int score) {
		for(int i=0; i<10; i++) {
			if(score>tops.get(i)){
				return (i);
			}
		}
		return 10;
	}
}
