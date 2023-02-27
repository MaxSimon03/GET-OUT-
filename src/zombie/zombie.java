//Maxwell Simon
//CPT
//ICS
package zombie;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class zombie extends JPanel implements ActionListener {
//declare variables
	private player pl;
	private kid k;
	private zboy z;
	private zbg zg[];
	private Font f;
	private ImageIcon imgBackground, imgRules, imgMainscreen, imgCursor, getout, fence, intro;
	private static final int PANEL_WIDTH = 1152;
	private static final int PANEL_HEIGHT = 394;
	private Cursor cursor;
	private Timer zTimer, kTimer, iTimer, plTimer, zgTimer, zgHit, gameTime, pTimer;
	private boolean start = false, game = false, intro1 = false, overlaps, hit = false, endgame = false, stop = false,
			timeDone, check = false, reset = false;
	private Clip clip, bgm, typing, zse;
	private int clickx, clicky, seconds, seconds1, gSeconds;
	private int hitSeconds;
	private int score, time = 30;

	public static void main(String[] args) {

		new zombie();
	}

	public zombie() {
		//creating sound clip for intro music
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("theme.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
//setting new timers
		zTimer = new Timer(30, this);
		kTimer = new Timer(30, this);
		iTimer = new Timer(2000, this);
		pTimer = new Timer(30, this);
		zgTimer = new Timer(time, this);
		zgHit = new Timer(1000, this);
		gameTime = new Timer(2000, this);
//setting all images
		getout = new ImageIcon("images\\out.png");
		fence = new ImageIcon("images\\fence.png");
		intro = new ImageIcon("images\\start.gif");
		imgMainscreen = new ImageIcon("images\\load.png");
		imgBackground = new ImageIcon("images\\game.png");
		imgRules = new ImageIcon("images\\rules.png");
		imgCursor = new ImageIcon("images\\cursor.png");
//creating custom cursor
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(imgCursor.getImage(),
				new Point(imgCursor.getIconWidth() / 2, imgCursor.getIconHeight() / 2), "");
//declaring all characters in game and setting all locations
		pl = new player();
		pl.setLocation(180, 180);
		k = new kid();
		k.setLocation(180, 180);
		z = new zboy();
		z.setLocation(40, 160);
		zg = new zbg[8];
		for (int index = 0; index < zg.length; index++) {
			zg[index] = new zbg();
		}
		//checking if the zombies overlap eachother, if so change x pos
		for (int index = 0; index < zg.length; index++) {
			do {
				overlaps = false;
				zg[index].setLocation((int) (Math.random() * 1600 + 1800), 160);
				for (int z = zg.length - 1; z >= 0; z--) {
					if (z != index) {
						if (zg[index].getBounds().intersects(zg[z].getBounds())) {
							overlaps = true;
							break;
						}
					}
				}
			} while (overlaps == true);
		}

		setLayout(null);
		//set panel size to my custom size
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		//setting cursor to my cursor
		setCursor(cursor);
		setFocusable(true);
		requestFocus();
		//starting kid and zombie timer
		zTimer.start();
		kTimer.start();

		this.addKeyListener(new KeyListener() {
			// adding key pressed events
			public void keyPressed(KeyEvent e) {
				// if user presses enter
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//rules appear
					start = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_K) {
					//rules disapper
					start = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					//game starts and intro begins
					iTimer.start();
					gameTime.start();
					//stopping user to check rules while game is playing
					if (start == false || game == false) {
						intro1 = false;
					}
					//stopping intro music
					clip.stop();
					//setting background game music
					try {
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File("bgm.wav").getAbsoluteFile());
						bgm = AudioSystem.getClip();
						bgm.open(ais);
						bgm.loop(Clip.LOOP_CONTINUOUSLY);
						bgm.start();
						// catching errors
					} catch (UnsupportedAudioFileException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (LineUnavailableException ex) {
						ex.printStackTrace();
					}
					//start typing music
					try {
						AudioInputStream ais = AudioSystem
								.getAudioInputStream(new File("typing.wav").getAbsoluteFile());
						typing = AudioSystem.getClip();
						typing.open(ais);
						typing.loop(Clip.LOOP_CONTINUOUSLY);
						typing.start();
						// catching errors
					} catch (UnsupportedAudioFileException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (LineUnavailableException ex) {
						ex.printStackTrace();
					}
					game = true;
					if (e.getKeyCode() == KeyEvent.VK_K) {
						start = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						start = false;
					}
				}
				repaint();
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}
		});
		this.addMouseListener(new MouseListener() {
			//getting mouse cords when mouse clicks
			public void mouseClicked(MouseEvent e) {
				// gettimg mouse click coords
				clickx = e.getX();
				clicky = e.getY();
				//checking if zombie is clicked
				for (int index = 0; index < zg.length; index++) {
					if (zg[index].checkHitz(clickx, clicky) == true) {
						score += 250;
						check = true;
						zg[index].mouseClickedz();
						zgHit.start();
					}
				}
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
		});
//creating jframe
		JFrame frame = new JFrame();
		// declaring height, size,title,location of jframe
		frame.setContentPane(this);
		frame.setTitle("GET OUT! by Max");
		frame.setSize(PANEL_WIDTH + 6, PANEL_HEIGHT + 29);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(false);
		frame.setVisible(true);

		System.out.println(this.getWidth() + ", " + this.getHeight());
		System.out.println(frame.getWidth() + ", " + frame.getHeight());
		for (int index = 0; index < zg.length; index++) {
			while (zg[index].getBounds().intersects(zg[index].getBounds())) {
				if (zg[index].getBounds().intersects(zg[index].getBounds())) {
					zg[index].setLocation((int) (Math.random() * 1600 + 1800), 160);
				}
			}
		}
	}

	public void paintComponent(Graphics p) {

		super.paintComponent(p);
		Graphics2D p2 = (Graphics2D) p;
		if (start == false) {

			p2.setColor(new Color(0, 0, 0));
//boots games main intro screen
			if (game == false) {
				p2.drawImage(imgMainscreen.getImage(), 0, 0, this);
				k.drawKid(p2);
				z.drawZboy(p2);
				p2.drawImage(fence.getImage(), 0, 0, this);
				p2.drawImage(getout.getImage(), 0, 0, this);
			}
		}
		//boots rules
		if (start == true) {
			p2.drawImage(imgRules.getImage(), 0, 0, this);
			p2.setColor(new Color(0, 0, 0));
		}
		//starts intro
		if (game == true) {
			if (intro1 == false) {
				p2.drawImage(intro.getImage(), 0, 0, this);
			}
			//when intro is done start game
			if (intro1 == true) {
				//start player and zombie timer
				zgTimer.start();
				pTimer.start();
				//creating font timer
				Font f = null;
				try {
					f = Font.createFont(Font.TRUETYPE_FONT, new File("Monstorm.otf")).deriveFont(24f);
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					ge.registerFont(f);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (FontFormatException e) {
					e.printStackTrace();
				}
				//setting font and declaring font as f
				p2.setFont(new Font("Monstorm", Font.BOLD, 36));
				p2.setFont(f);
				//setting font colour
				p2.setColor(new Color(255, 255, 255));
				//declaring bg img
				p2.drawImage(imgBackground.getImage(), 0, 0, this);
				//drawing zombie array
				for (int index = 0; index < zg.length; index++) {
					zg[index].drawZboyG(p2);
				}
				//drawing players
				pl.drawPlayer(p2);
				//drawing fence
				p2.drawImage(fence.getImage(), 0, 0, this);
				//outputting score
				p2.drawString("SCORE: " + score, 500, PANEL_HEIGHT - 50);
			}

		}

	}

	public void actionPerformed(ActionEvent e) {
		//if users wants to play again reset varaibales
		if (reset == true) {
			score = 0;
			for (int index = 0; index < zg.length; index++) {
				do {
					overlaps = false;
					zg[index].setLocation((int) (Math.random() * 1600 + 1800), 160);
					for (int z = zg.length - 1; z >= 0; z--) {
						if (z != index) {
							if (zg[index].getBounds().intersects(zg[z].getBounds())) {
								overlaps = true;
								break;
							}
						}
					}
				} while (overlaps == true);
			}
		}
			if (e.getSource() == pTimer) {
				//move player
				pl.move();
				//if player hits right boundary
				if (pl.getX() + pl.getWidth() > 200) {
					pl.setX(200 - pl.getWidth());
					pl.setDirection(player.WEST);

				}
				//if player hits right boundary
				if (pl.getX() + pl.getWidth() < 100) {
					pl.setX(100 - pl.getWidth());
					pl.setDirection(player.EAST);

				}
			}
			if (e.getSource() == zTimer) {
				//move zombie
				z.move();
				//if zombie hits right boundary
				if (z.getX() + z.getWidth() > 1600) {
					z.setX(1600 - z.getWidth());
					z.setDirection(zboy.WEST);

				}
				//if zombie hits left boundary
				if (z.getX() + z.getWidth() < -400) {
					z.setX(-400 - z.getWidth());
					z.setDirection(zboy.EAST);

				}
			}
			if (e.getSource() == kTimer) {
				//move intro kid
				k.move();
				//if kid hits right boundary
				if (k.getX() + k.getWidth() > 1600) {
					k.setX(1600 - k.getWidth());
					k.setDirection(kid.WEST);

				}
				//if kid hits left boundary
				if (k.getX() + k.getWidth() < -400) {
					k.setX(-400 - k.getWidth());
					k.setDirection(kid.EAST);

				}
			}
			//creating timer to stop intro after  seconds
			if (e.getSource() == iTimer) {
				seconds++;
				System.out.println(seconds);
				//if intro timer is 8 sec then stop intro and start game music
				if (seconds == 8) {
					iTimer.stop();
					typing.stop();
					intro1 = true;
					try {
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File("zse.wav").getAbsoluteFile());
						zse = AudioSystem.getClip();
						zse.open(ais);
						zse.loop(Clip.LOOP_CONTINUOUSLY);
						zse.start();
						// catching errors
					} catch (UnsupportedAudioFileException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (LineUnavailableException ex) {
						ex.printStackTrace();
					}
					if (seconds > 8) {
						iTimer.stop();
						seconds = 0;
					}
				}
			}
			//moving in game zombies
			if (e.getSource() == zgTimer) {
				seconds1++;
				for (int index = 0; index < zg.length; index++) {
					zg[index].move();
				}
				if (seconds1 == 1) {
					zgTimer.stop();
				}

			}
			if (e.getSource() == zgHit) {
				//after zombie is clicked, make gif last only 1 sec
				hitSeconds++;
				if (hitSeconds == 1) {
					for (int index = 0; index < zg.length; index++) {
						if (zg[index].isDead()) {
							//reset zombie when 1 second is done
							zg[index].resetz();
							check = false;
							//check if they overlap when setting new location
							do {
								overlaps = false;
								zg[index].setLocation((int) (Math.random() * 1600 + 1800), 160);
								for (int z = zg.length - 1; z >= 0; z--) {
									if (z != index) {
										if (zg[index].getBounds().intersects(zg[z].getBounds())) {
											overlaps = true;
											break;
										}
									}
								}
							} while (overlaps == true);
						}
						//reseting hit timer
						hitSeconds = 0;
						zgHit.stop();
					}
				}
			}
//checking how long game lasts
			if (e.getSource() == gameTime) {
				gSeconds++;
				System.out.println(gSeconds);
				if (gSeconds >= 15) {
					time = 25;
				}
				if (gSeconds >= 30) {
					time = 20;
				}
				if (gSeconds >= 45) {
					time = 15;
				}
				if (gSeconds >= 65) {
					time = 10;
				}
			}// if zombie intersects with kid end game
			if (check == false) {
				for (int index = 0; index < zg.length; index++) {
					if (zg[index].getBounds().intersects(pl.getBounds())) {
						//reset all variables
						gameTime.stop();
						reset = true;
						zgTimer.stop();
						//tell user hes dead and ask if he wants to play again
						int playAgain = JOptionPane.showConfirmDialog(null,
								"YOU DIED!\nYour final score is " + score + ".\n" + "Would you like to play again?",
								"YOU DIED!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
								new ImageIcon("images\\end.gif"));
						//if yes reset game and all varibales
						if (playAgain == JOptionPane.YES_OPTION) {
							game = true;
							reset = false;
							gSeconds = 0;
							intro1 = true;

						} else {
							//close game
							System.exit(0);
						}
					}
				}
			}
		//repaint
		repaint();

	}
}
