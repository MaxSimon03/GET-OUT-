package zombie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class kid {
	//declaring all the varibles for the kid class
	private boolean doghit = true;
	private ImageIcon imgKid, imgLaughingDog;
	private int ypo, xpo, dir, width, height, seconds;
	public static final int EAST = 0;
	public static final int WEST = 1;

	
	//contructor for the kid 
	public kid() {
		xpo = 0;
		ypo = 0;
		dir = 6;
		imgKid = new ImageIcon("images/k.gif");
		imgLaughingDog = new ImageIcon("images/dog.gif");
		width = imgKid.getIconWidth();
		height = imgKid.getIconHeight();

		doghit = false;

	}
	// method that gets the height and returns the heigth 
	public int getHeight() {
		return height;
	}
	//method that get the width and returns the width 
	public int getWidth() {
		return width;
	}


	//method that draws the kid 
	public void drawKid(Graphics2D p2) {

		p2.drawImage(imgKid.getImage(), xpo, ypo, width, height, null);
	}
	

	//method that moves the kid 
	public void move() {
		xpo += dir;
		ypo += 0;
	}

	//method that moves the kid xpixels 
	public void move(int xpixels) {
		
		dir = xpixels;
		xpo += dir;
	}
	//method that moves the kid xpixels and ypixels 
	public void move(int xpixels, int ypixels) {
		dir = xpixels;
		dir = ypixels;
		xpo += dir;
		ypo += dir;
	}
	//method that sets the x 
	public void setX(int x) {
		xpo = x;
	}
	//method that gets the x direction and returns the direction
	public int getxDirection() {
		return dir;
	}
	//method that set direction adjusted the image of the kid
	public void setDirection(int xd) {
		dir = xd;
		if (dir == EAST) {
			imgKid.setImage(new ImageIcon("images/k.gif").getImage());
			dir = 6;
		}
		else {
			imgKid.setImage(new ImageIcon("images/k2.gif").getImage());
			dir = -6;
		}
	}
	//get coordinates of the kid 
	public boolean checkHitd(int x, int y) {
		return x > this.xpo && x < this.xpo + this.width && y > this.ypo && y < this.ypo + this.height;
	}
	




	

	//set the location of the kid
	public void setLocation(int x, int y) {
		xpo = x;
		ypo = y;
	}

	//get the xpostion and returns x postion
	public int getX() {
		return xpo;
	}

	//gets the ypostion and returns y postion
	public int getY() {
		return ypo;
	}


	
}
