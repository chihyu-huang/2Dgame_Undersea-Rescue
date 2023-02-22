import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.GameObject;



public class Viewer extends JPanel {
	private long CurrentAnimationTime = 0;
	
	Model gameworld = new Model();
	 
	public Viewer(Model World) {
		this.gameworld = World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		this.repaint();
		// TODO Auto-generated method stub
	}
	
	
	public void paintComponent(Graphics g2) {
		
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;

		CurrentAnimationTime++; // runs animation time step
//		System.out.println(CurrentAnimationTime);
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();
		
		//Draw background 
		drawBackground(g);
		
		//Draw player
		drawPlayer(x, y, width, height, texture,g);
		  
		//Draw Bullets 
		// change back 
		gameworld.getBullets().forEach((temp) -> {
			drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(),
					temp.getWidth(), temp.getHeight(), temp.getTexture(), g);
		}); 
		
		//Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> {
			drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);
	    }); 
	}

	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The sprite is 32x32 pixel wide and 4 of them are placed together, so we need to grab a different one each time
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31
			int currentPositionInAnimation = ((int) (CurrentAnimationTime % 12) / 3  * 22);
			//slows down animation so every 10 frames we get another frame so every 100ms
			g.drawImage(myImage, x, y, x+width, y+height,
					currentPositionInAnimation, 0, currentPositionInAnimation+21, 22, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime % 20) / 5 )) * 18;
			 g.drawImage(myImage, x,y, x+width, y+height,
					 	0 , 0, currentPositionInAnimation + 17, 13, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The sprite is 32x32 pixel wide and 4 of them are placed together, so we need to grab a different one each time
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime % 12) / 3)) * 20; //slows down animation so every 10 frames we get another frame so every 100ms
			g.drawImage(myImage, x, y, x+width, y+height, currentPositionInAnimation ,
						0, currentPositionInAnimation + 19, 16, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
	}

	private void drawBackground(Graphics g) {
//		File TextureToLoad = new File("res/spacebackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		File TextureToLoad = new File("res/Background.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, 0,0, 1164, 764,
					0 , 0, 1164, 764, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

