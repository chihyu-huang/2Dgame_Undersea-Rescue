
/*
 * Name: Chih-Yu Huang
 * Student number: 22209269
 *
 * */

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f;


public class Model {

	private Controller controller = Controller.getInstance();
	private Sound sound = new Sound();


	Random random = new Random();
	// game object
	private GameObject Player;
	private GameObject Player2;
	private CopyOnWriteArrayList<GameObject> PlayerList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> EnemiesList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> EnemiesList2 = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> AnimalsList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList2 = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> NetList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> NetList2 = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> LivesList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> LivesList2 = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> RescueList = new CopyOnWriteArrayList<GameObject>();


	// variable for objects
	float enemySpeedX = 1.0F;
	float animalSpeedY = -1.0F;
	float bulletSpeedX = -1.0F;
	float netSpeedX = 0.0F;
	float netSpeedY = 0.5F;
	float playerSpeed = 2.0F ;


	private int Score = 0;
	private int Score2 = 0;

	private int life1 = 3;
	private int life2 = 3;

	private int rescue = 0;


	private static int numPlayer;
	private static int level = 0;
	private boolean gameOver;
	private boolean gameStart = true;

	private int levelMusic = 0;


	private float player1X = 500;
	private float player1Y = 500;

	private float player2X = 500;
	private float player2Y = 200;

	private int enemySize1 = 3;
	private int enemySize2 = 4;





	public Model() {
		if(isGameStart()) {

			Player = new GameObject("res/mini_sub.png", 64, 64, new Point3f(getPlayer1X(), getPlayer1Y(), 0));
			Player2 = new GameObject("res/player.png", 64, 64, new Point3f(getPlayer2X(), getPlayer2Y(), 0));
			PlayerList.add(Player);
			PlayerList.add(Player2);


			sound.loadSound("start", "sound/start.wav");

			sound.loadSound("shoot", "sound/shoot.wav");
			sound.loadSound("hit", "sound/hit.wav");
			sound.loadSound("enemy_player", "sound/enemy_player.wav");
			sound.loadSound("saving", "sound/saving.wav");
			sound.loadSound("throw", "sound/throw.wav");
			sound.loadSound("catch", "sound/catch.wav");
			sound.loadSound("gameOverSound", "sound/gameOver.wav");
			sound.loadSound("bgm1", "sound/bgm1.wav");
			sound.loadSound("bgm2", "sound/bgm2.wav");






			//Enemies  starting with four
			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));
			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));
			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));

			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));
			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));
			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float)  random.nextInt(12) * 50 + 100), 0)));

			LivesList.add(new GameObject("res/player2.png", 48, 48, new Point3f(10, 15, 0)));
			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(60, 10, 0)));
			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(108, 10, 0)));
			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(108 + 48, 10, 0)));


			LivesList2.add(new GameObject("res/player1.png", 48, 48, new Point3f(950, 15, 0)));
			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1004, 10, 0)));
			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1052, 10, 0)));
			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1100, 10, 0)));
		}


		startSound.start();


	}

	Thread startSound = new Thread(new SoundPlayer(sound, "start"));
	Thread bgmSound1 = new Thread(new SoundPlayer(sound, "bgm1"));
	Thread bgmSound2 = new Thread(new SoundPlayer(sound, "bgm2"));
	Thread gameOverSound = new Thread(new SoundPlayer(sound, "gameOver"));


	public void music(){

		if(getLevelMusic() == 1){
			System.out.println("level 1");
			bgmSound1.start();
			setLevelMusic(100);
		} else if (getLevelMusic() == 2) {
			System.out.println("level 2");
			bgmSound2.start();
			setLevelMusic(100);
		} else if (getLevelMusic() == 3) {
			System.out.println("game over");
			gameOverSound.start();
			setLevelMusic(100);
		}
	}



	public void setting() {

//		if(isGameStart()) {
//
//
//			Player = new GameObject("res/mini_sub.png", 64, 64, new Point3f(getPlayer1X(), getPlayer1Y(), 0));
//			Player2 = new GameObject("res/player.png", 64, 64, new Point3f(getPlayer2X(), getPlayer2Y(), 0));
//			PlayerList.add(Player);
//			PlayerList.add(Player2);
//
//
//			sound.loadSound("start", "sound/start.wav");
//
//			sound.loadSound("shoot", "sound/shoot.wav");
//			sound.loadSound("hit", "sound/hit.wav");
//			sound.loadSound("enemy_player", "sound/enemy_player.wav");
//			sound.loadSound("saving", "sound/saving.wav");
//			sound.loadSound("throw", "sound/throw.wav");
//			sound.loadSound("catch", "sound/catch.wav");
//			sound.loadSound("levelUp", "sound/levelUp.wav");
//			sound.loadSound("gameOver", "sound/gameOver.wav");
//
//
//			sound.loadSound("bgm1", "sound/bgm1.wav");
//			sound.loadSound("bgm2", "sound/bgm2.wav");
//
//			sound.loadSound("death", "sound/death.wav");
//			sound.loadSound("loseLife", "sound/loselife.wav");
//
//
//			Thread startSound = new Thread(new SoundPlayer(sound, "start"));
//			Thread levelUpSound = new Thread(new SoundPlayer(sound, "levelUp"));
//
//
//			startSound.start();
//
//
//			Thread bgmSound1 = new Thread(new SoundPlayer(sound, "bgm1"));
//			Thread bgmSound2 = new Thread(new SoundPlayer(sound, "bgm2"));
//
////			bgmSound1.start();
//
//			if(getLevel() == 1){
//				System.out.println("level 1");
//				bgmSound1.start();
//			} else if (getLevel() == 2) {
//				bgmSound2.start();
//			}
//
//
//			//Enemies  starting with four
//			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//			EnemiesList.add(new GameObject("res/shark.png", 64, 64, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//
//			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//			EnemiesList2.add(new GameObject("res/jellyfish.png", 48, 48, new Point3f(0, ((float) Math.random() * 700 + 50), 0)));
//
//			LivesList.add(new GameObject("res/player2.png", 48, 48, new Point3f(10, 15, 0)));
//			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(60, 10, 0)));
//			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(108, 10, 0)));
//			LivesList.add(new GameObject("res/heart.png", 48, 48, new Point3f(108 + 48, 10, 0)));
//
//
//			LivesList2.add(new GameObject("res/player1.png", 48, 48, new Point3f(950, 15, 0)));
//			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1004, 10, 0)));
//			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1052, 10, 0)));
//			LivesList2.add(new GameObject("res/heart.png", 48, 48, new Point3f(1100, 10, 0)));
//		}
	}

	
	// This is the heart of the game , where the model takes in all the inputs ,
	// decides the outcomes and then changes the model accordingly.
	public void update() {
		playerLogic(); // Player Logic first
		enemyLogic(); // Enemy Logic next
//		bulletLogic(); // Bullets move next
//		gameLogic(); // interactions between objects
//		bulletLogic2();

		gameLogic1(EnemiesList);
		gameLogic1(EnemiesList2);
		bulletLogic(BulletList);
		bulletLogic(BulletList2);

		netLogic(NetList);
		netLogic(NetList2);
		animalLogic();

		nextLevel();
		gameOver();

		music();

	}


	// unsolved
	public void clearData(){
		PlayerList.clear();
		LivesList.clear();
		LivesList2.clear();
		EnemiesList.clear();
		EnemiesList2.clear();
		BulletList.clear();
		BulletList2.clear();
		NetList.clear();
		NetList2.clear();
		AnimalsList.clear();
		RescueList.clear();


		setLife1(3);
		setLife2(3);
		setScore(0);
		setScore2(0);
		setNumPlayer(0);
		setPlayer1X(500);
		setPlayer2X(500);
		setPlayer1Y(500);
		setPlayer2Y(200);
		setRescue(0);
		setGameOver(false);

		System.out.println("clearrrr");
		MainWindow.score1Label.setVisible(true);
		MainWindow.score2Label.setVisible(true);
		setting();
	}

//	public void playMusic(int i){
//		sound.setFile(i);
//		sound.play();
//		sound.loop();
//	}
//
//	public void stopMusic(){
//		sound.stop();
//	}
//
//	public void playSE(int i){
//		sound.setFile(i);
//		sound.play();
//	}



	private void gameLogic1(CopyOnWriteArrayList<GameObject> list) {

		Thread hitSound = new Thread(new SoundPlayer(sound, "hit"));
		Thread enemy_playerSound = new Thread(new SoundPlayer(sound, "enemy_player"));

		Thread savingSound = new Thread(new SoundPlayer(sound, "saving"));

		Thread catchSound = new Thread(new SoundPlayer(sound, "catch"));

		Thread gameOverSound = new Thread(new SoundPlayer(sound, "gameOver"));


		for (GameObject temp : list) {

			// bullet hits enemy
			for (GameObject Bullet : BulletList) {
				if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
					list.remove(temp);
					BulletList.remove(Bullet);
					Score++;
//					System.out.println("1: " + Score);
					hitSound.start();
				}
			}

			// net catches enemy
			for (GameObject Net : NetList) {
				if ( Math.abs(temp.getCentre().getX()- Net.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Net.getCentre().getY()) < temp.getHeight()) {
					list.remove(temp);
					NetList.remove(Net);
					Score = Score + 2;
//					System.out.println("1: +2  		 " + Score );
					catchSound.start();
				}
			}

			// enemy eats player
			if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
					&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight()){
				list.remove(temp);
				life1--;
				for(int i = getLife1(); i < LivesList.size() - 1; i++){
					LivesList.remove(i + 1);
				}
				if (life1 <= 0){
					setGameOver(true);
					setGameStart(false);
				}
				enemy_playerSound.start();
			}

			// enemy eats animal -> gameOver sound
			for (GameObject Animal : AnimalsList) {
				if ( Math.abs(temp.getCentre().getX()- Animal.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Animal.getCentre().getY()) < temp.getHeight()) {
					list.remove(temp);
					AnimalsList.remove(Animal);
					setGameOver(true);
					gameOverSound.start();
				}
			}

			// only when there are 2 players
			if(getNumPlayer() == 2) {

				// bullet hits enemy
				for (GameObject Bullet : BulletList2) {
					if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
							&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
						list.remove(temp);
						BulletList2.remove(Bullet);
						Score2++;
						System.out.println("2: " + Score2);
						hitSound.start();
					}
				}

				// net catches enemy
				for (GameObject Net : NetList2) {
					if ( Math.abs(temp.getCentre().getX()- Net.getCentre().getX())< temp.getWidth()
							&& Math.abs(temp.getCentre().getY()- Net.getCentre().getY()) < temp.getHeight()) {
						list.remove(temp);
						NetList2.remove(Net);
						Score2 = Score2 + 2;
						System.out.println("1: +2  		 " + Score );
						catchSound.start();
					}
				}

				// enemy eats player2
				if(Math.abs(temp.getCentre().getX()- Player2.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Player2.getCentre().getY()) < temp.getHeight()){
					list.remove(temp);
					life2 = life2 - 1;
					for(int i = getLife2(); i < LivesList2.size() - 1; i++){
						LivesList2.remove(i + 1);
					}
					if (life2 <= 0){
						setGameOver(true);
						setGameStart(false);
					}
					enemy_playerSound.start();

				}
			}
		}


		// player saves animal
		for (GameObject temp : AnimalsList) {
			if (getNumPlayer() == 1) {
				if (Math.abs(temp.getCentre().getX() - Player.getCentre().getX()) < temp.getWidth()
						&& Math.abs(temp.getCentre().getY() - Player.getCentre().getY()) < temp.getHeight()) {
					AnimalsList.remove(temp);
					rescue++;
//					System.out.println("r: " + rescue);
					for (int i = 0; i < rescue; i++) {
						RescueList.add(new GameObject("res/fox.png", 30, 30, new Point3f(500 + 30 * i, 50, 0)));
					}
					savingSound.start();
				}
			}
			if (getNumPlayer() == 2) {
				if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight() ||
						Math.abs(temp.getCentre().getX()- Player2.getCentre().getX())< temp.getWidth()
								&& Math.abs(temp.getCentre().getY()- Player2.getCentre().getY()) < temp.getHeight()){
					AnimalsList.remove(temp);
					rescue ++;
					System.out.println("r: " + rescue);
					for (int i = 0; i < rescue; i++) {
						RescueList.add(new GameObject("res/fox.png", 30, 30, new Point3f(500 + 30 * i, 50, 0)));
					}
					savingSound.start();
				}
			}
		}

	}


//	private void gameLogic() {
//		// this is a way to increment across the array list data structure
//
//		// see if they hit anything
//		// using enhanced for-loop style as it makes it alot easier both code wise and reading wise too
//
//
//		// if bullet hits the enemy, they're both eliminated from the list and add 1 score at the same time
//		for (GameObject temp : EnemiesList) {
//			for (GameObject Bullet : BulletList) {
//				if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
//					&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
//					EnemiesList.remove(temp);
//					BulletList.remove(Bullet);
//					Score++;
//					System.out.println("1: " + Score);
//				}
//			}
//
//			for (GameObject Net : NetList) {
//				if ( Math.abs(temp.getCentre().getX()- Net.getCentre().getX())< temp.getWidth()
//						&& Math.abs(temp.getCentre().getY()- Net.getCentre().getY()) < temp.getHeight()) {
//					EnemiesList.remove(temp);
//					NetList.remove(Net);
//					Score = Score + 2;
//					System.out.println("1: +2  		 " + Score );
//				}
//			}
//
//			// when player 1 collides with enemies
//			if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
//					&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight()){
//				EnemiesList.remove(temp);
//				life1 = life1 - 1;
//
//				for(int i = getLife1(); i < LivesList.size() - 1; i++){
//					LivesList.remove(i + 1);
//				}
//
////				if(getLife1() == 2){
////					LivesList.remove(2);
////				} else if (getLife1() == 1){
////					LivesList.remove(1);
////				} else if (getLife1() == 0){
////					LivesList.remove(getLife1());
////				}
//				System.out.println("1 life: " + life1);
//			}
//
//			if(getNumPlayer() == 2) {
//
//
//				for (GameObject Bullet : BulletList2) {
//					if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
//							&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
//						EnemiesList.remove(temp);
//						BulletList2.remove(Bullet);
//						Score2++;
//						System.out.println("2: " + Score2);
//					}
//				}
//
//				for (GameObject Net : NetList2) {
//					if ( Math.abs(temp.getCentre().getX()- Net.getCentre().getX())< temp.getWidth()
//							&& Math.abs(temp.getCentre().getY()- Net.getCentre().getY()) < temp.getHeight()) {
//						EnemiesList.remove(temp);
//						NetList2.remove(Net);
//						Score2 = Score2 + 2;
//						System.out.println("1: +2  		 " + Score );
//					}
//				}
//
//				if(Math.abs(temp.getCentre().getX()- Player2.getCentre().getX())< temp.getWidth()
//						&& Math.abs(temp.getCentre().getY()- Player2.getCentre().getY()) < temp.getHeight()){
//					EnemiesList.remove(temp);
//					life2 = life2 - 1;
//
//					for(int i = getLife2(); i < LivesList2.size() - 1; i++){
//						LivesList2.remove(i + 1);
//					}
//					System.out.println("2 life: " + life2);
//				}
//			}
//		}
//
//		for (GameObject temp : AnimalsList) {
//			if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
//					&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight()){
//				AnimalsList.remove(temp);
//				rescue ++;
//				System.out.println("r: " + rescue);
//				for(int i = 0; i < rescue; i ++){
//					RescueList.add(new GameObject("res/fox.png", 30, 30, new Point3f(500 + 30*i, 50 ,0) ));
//				}
//			}
//			if (getNumPlayer() == 2) {
//				if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
//						&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight() ||
//					Math.abs(temp.getCentre().getX()- Player2.getCentre().getX())< temp.getWidth()
//						&& Math.abs(temp.getCentre().getY()- Player2.getCentre().getY()) < temp.getHeight()){
//					AnimalsList.remove(temp);
//					rescue ++;
//					System.out.println("r: " + rescue);
//					RescueList.add(new GameObject("res/fox.png", 48, 48, new Point3f(500, 50 ,0) ));
//				}
//			}
//		}
//
//	}

	private void enemyLogic() {
		// TODO Auto-generated method stub
		for (GameObject temp : EnemiesList) {
		    // Move enemies
			temp.getCentre().ApplyVector(new Vector3f(enemySpeedX,0,0));

			//see if they get to the top of the screen ( remember 0 is the top 
			if (temp.getCentre().getX() == 1100.0f) {  // current boundary need to pass value to model
				EnemiesList.remove(temp);
				// enemies win so score decreased 
				Score--;
				Score2--;
			}
		}

		for (GameObject temp : EnemiesList2) {
			temp.getCentre().ApplyVector(new Vector3f(enemySpeedX,0,0));

			if (temp.getCentre().getX() == 1100.0f) {
				EnemiesList2.remove(temp);
				Score--;
				Score2--;
			}
		}

		// difficulty
//		if (EnemiesList.size() < 4) {
//			while (EnemiesList.size() < 8) {
//				Random random = new Random();
//				int i = random.nextInt(2);
//
//				String[] enemy = {"res/jellyfish.png", "res/shark.png"};
//				String pic = enemy[i];
//				EnemiesList.add(new GameObject(pic,64,64, new Point3f(0, ((float)Math.random()*1000),0)));
//			}
//		}


		// set difficulty
//		if(getLevel() == 2){
//			enemySize2 = 6;
//		}

		if (EnemiesList.size() < enemySize1) {
			while (EnemiesList.size() < enemySize2) {
				EnemiesList.add(new GameObject("res/shark.png",64,64, new Point3f(0, ((float) random.nextInt(12) * 50 + 100),0)));
			}
		}

		if (EnemiesList2.size() < enemySize1) {
			while (EnemiesList2.size() < enemySize2) {
				EnemiesList2.add(new GameObject("res/jellyfish.png",48,48, new Point3f(0, ((float)Math.random()* 600 + 50),0)));
			}
		}

	}

	private void animalLogic() {
		Thread gameOverSound = new Thread(new SoundPlayer(sound, "gameOver"));
		for (GameObject temp : AnimalsList) {
			// Move enemies
			temp.getCentre().ApplyVector(new Vector3f(0, animalSpeedY, 0));

			if(temp.getCentre().getY() == 750){
				setGameOver(true);
				gameOverSound.start();
			}
		}


		if(getLevel() == 2){
			while(AnimalsList.size() < 1){
				AnimalsList.add(new GameObject("res/fox.png", 30, 30, new Point3f((float)Math.random()* 800 + 350 , 0, 0)));
//				AnimalsList.add(new GameObject("res/cat.png", 32, 32, new Point3f((float)Math.random()* 800 + 350 , 0, 0)));
			}
		}
	}

	private void bulletLogic(CopyOnWriteArrayList<GameObject> list) {
		// move bullets 

		for (GameObject temp : list) {
		    //check to move them
			temp.getCentre().ApplyVector(new Vector3f(bulletSpeedX,0,0));
			//see if they hit anything 
			
			//see if they get to the top of the screen ( remember 0 is the top 
			if (temp.getCentre().getX() == 0) {
			 	list.remove(temp);
			} 
		}
	}


	private void netLogic(CopyOnWriteArrayList<GameObject> list) {
		for (GameObject temp : list) {
			//check to move them


			temp.getCentre().ApplyVector(new Vector3f( netSpeedX, netSpeedY,0));
			//see if they hit anything

			//see if they get to the top of the screen ( remember 0 is the top
			if (temp.getCentre().getY() == 0) {
				list.remove(temp);
			}
		}
	}

	private void playerLogic() {
		// smoother animation is possible if we make a target position
		// done but may try to change things for students
		 
		//check for movement and if you fired a bullet


		Thread shootSound = new Thread(new SoundPlayer(sound, "shoot"));
		Thread throwSound = new Thread(new SoundPlayer(sound, "throw"));


		if(controller.isKeyAPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(-playerSpeed,0,0));
		}
		
		if(controller.isKeyDPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(playerSpeed,0,0));
		}
			
		if(controller.isKeyWPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(0,playerSpeed,0));
		}
		
		if(controller.isKeySPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(0,-playerSpeed,0));
		}

		if(controller.isKeySpacePressed()) {
			CreateBullet();
			shootSound.start();
			controller.setKeySpacePressed(false);
		}
		if(controller.isKeyFPressed()) {
			CreateNet(NetList, Player);
			throwSound.start();
			controller.setKeyFPressed(false);
		}
		if(controller.isKeyVPressed()) {
			CreateNet(NetList, Player);
			throwSound.start();
			controller.setKeyVPressed(false);
		}



		if(controller.isKeyJPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(-playerSpeed,0,0));
		}
		if(controller.isKeyLPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(playerSpeed,0,0));
		}
		if(controller.isKeyIPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(0,playerSpeed,0));
		}
		if(controller.isKeyKPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(0,-playerSpeed,0));
		}

		if(controller.isKeyNPressed()) {
			CreateBullet2();
			shootSound.start();
			controller.setKeyNPressed(false);
		}
		if(controller.isKeyHPressed()) {
			CreateNet(NetList2, Player2);
			throwSound.start();
			controller.setKeyHPressed(false);
		}
		if(controller.isKeyBPressed()) {
			CreateNet(NetList2, Player2);
			throwSound.start();
			controller.setKeyBPressed(false);
		}

//		if(Player.getCentre().getX() == 0.0f && gameStart){
//			setLevel(2);
//
////			System.out.println("level = " + level);
//			setPlayer1X(1176);
//			PlayerList.remove(Player);
////			setGameStart(false);
//		}

	}


	public void nextLevel(){
		if(getLevel() == 1) {
			if (getScore() >= 10 || getScore2() >= 10) {
				setLevel(2);
				setLevelMusic(2);
			}
		}
	}


	public void gameOver(){
		if ( getLife1() <= 0 || getLife2() <= 0) {
			gameOver = true;
		}
	}

	public boolean isWinGame(){
		if (getScore() >= 25 || getScore2() >= 25) {
			return true;
		}
		return false;
	}

	private void CreateBullet() {
		BulletList.add(new GameObject("res/mini_sub_bullet.png",36,36, new Point3f(Player.getCentre().getX(), Player.getCentre().getY(),0.0f)));
//		BulletList.add(new GameObject("res/player_bullet2.png",36,36, new Point3f(Player.getCentre().getX(), Player.getCentre().getY(),0.0f)));
	}
	private void CreateBullet2() {
		BulletList2.add(new GameObject("res/player_bullet.png",36,36, new Point3f(Player2.getCentre().getX(), Player2.getCentre().getY(),0.0f)));
	}

	private void CreateNet(CopyOnWriteArrayList<GameObject> list, GameObject player) {
		list.add(new GameObject("res/net.png",36,36, new Point3f(player.getCentre().getX(), player.getCentre().getY(),0.0f)));
	}



	public GameObject getPlayer() {
		return Player;
	}

	public GameObject getPlayer2() {
		return Player2;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return EnemiesList;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies2() {
		return EnemiesList2;
	}
	
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}
	public CopyOnWriteArrayList<GameObject> getBullets2() {
		return BulletList2;
	}

	public CopyOnWriteArrayList<GameObject> getNets() {
		return NetList;
	}
	public CopyOnWriteArrayList<GameObject> getNets2() {
		return NetList2;
	}

	public CopyOnWriteArrayList<GameObject> getAnimals() {
		return AnimalsList;
	}

	public CopyOnWriteArrayList<GameObject> getLives() {
		return LivesList;
	}
	public CopyOnWriteArrayList<GameObject> getLives2() {
		return LivesList2;
	}

	public CopyOnWriteArrayList<GameObject> getRescueList() {
		return RescueList;
	}

	public void setScore(int Score){
		this.Score = Score;
	}
	public void setScore2(int Score2){
		this.Score2 = Score2;
	}


	public int getScore() { 
		return Score;
	}
	public int getScore2() {
		return Score2;
	}


	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}
	public static int getNumPlayer() {
		return numPlayer;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public static int getLevel() {
		return level;
	}

	public void setLife1(int life1) {
		this.life1 = life1;
	}
	public void setLife2(int life2) {
		this.life2 = life2;
	}

	public int getLife1(){
		return life1;
	}
	public int getLife2(){
		return life2;
	}


	public void setPlayer1X(float x){
		this.player1X = x;
	}
	public void setPlayer2X(float x){
		this.player2X = x;
	}


	public float getPlayer1X() {
		return player1X;
	}
	public float getPlayer2X() {
		return player2X;
	}

	public void setPlayer1Y(float y){
		this.player1Y = y;
	}
	public void setPlayer2Y(float y){
		this.player2Y = y;
	}


	public float getPlayer1Y() {
		return player1Y;
	}
	public float getPlayer2Y() {
		return player2Y;
	}



	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	public boolean isGameOver() {
		return gameOver;
	}

	public void setRescue(int r){
		 this.rescue = r;
	}

	public int getRescue(){
		return rescue;
	}

	public void setLevelMusic(int n){
		this.levelMusic = n;
	}

	public int getLevelMusic(){
		return levelMusic;
	}


	public void setGameStart(boolean s) {
		this.gameStart = s;
	}
	public boolean isGameStart() {
		return gameStart;
	}
	public void updateLabel1(){
		updateScore1(Score);
	}

	public void updateLabel2(){
		updateScore2(Score2);
//		updateLive2(life2);
	}

	public void updateScore1(int score){
		MainWindow.score1Label.setText("Player 1 score: " + score);
	}
	public void updateScore2(int score2){
		MainWindow.score2Label.setText("Player 2 score: " + score2);
	}

	public void updateTitle(int n){
		MainWindow.frame.setTitle("Undersea Rescue - LEVEL " + n);
	}

//	public void updateLive1(int life1){
//		MainWindow.lives1Label.setText("Player 1 lives: " + life1);
//	}
//	public void updateLive2(int life2){
//		MainWindow.lives2Label.setText("Player 2 lives: " + life2);
//	}



}
