import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.UnitTests;




public class MainWindow extends JPanel{ //implements Runnable {

	// title of the game
	private static JFrame frame = new JFrame("Submarineee");
	private static Model gameWorld = new Model();
	private static Viewer canvas = new Viewer(gameWorld, gameWorld.getNumPlayer());
	private KeyListener Controller =new Controller()  ;
	private static int TargetFPS = 100;
	private static boolean startGame= false;
	private JLabel BackgroundImageForStartMenu;

	public static JLabel score1Label;
	public static JLabel score2Label;

	public static JLabel lives1Label;
	public static JLabel lives2Label;

	final int width = 1164;
	final int height = 764;

	static int numPlayer;
	  
	public MainWindow() {
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//If exit // you can modify with your way of quitting , just is a template.
		frame.setLayout(null);
		frame.add(canvas);

		canvas.setBounds(0, 0, width, height);
//		canvas.setBackground(new Color(57,126,213));
		canvas.setVisible(false);   // this will become visible after you press the key.

//		startGameThread();
		JButton player1Button = new JButton("1 Player");
		JButton player2Button = new JButton("2 Players");
//		JButton startMenuButton = new JButton("Start Game");  // start button
//		startMenuButton.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				startMenuButton.setVisible(true);
//				BackgroundImageForStartMenu.setVisible(false);
//				canvas.setVisible(true);
//				canvas.addKeyListener(Controller);    //adding the controller to the Canvas
//				canvas.requestFocusInWindow();
//				// making sure that the Canvas is in focus so keyboard input will be taking in .
//				startGame = true;
//
//
//				score1Label = new JLabel("Player 1 score: " + gameWorld.getScore());
//				score1Label.setBounds(width/2 - 100, 0, 200, 100);
//				canvas.add(score1Label);
//
//				score2Label = new JLabel("Player 2 score: " + gameWorld.getScore2());
//				score2Label.setBounds(width/2 + 100, 0, 200, 100);
//				canvas.add(score2Label);
//
//			}
//		});

		player1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameWorld.setNumPlayer(1);
//				canvas.setNumPlayer(1);
//				numPlayer = 1;
				player1Button.setVisible(true);
				BackgroundImageForStartMenu.setVisible(false);
				canvas.setVisible(true);
				canvas.addKeyListener(Controller);    //adding the controller to the Canvas
				canvas.requestFocusInWindow();
				// making sure that the Canvas is in focus so keyboard input will be taking in .
				startGame = true;

				score1Label = new JLabel("Player 1 score: " + gameWorld.getScore());
				score1Label.setBounds(width/2 - 100, 0, 200, 100);
				canvas.add(score1Label);
			}
		});

		player2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameWorld.setNumPlayer(2);
//				canvas.setNumPlayer(2);

//				numPlayer = 2;
				player2Button.setVisible(true);
				BackgroundImageForStartMenu.setVisible(false);
				canvas.setVisible(true);
				canvas.addKeyListener(Controller);    //adding the controller to the Canvas
				canvas.requestFocusInWindow();
				// making sure that the Canvas is in focus so keyboard input will be taking in .
				startGame = true;

				score1Label = new JLabel("Player 1 score: " + gameWorld.getScore());
				score1Label.setBounds(width/2 - 100, 0, 200, 100);
				canvas.add(score1Label);

				score2Label = new JLabel("Player 2 score: " + gameWorld.getScore2());
				score2Label.setBounds(width/2 + 100, 0, 200, 100);
				canvas.add(score2Label);
			}
		});


//		System.out.println(numPlayer);

//		startMenuButton.setBounds(200, 200, 200, 40);
		player1Button.setBounds(width/2 - 100, height/2 - 30, 200, 40);
		player2Button.setBounds(width/2 - 100, height/2 + 30, 200, 40);

		//loading background image
		File BackroundToLoad = new File("res/startscreen.png");
//		File BackroundToLoad = new File("res/Background.png");
		//should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			BufferedImage myPicture = ImageIO.read(BackroundToLoad);
			BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
			BackgroundImageForStartMenu.setBounds(0, 0, 0, 0); //1164, 764);
			frame.add(BackgroundImageForStartMenu);
		}  catch (IOException e) {
			e.printStackTrace();
		}
//		frame.add(startMenuButton);
		frame.add(player1Button);
		frame.add(player2Button);
		frame.setVisible(true);


	}

	public static void main(String[] args) {
		MainWindow hello = new MainWindow();  //sets up environment 
		while(true){
			//not nice but remember we do just want to keep looping till the end.
			// this could be replaced by a thread, but again we want to keep things simple

			//swing has timer class to help us time this, but I'm writing my own,
			// you can of course use the timer, but I want to set FPS and display it
			
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;

			
			//wait till next time step 
		 	while (FrameCheck > System.currentTimeMillis()){

			}
			 if(startGame){
				 gameloop();
			 }

			 //UNIT test to see if frame rate matches
			UnitTests.CheckFrameRate(System.currentTimeMillis(), FrameCheck, TargetFPS);
			  
		}
	} 
	//Basic Model-View-Controller pattern 
	private static void gameloop() {
		// GAMELOOP
		
		// controller input  will happen on its own thread 
		// So no need to call it explicitly 
		
		// model update   
		gameWorld.gamelogic();
		// view update 
		
		canvas.updateview();
		
		//Both these calls could be setup as a thread, but we want to simplify the game logic for you.
		//score update  
//		frame.setTitle("Player 1 Score =  " + gameWorld.getScore() + "; Player 2 Score =  " + gameWorld.getScore2());




	}





//	Thread gameThread;
//
//	public void startGameThread() {
//		gameThread = new Thread(this);
//		gameThread.start();
//	}

//	@Override
//	public void run() {
//
//		double drawInterval = 1000 / TargetFPS;
//		double nextDrawTime = System.currentTimeMillis() + drawInterval;
//
//
//		while(gameThread != null){
////			1 update info
////			2 draw with the updated info
//			update();
//			repaint();
//
//			try{
//				double remainingTime = nextDrawTime - System.currentTimeMillis();
//
//				if(remainingTime < 0){
//					remainingTime = 0;
//				}
//
//				Thread.sleep((long) remainingTime);
//
//				nextDrawTime += drawInterval;
//
//			}catch (InterruptedException e){
//				e.printStackTrace();
//			}
//
//
//
//		}
//	}

//	public void update(){
//
//	}

//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//
//		Graphics2D g2 = (Graphics2D) g;
//
//		g2.setColor(Color.white);
//
//		g2.fillRect(100, 100, 32, 32);
//
//		g2.dispose();
//
//	}
}

