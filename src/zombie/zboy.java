package zombie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class zboy {
	private boolean doghit = true;
	private ImageIcon imgZboy, imgLaughingDog;
	private int ypo, xpo, dir, width, height, seconds;
	public static final int EAST = 0;
	public static final int WEST = 1;

	public zboy() {
		xpo = 0;
		ypo = 0;
		dir = 6;
		imgZboy = new ImageIcon("images/zboy.gif");
		imgLaughingDog = new ImageIcon("images/dog.gif");
		width = imgZboy.getIconWidth();
		height = imgZboy.getIconHeight();

		doghit = false;

	}
	
	public zboy(String filename) {
		//		xpo = 5;
		//		ypo = 30;
		//		xdir = 3;
		//		ydir = 5;
		//		imgDog = new ImageIcon(filename);
		//		dogwidth = imgDog.getIconWidth();
		//		dogheight = imgDog.getIconHeight();
		//		dogColor = null;
	}
	public zboy(int x, int y) {

	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}


	public void drawZboy(Graphics2D p2) {

		p2.drawImage(imgZboy.getImage(), xpo, ypo, width, height, null);
	}
	

	public void move() {
		xpo += dir;
		ypo += 0;
	}

	
	public void move(int xpixels) {
		
		dir = xpixels;
		xpo += dir;
	}
	
	public void move(int xpixels, int ypixels) {
		dir = xpixels;
		dir = ypixels;
		xpo += dir;
		ypo += dir;
	}
	public void setX(int x) {
		xpo = x;
	}
	public int getxDirection() {
		return dir;
	}
	public void setDirection(int xd) {
		dir = xd;
		if (dir == EAST) {
			imgZboy.setImage(new ImageIcon("images/zboy.gif").getImage());
			dir = 6;
		}
		else {
			imgZboy.setImage(new ImageIcon("images/zboy2.gif").getImage());
			dir = -6;
		}
	}
	public boolean checkHitz(int x, int y) {
		return x > this.xpo && x < this.xpo + this.width && y > this.ypo && y < this.ypo + this.height;
	}
	public void mouseClickedz() {
		imgZboy.setImage(new ImageIcon("images/zkill.gif").getImage());
		dir = -7;
	}

//	public void move() {
//		xpo += xdir;
//		ypo += ydir;
//	}

//	public void move(int xpixels) {
//
//		xdir = xpixels;
//		xpo += xdir;
//	}
//	public void setSize(int width, int height) {
//		dogwidth = width;
//		dogheight = height;
//	}
//
//	public void move(int xpixels, int ypixels) {
//		xdir = xpixels;
//		ydir = ypixels;
//		xpo += xdir;
//		ypo += ydir;
//	}


	public void setImage(ImageIcon img) {

	}

	public void setPosition(int x, int y) {

	}

	public void setLocation(int x, int y) {
		xpo = x;
		ypo = y;
	}

	public int getX() {
		return xpo;
	}

	public int getY() {
		return ypo;
	}

//	public void setxDirection(int xd) {
//		xdir = xd;
//	}
//
//	public void setyDirection(int yd) {
//		ydir = yd;
//	}
//
//	public int getxDirection() {
//		return xdir;
//	}
//
//	public int getyDirection() {
//		return ydir;
//	}
//
//
//	public int getWidth() {
//		return dogwidth;
//	}
//
//	public void setX(int x) {
//		xpo = x;
//	}
//
//	public void setY(int y) {
//		ypo = y;
//	}
	
}
