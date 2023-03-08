
/*
* Name: Chih-Yu Huang
* Student number: 22209269
*
* */


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


	public static JFrame frame = new JFrame("Undersea Rescue"); // title of the game


	// MVC
	private static Model model1 = new Model();
	private static Viewer view1 = new Viewer(model1, model1.getNumPlayer());
	private KeyListener Controller =new Controller();

	// additional model and view
	private static GameModel model2 = new GameModel();
	private static GameOverView view2 = new GameOverView(model2);
	private static WinGameView view3 = new WinGameView(model2);

	// sound class
	private Sound sound = new Sound();



	private static int TargetFPS = 120;
	public static boolean startGame= false;
	final int width = 1164;
	final int height = 764;


	private JLabel BackgroundImageForStartMenu;

	public static JLabel score1Label;
	public static JLabel score2Label;

	public static JButton player1Button = new JButton("1 Player");;
	public static JButton player2Button = new JButton("2 Players");

	public static JButton playAgain = new JButton("Play Again");
	public static JButton exitGame = new JButton("Exit Game");


	public MainWindow() {

//		model1.setGameStart(true);

		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);


		view1.setBounds(0, 0, width, height);
		view2.setBounds(0, 0, width, height);
		view3.setBounds(0, 0, width, height);

		frame.add(view1);
		frame.add(view2);
		frame.add(view3);

		view1.setVisible(false);
		view2.setVisible(false);
		view3.setVisible(false);


		// button to choose number of players
		player1Button.setBounds(width/2 - 100, height/2 + 30, 200, 40);
		player2Button.setBounds(width/2 - 100, height/2 + 90, 200, 40);

		frame.add(player1Button);
		frame.add(player2Button);


		// set background
		File BackroundToLoad = new File("res/startGame.tiff");
		try {
			BufferedImage myPicture = ImageIO.read(BackroundToLoad);
			BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
			BackgroundImageForStartMenu.setBounds(0, 0, 1164, 764);
			frame.add(BackgroundImageForStartMenu);
		}  catch (IOException e) {
			e.printStackTrace();
		}


		// set "1 player" button action
		player1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model1.setNumPlayer(1); // set number of player to 1
				model1.setLevel(1);
				BackgroundImageForStartMenu.setVisible(false);

				view1.setVisible(true);
				view1.addKeyListener(Controller);    //adding the controller to the view1
				view1.requestFocusInWindow(); // making sure that the view1 is in focus so keyboard input will be taking in .

				startGame = true;

				player1Button.setVisible(false);
				player2Button.setVisible(false);
				view1.add(score1Label);

				model1.setLevelMusic(1);


			}
		});

		// set "2 players" button action
		player2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model1.setNumPlayer(2);
				BackgroundImageForStartMenu.setVisible(false);
				model1.setLevel(1);

				view1.setVisible(true);
				view1.addKeyListener(Controller);    //adding the controller to the view1
				view1.requestFocusInWindow(); // making sure that the view1 is in focus so keyboard input will be taking in .

				startGame = true;

				player1Button.setVisible(false);
				player2Button.setVisible(false);

				view1.add(score1Label);
				view1.add(score2Label);

				model1.setLevelMusic(1);

			}
		});


		// set score label
		score1Label = new JLabel("Player 1 score: " + model1.getScore());
		score1Label.setBounds(width/2 - 500, 0, 200, 100);
		score1Label.setFont(new Font("Sans Serif", Font.PLAIN, 24));

		score2Label = new JLabel("Player 2 score: " + model1.getScore2());
		score2Label.setBounds(width/2 + 100, 0, 200, 100);
		score2Label.setFont(new Font("Sans Serif", Font.PLAIN, 24));



		// game over
		playAgain = new JButton("Play Again");
		exitGame = new JButton("Exit Game");

		playAgain.setBounds(width/2 - 100, height/2 + 30, 200, 40);
		exitGame.setBounds(width/2 - 100, height/2 + 90, 200, 40);


		playAgain.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("play again");
				frame.setVisible(true);
				view1.setVisible(false);
				view2.setVisible(false);
				view3.setVisible(false);

				score1Label.setVisible(false);
				score2Label.setVisible(false);

				model1.clearData();

				startGame = true;

				new MainWindow();
				model1.setGameStart(true);
				model1.setLevelMusic(1);
			}
		});

		exitGame.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("exit game");
				System.exit(0);
			}
		});



	}


	public static void main(String[] args){
		MainWindow hello = new MainWindow();  //sets up environment

		gameloop();

//		while(true){
//			//not nice but remember we do just want to keep looping till the end.
//			// this could be replaced by a thread, but again we want to keep things simple
//
//			//swing has timer class to help us time this, but I'm writing my own,
//			// you can of course use the timer, but I want to set FPS and display it
//
//			int TimeBetweenFrames =  1000 / TargetFPS;
//			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;
//
//
//			//wait till next time step
//		 	while (FrameCheck > System.currentTimeMillis()){
////				Thread.sleep(FrameCheck);
////				System.out.println("slow");
//			}
//
//			 if(startGame){
//				 if(model1.isGameOver()){
////					 System.out.println("ouuuut");
//					 view1.setVisible(false);
//					 view2.setVisible(true);
//					 view3.setVisible(false);
//					 view2.updateview();
//					 view2.add(playAgain);
//					 view2.add(exitGame);
//
//					 startGame = false;
//					 model1.setGameOver(false);
//
//				 } else if (!model1.isGameOver() && model1.rescue >= 3) {
//					 view1.setVisible(false);
//					 view2.setVisible(false);
//					 view3.setVisible(true);
//					 view3.add(playAgain);
//					 view3.add(exitGame);
//					 view3.updateview();
//				 } else{
//
//					 model1.update();
//					 view1.updateview();
//				 }
//			 }
//			//UNIT test to see if frame rate matches
//		}

	}
	//Basic Model-View-Controller pattern
	public static void gameloop() {

		long drawInterval = 1000 / TargetFPS;
		long nextDrawTime = System.currentTimeMillis() + drawInterval;


		while(true){

			if(startGame){
				// game over
				 if(model1.isGameOver() || model1.getLife1() <= 0 || model1.getLife2() <= 0 ){
//					 System.out.println("ouuuut");
					 view1.setVisible(false);
					 view2.setVisible(true);
					 view3.setVisible(false);
					 view2.updateview();
					 view2.add(playAgain);
					 view2.add(exitGame);

					 view1.remove(score1Label);
					 view1.remove(score2Label);
					 model1.setLevelMusic(3);

					 startGame = false;
					 model1.setGameOver(false);

					 // win game
				 } else if (!model1.isGameOver() && model1.getRescue() >= 3 || model1.isWinGame()) {
					 view1.setVisible(false);
					 view2.setVisible(false);
					 view3.setVisible(true);
					 view3.add(playAgain);
					 view3.add(exitGame);
					 view3.updateview();
					 view1.remove(score1Label);
					 view1.remove(score2Label);

					 startGame = false;
					 model1.setGameOver(false);

				 } else{
					 if(model1.isGameStart()){
						 model1.update();
						 view1.updateview();
					 }
					 player1Button.setVisible(true);
					 player2Button.setVisible(true);
				 }
			 }


			try{
				long remainingTime = nextDrawTime - System.currentTimeMillis();

				if(remainingTime < 0){
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;

			}catch (InterruptedException e){
				e.printStackTrace();
			}
			UnitTests.CheckFrameRate(System.currentTimeMillis(), nextDrawTime, TargetFPS);
		}
	}

}

