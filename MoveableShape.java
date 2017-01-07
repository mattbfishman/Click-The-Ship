// Matt Fishman
// Partner: Matthew Cucuzza
// SER 210
// Version 1.0 
// Interface to be implemented in SpaceShip in order to draw and translate the ship 


import java.awt.*;

public interface MoveableShape {

	// Empty method to be implemented
	void draw(Graphics2D g2);
	
	// Empty method to be implemented 
	void translate(double dx, double dy);

	boolean contains(int x, int y);

}
