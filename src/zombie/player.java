package zombie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class player {
	//declaring all the varibles that is used in the player class
	private boolean doghit = true;
	private ImageIcon imgKid, imgLaughingDog;
	private int ypo, xpo, dir, width, height, seconds;
	public static final int EAST = 0;
	public static final int WEST = 1;

	//crate the constructor of the player 
	public player() {
		xpo = 0;
		ypo = 0;
		dir = 3;
		imgKid = new ImageIcon("images/k.gif");
		imgLaughingDog = new ImageIcon("images/dog.gif");
		width = imgKid.getIconWidth();
		height = imgKid.getIconHeight();

		doghit = false;

	}
	
	
	//method that gets the height and returns the height
	public int getHeight() {
		return height;
	}
	//method that gets the width and returns the width 
	public int getWidth() {
		return width;
	}


	//method that draws the player 
	public void drawPlayer(Graphics2D p2) {

		p2.drawImage(imgKid.getImage(), xpo, ypo, width, height, null);
	}
	

	//method that moves the player 
	public void move() {
		xpo += dir;
		ypo += 0;
	}

	//method that moves the player with xpixels
	public void move(int xpixels) {
		
		dir = xpixels;
		xpo += dir;
	}
	//method that moves the players xpixels and ypixels 
	public void move(int xpixels, int ypixels) {
		dir = xpixels;
		dir = ypixels;
		xpo += dir;
		ypo += dir;
	}
	//method that sets the x xpostion
	public void setX(int x) {
		xpo = x;
	}
	//method that gets the x direction and returns direction 
	public int getxDirection() {
		return dir;
	}
	//method that setdirection and adjusted thr image of the player depending on whether facing left or right 
	public void setDirection(int xd) {
		dir = xd;
		if (dir == EAST) {
			imgKid.setImage(new ImageIcon("images/k.gif").getImage());
			dir = 3;
		}
		else {
			imgKid.setImage(new ImageIcon("images/k2.gif").getImage());
			dir = -3;
		}
	}

	//method that get the cordinates of the player 
	public boolean checkHitd(int x, int y) {
		return x > this.xpo && x < this.xpo + this.width && y > this.ypo && y < this.ypo + this.height;
	}
	//method that changes the image when clicked on 
	public void mouseClickedD() {
		imgKid.setImage(new ImageIcon("images/dog.gif").getImage());
		dir = 0;
	}


	//method that sets the location
	public void setLocation(int x, int y) {
		xpo = x;
		ypo = y;
	}

	//method gets x and retuns x position
	public int getX() {
		return xpo;
	}

	//method that gets y and returns y position
	public int getY() {
		return ypo;
	}
	//method that gets the coordinated of the player
	public Rectangle getBounds() {
		return new Rectangle((int)xpo, (int)ypo, (int)width,(int)height);
	}


	
}

