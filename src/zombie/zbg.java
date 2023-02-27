package zombie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class zbg {
	private boolean isHit = false;
	private ImageIcon imgZboy, imgLaughingDog;
	private int ypo, xpo, dir, width, height, seconds;
	public static final int EAST = 0;
	public static final int WEST = 1;

	//constructor for the 
	public zbg() {
		xpo = 0;
		ypo = 0;
		dir = -6;
		imgZboy = new ImageIcon("images/zboy2.gif");
		imgLaughingDog = new ImageIcon("images/dog.gif");
		width = imgZboy.getIconWidth();
		height = imgZboy.getIconHeight();

		isHit = false;

	}
//get height 
	public int getHeight() {
		return height;
	}
	//get width
	public int getWidth() {
		return width;
	}

//draws zombie
	public void drawZboyG(Graphics2D p2) {

		p2.drawImage(imgZboy.getImage(), xpo, ypo, width, height, null);
	}
	
//moves zombie
	public void move() {
		xpo += dir;
		ypo += 0;
	}

	//move zombie
	public void move(int xpixels) {
		
		dir = xpixels;
		xpo += dir;
	}
	//move zombie
	public void move(int xpixels, int ypixels) {
		dir = xpixels;
		dir = ypixels;
		xpo += dir;
		ypo += dir;
	}
	//checks if zombie is dead
	public boolean isDead() {
		return isHit;
	}
	//set x cord
	public void setX(int x) {
		xpo = x;
	}
	//get x cord
	public int getxDirection() {
		return dir;
	}
	//set direction of zombie and changes image
	public void setDirection(int xd) {
		dir = xd;
		if (dir == EAST) {
			imgZboy.setImage(new ImageIcon("images/zboy2.gif").getImage());
			dir = 6;
		}
	}
	//checks zombies cords
	public boolean checkHitz(int x, int y) {
		return x > this.xpo && x < this.xpo + this.width && y > this.ypo && y < this.ypo + this.height;
	}
	//changes image of zombie when clicked
	public void mouseClickedz() {
		isHit = true;
		imgZboy.setImage(new ImageIcon("images/zkill.gif").getImage());
		dir = -7;
	}
	//sets zombies pic back to the original
	public void resetz() {
		//setLocation((int) (Math.random() * 1600 + 1800), 160);
		imgZboy.setImage(new ImageIcon("images/zboy2.gif").getImage());
		dir = -6;
		isHit = false;
	}


//set location of zombie
	public void setLocation(int x, int y) {
		xpo = x;
		ypo = y;
	}
//gets x cord 
	public int getX() {
		return xpo;
	}
//gets y cord
	public int getY() {
		return ypo;
	}
	// get boundaries of zombie
	public Rectangle getBounds() {
		return new Rectangle((int)xpo, (int)ypo, (int)width,(int)height);
	}

}