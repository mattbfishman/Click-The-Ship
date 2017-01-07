// Matt Fishman
// Partner: Matthew Cucuzza
// SER 210
// Version 1.0 
// Creates a spaceship object which can be moved and drawn 


import java.awt.*; 
import java.awt.geom.*;
import java.util.*;

public class SpaceShip implements MoveableShape {

	// Variables
	private double x, y; 
	private int width;
	private Color color; 
	private int r = 250, g= 250, b = 250;
	private boolean goingLeft = false;
	private boolean goingUp = false; 
	Random rand = new Random(); 
	
	// Objects for spaceship 
	Ellipse2D.Double body; 
	Rectangle2D.Double anchor; 
	Ellipse2D.Double rightTail;
	Ellipse2D.Double leftTail;
	Ellipse2D.Double window; 
	ShipPanel panel;
	
	// Constructor, creating the objects for the ship and assigning a random color
	public SpaceShip(int x, int y, int width, ShipPanel panel) {
		this.x = x; 
		this.y = y; 
		this.width = width; 
		this.panel = panel;
		
		body = new Ellipse2D.Double(x, y + width / 5, width / 5 * 2.5, width / 5 * 5);
		window = new Ellipse2D.Double(x + width / 5 , y + width / 3, width / 9, width / 9);
		anchor = new Rectangle2D.Double(x + width / 5, y + (width - 30), width / 9, width / 2);
		rightTail = new Ellipse2D.Double(x + width / 3, y + width / 5 * 3, width / 5, width / 3 * 2);
		leftTail = new Ellipse2D.Double(x + (width / 3) - 45, y + width / 5 * 3, width / 5, width / 3 * 2);
		color = randomColor() ;
	}
	
	
	// Method to move the ship around the screen 
	public void translate(double dx, double dy) {
		// if the spaceship is moving to the right translate this way 
		if (goingLeft == false) { 
			x = (x+dx);
		}
		
		// if the spaceship is moving to the right translate this way 
		if (goingUp == false) {
			y = (y+dy);
		}
		
		// if the spaceship hits the right border of the panel, go left 
		if (x >= (panel.getWidth() - body.width)) {
			goingLeft = true;
		}  
		
		// if goingLeft is true translate to the left 
		if (goingLeft == true) {
			x = (x-dx); 
		}
		
		/* if the spaceship hits the left border of the screen 
		 *set goingLeft equal to false */
		if (x <= 0) {
			goingLeft = false;
		}
		
		// if the spaceship hits the bottom of the panel, set goingUp to true 
		if (y >= (panel.getHeight() - anchor.height*5/2)) {
			goingUp = true;
		}
		
		// if translating up use this translation 
		if (goingUp == true) {
			y = (y-dy);
		}
		
		// if the ship hits the top of the screen change status to move down 
		if (y <= 0-width/5) {
			goingUp = false; 
		}
		
		// Create the frames surrounding the spaceship objects 
		body.setFrame(x, y + width / 5, width / 5 * 2.5, width / 5 * 5);
		window.setFrame(x + width / 5 , y + width / 3, width / 9, width / 9); 
		anchor.setFrame(x + width / 5, y + (width - 30), width / 9, width / 2);
		rightTail.setFrame(x + width / 3, y + width / 5 * 3, width / 5, width / 3 * 2);
		leftTail.setFrame(x + (width / 3) - 45, y + width / 5 * 3, width / 5, width / 3 * 2);
		
		
	}
	
	// Method to display a random color for the spaceship 
	private Color randomColor() {
		return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)); 
	}
	
	public boolean contains(int x, int y) {
		return body.contains(x, y);
	}
	
	// Method to paint the spaceship with color 
	public void draw(Graphics2D g) {
		g.setColor(Color.gray);
		g.draw(anchor);
		g.fill(anchor);
		
		g.setColor(Color.red);
		g.draw(rightTail);
		g.draw(leftTail);
		g.fill(rightTail);
		g.fill(leftTail);
		
		g.setColor(color); 
		g.draw(body);
		g.fill(body);
		
		g.setColor(Color.yellow);
		g.draw(window);
		g.fill(window);
	}
} // end class 
