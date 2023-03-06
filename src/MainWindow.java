import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.UnitTests;




public class MainWindow extends JPanel{ //implements Runnable {

	Sound sound = new Sound();

	// title of the game
	private static JFrame frame = new JFrame("Submarineee");
	private static Model model1 = new Model();
	private static GameModel model2 = new GameModel();
	private static Viewer view1 = new Viewer(model1, model1.getNumPlayer());
	private static GameOverView view2 = new GameOverView(model2);
	private static WinGameView view3 = new WinGameView(model2);
	private KeyListener Controller =new Controller();
	private static int TargetFPS = 100;
	private static boolean startGame= false;
	private JLabel BackgroundImageForStartMenu;

	public static JLabel score1Label;
	public static JLabel score2Label;


	final int width = 1164;
	final int height = 764;


	public static JButton player1Button;
	public static JButton player2Button;

	public static JButton playAgain = new JButton("Play Again");
	public static JButton exitGame = new JButton("Exit Game");


	public MainWindow() {


		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//If exit // you can modify with your way of quitting , just is a template.
		frame.setLayout(null);
		frame.add(view1);
		frame.add(view2);
		frame.add(view3);


		view1.setBounds(0, 0, width, height);
		view2.setBounds(0, 0, width, height);

		view1.setVisible(false);
		view2.setVisible(false);
		view3.setVisible(false);

//		startGameThread();

		player1Button = new JButton("1 Player");
		player2Button = new JButton("2 Players");

		player1Button.setBounds(width/2 - 100, height/2 + 30, 200, 40);
		player2Button.setBounds(width/2 - 100, height/2 + 90, 200, 40);

		frame.add(player1Button);
		frame.add(player2Button);


		//loading background image
		File BackroundToLoad = new File("res/startGame.tiff");
//		File BackroundToLoad = new File("res/Background.png");
		//should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			BufferedImage myPicture = ImageIO.read(BackroundToLoad);
			BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
			BackgroundImageForStartMenu.setBounds(0, 0, 1164, 764);
			frame.add(BackgroundImageForStartMenu);
		}  catch (IOException e) {
			e.printStackTrace();
		}


		frame.setVisible(true);


		score1Label = new JLabel("Player 1 score: " + model1.getScore());
		score1Label.setBounds(width/2 - 500, 0, 200, 100);
		score1Label.setFont(new Font("Sans Serif", Font.PLAIN, 24));


		score2Label = new JLabel("Player 2 score: " + model1.getScore2());
		score2Label.setBounds(width/2 + 100, 0, 200, 100);
		score2Label.setFont(new Font("Sans Serif", Font.PLAIN, 24));


		// start menu choose numbers of players
		player1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model1.setNumPlayer(1);
				player1Button.setVisible(true);
				BackgroundImageForStartMenu.setVisible(false);
				view1.setVisible(true);
				view1.addKeyListener(Controller);    //adding the controller to the view1
				view1.requestFocusInWindow();
				// making sure that the view1 is in focus so keyboard input will be taking in .
				startGame = true;


				view1.add(score1Label);

//				lives1Label = new JLabel("Player 1 lives: " + model1.getLife1());
//				lives1Label.setBounds(width/2 - 100, 500, 200, 100);
//				view1.add(lives1Label);
			}
		});

		player2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model1.setNumPlayer(2);

				player2Button.setVisible(true);
				BackgroundImageForStartMenu.setVisible(false);
				view1.setVisible(true);
				view1.addKeyListener(Controller);    //adding the controller to the view1
				view1.requestFocusInWindow();
				// making sure that the view1 is in focus so keyboard input will be taking in .
				startGame = true;

				view1.add(score1Label);

				view1.add(score2Label);

//				lives1Label = new JLabel("Player 1 lives: " + model1.getLife1());
//				lives1Label.setBounds(width/2 - 100, 50, 200, 100);
//				view1.add(lives1Label);
//
//				lives2Label = new JLabel("Player 2 lives: " + model1.getLife2());
//				lives2Label.setBounds(width/2 - 100, 50, 200, 100);
//				view1.add(lives2Label);
			}
		});


		// game over

		playAgain = new JButton("Play Again");
		exitGame = new JButton("Exit Game");

		playAgain.setBounds(width/2 - 100, height/2 + 30, 200, 40);
		exitGame.setBounds(width/2 - 100, height/2 + 90, 200, 40);

//		playAgain.setVisible(false);

//			frame.add(playAgain);
//			frame.add(exitGame);

		playAgain.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("play again");
				frame.setVisible(true);
				view2.setVisible(false);

				score1Label.setVisible(false);
				score2Label.setVisible(false);

				model1.clearData();

			}
		});

		exitGame.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("exit game");
			}
		});

	}


	public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
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
				 if(model1.isGameOver()){
//					 System.out.println("ouuuut");
					 view1.setVisible(false);
					 view2.setVisible(true);
					 view3.setVisible(false);
					 view2.updateview();

					 view2.add(playAgain);
					 view2.add(exitGame);
				 } else if (!model1.isGameOver() && model1.rescue >= 3) {
					 view1.setVisible(false);
					 view2.setVisible(false);
					 view3.setVisible(true);
					 view3.updateview();
				 } else{
					 gameloop();
				 }
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
		model1.update();

		// view update
		view1.updateview();




		
		//Both these calls could be setup as a thread, but we want to simplify the game logic for you.
		//score update  
//		frame.setTitle("Player 1 Score =  " + model1.getScore() + "; Player 2 Score =  " + model1.getScore2());




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

