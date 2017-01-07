// Matt Fishman
// Partner: Matthew Cucuzza
// SER 210
// Version 1.0 
// Main method to fly the spaceship. Creates the JFrame for the panel to reside on. 

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class FlyShip extends JFrame {

	public FlyShip(String title) {
		super(title);
		this.add(new ShipPanel(),BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		new FlyShip("Click the Spaceships!");
	}

}
