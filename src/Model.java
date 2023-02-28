import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 

public class Model {
	private GameObject Player;
	private GameObject Player2;
	private Controller controller = Controller.getInstance();
	private CopyOnWriteArrayList<GameObject> EnemiesList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList2 = new CopyOnWriteArrayList<GameObject>();
	private int Score = 0;
	private int Score2 = 0;

	private int life1 = 3;
	private int life2 = 3;

	private static int numPlayer;

	public Model() {
		//setup game world
//		 Player= new GameObject("res/Lightning.png",564564new Point3f(500,500,0));
//		Player= new GameObject("res/Large/Predator 1.png",564564 new Point3f(500,500,0));
		Player= new GameObject("res/mini_sub.png",64,64, new Point3f(500,500,0));
		Player2= new GameObject("res/player.png",64,64, new Point3f(500,200,0));

		//Enemies  starting with four
		
		EnemiesList.add(new GameObject("res/shark.png",64,64,new Point3f(0, ((float)Math.random()*50+400 ),0)));
		EnemiesList.add(new GameObject("res/shark.png",64,64,new Point3f(0,((float)Math.random()*50+500 ),0)));
		EnemiesList.add(new GameObject("res/shark.png",64,64,new Point3f(0,((float)Math.random()*100+500 ),0)));
		EnemiesList.add(new GameObject("res/shark.png",64,64,new Point3f(0,((float)Math.random()*100+400 ),0)));

		//		EnemiesList.add(new GameObject("res/sea/targets/Jellyfish1.png", new Point3f(((float)Math.random()*50+400 ),0,0)));
//		EnemiesList.add(new GameObject("res/sea/targets/Jellyfish2.png",new Point3f(((float)Math.random()*100+400 ),0,0)));

	}
	
	// This is the heart of the game , where the model takes in all the inputs ,decides the outcomes and then changes the model accordingly. 
	public void gamelogic() {
		// Player Logic first 
		playerLogic(); 
		// Enemy Logic next
		enemyLogic();
		// Bullets move next 
		bulletLogic();
		// interactions between objects 
		gameLogic();

		bulletLogic2();
	   
	}

	private void gameLogic() {
		// this is a way to increment across the array list data structure 

		// see if they hit anything
		// using enhanced for-loop style as it makes it alot easier both code wise and reading wise too


		// if bullet hits the enemy, they're both eliminated from the list and add 1 score at the same time
		for (GameObject temp : EnemiesList) {
			for (GameObject Bullet : BulletList) {
				if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
					&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
					EnemiesList.remove(temp);
					BulletList.remove(Bullet);
					Score++;
					System.out.println("1: " + Score);
				}
			}
			for (GameObject Bullet : BulletList2) {
				if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth()
						&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
					EnemiesList.remove(temp);
					BulletList2.remove(Bullet);
					Score2++;
					System.out.println("2: " + Score2);
				}
			}
			if(Math.abs(temp.getCentre().getX()- Player.getCentre().getX())< temp.getWidth()
					&& Math.abs(temp.getCentre().getY()- Player.getCentre().getY()) < temp.getHeight()){
				EnemiesList.remove(temp);
				Score = Score - 10;
				System.out.println("1: " + Score);
			}
		}
	}

	private void enemyLogic() {
		// TODO Auto-generated method stub
		for (GameObject temp : EnemiesList) {
		    // Move enemies
			temp.getCentre().ApplyVector(new Vector3f(1,0,0));

			//see if they get to the top of the screen ( remember 0 is the top 
			if (temp.getCentre().getX() == 1100.0f) {  // current boundary need to pass value to model
				EnemiesList.remove(temp);
				// enemies win so score decreased 
				Score--;
			} 
		}


		// difficulty
		if (EnemiesList.size() < 2) {
			while (EnemiesList.size() < 6) {
				Random random = new Random();
				int i = random.nextInt(2);

				String[] enemy = {"res/jellyfish.png", "res/shark.png"};
				String pic = enemy[i];
				EnemiesList.add(new GameObject(pic,64,64, new Point3f(0, ((float)Math.random()*1000),0)));
			}
		}
	}

	private void bulletLogic() {
		// TODO Auto-generated method stub
		// move bullets 
	  
		for (GameObject temp : BulletList) {
		    //check to move them
			  
			temp.getCentre().ApplyVector(new Vector3f(-1,0,0));
			//see if they hit anything 
			
			//see if they get to the top of the screen ( remember 0 is the top 
			if (temp.getCentre().getX() == 0) {
			 	BulletList.remove(temp);
			} 
		}
	}

	private void bulletLogic2 () {

		for (GameObject temp : BulletList2) {
			//check to move them

			temp.getCentre().ApplyVector(new Vector3f(-1,1,0));
			//see if they hit anything

			//see if they get to the top of the screen ( remember 0 is the top
			if (temp.getCentre().getX() == 0) {
				BulletList2.remove(temp);
			}
		}
	}

	private void playerLogic() {
		// smoother animation is possible if we make a target position
		// done but may try to change things for students
		 
		//check for movement and if you fired a bullet

		int speed = 2;
		if(Controller.getInstance().isKeyAPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(-speed,0,0));
		}
		
		if(Controller.getInstance().isKeyDPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(speed,0,0));
		}
			
		if(Controller.getInstance().isKeyWPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(0,speed,0));
		}
		
		if(Controller.getInstance().isKeySPressed()) {
			Player.getCentre().ApplyVector( new Vector3f(0,-speed,0));
		}
		
		if(Controller.getInstance().isKeySpacePressed()) {
			CreateBullet();
			Controller.getInstance().setKeySpacePressed(false);
		}


		if(Controller.getInstance().isKeyJPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(-speed,0,0));
		}
		if(Controller.getInstance().isKeyLPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(speed,0,0));
		}
		if(Controller.getInstance().isKeyIPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(0,speed,0));
		}
		if(Controller.getInstance().isKeyKPressed()) {
			Player2.getCentre().ApplyVector( new Vector3f(0,-speed,0));
		}
		if(Controller.getInstance().isKeyNPressed()) {
			CreateBullet2();
			Controller.getInstance().setKeyNPressed(false);
		}

	}

	private void CreateBullet() {
		BulletList.add(new GameObject("res/mini_sub_bullet.png",36,36, new Point3f(Player.getCentre().getX(), Player.getCentre().getY(),0.0f)));
	}
	private void CreateBullet2() {
		BulletList2.add(new GameObject("res/player_bullet.png",36,36, new Point3f(Player2.getCentre().getX(), Player2.getCentre().getY(),0.0f)));
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
	
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}
	public CopyOnWriteArrayList<GameObject> getBullets2() {
		return BulletList2;
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


}
