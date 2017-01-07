// Matt Fishman
// Partner: Matthew Cucuzza
// SER 210
// Version 1.0 
// Creates a panel in which a ship is displayed and painted 

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer; 

public class ShipPanel extends javax.swing.JPanel {

	// Variables 
	private MoveableShape shape; 
	private final int DELAY = 50; 
	private static final int SHIP_WIDTH = 125;
	private static final int PANEL_WIDTH = 700;
	private static final int PANEL_HEIGHT = 500;
	private boolean gameOver = false, hit = false, start = false, pause = false; 
	private int score = 0, counter = 0, time = 2, lives = 10, index;
	
	private double tickTime = 0, speed = 1;
	private ArrayList <MoveableShape> ships = new ArrayList<MoveableShape>();
	Random rand = new Random(); 
	Point mousePoint; 
	
	// Constructor creating the JPanel 
	public ShipPanel() { 
		super(); 
		ShipPanel shipPanel = this;
		setLayout(new BorderLayout());
		this.setBackground(java.awt.Color.white); 
		this.setPreferredSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		//creates the buttons on the bottom of the screen and score
		JPanel controlPanel = new JPanel();
		GridLayout controlGrid = new GridLayout(0,2);
		controlPanel.setLayout(controlGrid);
		
		JButton startButton = new JButton("Start");
		JButton pauseButton = new JButton("Pause");
		JLabel scoreLabel = new JLabel("Score: " + score);
		JLabel livesLabel = new JLabel("Lives: " + lives);

		controlPanel.add(startButton);
		controlPanel.add(pauseButton);
		controlPanel.add(scoreLabel);
		controlPanel.add(livesLabel);
		
		this.add(controlPanel, BorderLayout.SOUTH);	
		
		// Creates the two spaceship objects 
		addShips();
		// Creates the timer and how the ship will translate across the screen 
		Timer t = new Timer (DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (int i = time; time > 0; i--) {
					
					for (MoveableShape s: ships){
					s.translate(speed, speed);
					repaint();
					}
					time--;
				}
				time = 2;
				if (tickTime > 2.0){
					counter = 0;
					addShips();//adds 2 ships every 2 seconds
					tickTime = 0;
					
				}
				if(gameOver == true){
					time = 0;
					repaint();
					
				}
				tickTime +=0.05;
			}
			
		});
		
		
		this.addMouseListener(new MouseListener(){

			public void mousePressed(MouseEvent e) {
				if(gameOver == false && start == true){
				for (MoveableShape s: ships) { 
					mousePoint = e.getPoint(); 
					if (s.contains(e.getX(), e.getY())) {
						index = ships.indexOf(s);
						hit = true; 
						/*try{
						InputStream in = new FileInputStream("boom.wave");
						AudioStream as = new AudioStream(in);         
						AudioPlayer.player.start(as);
						}
						catch(Exception es){
							System.out.println("Exception: " + es);
						}*/
					}
					
				}
				//updates lives on miss
				if (hit == false && lives > 0) {
					lives--;
					livesLabel.setText("Lives: " + lives);
					System.out.println(lives);
					
				}
				//removes the ships at game over
				else if(lives == 0){
					gameOver = true;
					for (int i = (ships.size()-1); i > -1; i--) {
						MoveableShape s = ships.get(i);
						ships.remove(i);
					}
					t.stop();
					scoreLabel.setText("Final Score: " + score);
				}
				else{ 
					//when hit adds new ships and increases speed + adds score
					score+=100;	
					System.out.println(score);
					scoreLabel.setText("Score: " + score);
					if(speed < 7) speed +=.25;
					ships.remove(index);
					counter = 1;
					addShips();
					hit = false;
			}
				}
				repaint();
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
	
	startButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			//Starts the game
			t.start();
			start = true;
			//restarts the game at game over
			if(gameOver == true){
			counter = 0;
			addShips();
			gameOver = false;
			score = 0;
			lives = 10;
			speed = 1;
			scoreLabel.setText("Score: " + score);
			livesLabel.setText("Lives: " + lives);
			
			}
		}
	});
	
	//pauses the game
	pauseButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			t.stop();
			start = false;
		}
	});
	
	} // end Constructor 
	
	// Method to paint the spaceship onto the screen 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		for(MoveableShape s: ships){
			s.draw(brush);
		}
		//draws gameover
		if(gameOver==true){
			brush.drawString("GAME OVER!", PANEL_WIDTH/2-35, PANEL_HEIGHT/2-10);
		}
	}
	
	private int randomXLocation() { 
		return rand.nextInt(PANEL_WIDTH - (SHIP_WIDTH*2));
	}
	
	private int randomYLocation() { 
		return rand.nextInt(PANEL_HEIGHT - (SHIP_WIDTH*2));
	}
	
	
	
	public void addShips(){
		for(int i = counter; i < 2; i++){
		shape = new SpaceShip(randomXLocation(), randomYLocation(), SHIP_WIDTH, this);
		ships.add(shape);
			}
		}
	
}
	
