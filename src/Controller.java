import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;


//Singeton pattern
public class Controller implements KeyListener {

	private static boolean KeyAPressed = false;
	private static boolean KeySPressed = false;
	private static boolean KeyDPressed = false;
	private static boolean KeyWPressed = false;
	private static boolean KeySpacePressed = false;

	private static boolean KeyJPressed = false;
	private static boolean KeyKPressed = false;
	private static boolean KeyLPressed = false;
	private static boolean KeyIPressed = false;
	private static boolean KeyNPressed = false;

	private static final Controller instance = new Controller();
	   
	public Controller() {

	}

	public static Controller getInstance(){
	        return instance;
	}


	   
	@Override
	// Key pressed, will keep triggering
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'a':
				setKeyAPressed(true);
				break;
			case 's':
				setKeySPressed(true);
				break;
			case 'w':
				setKeyWPressed(true);
				break;
			case 'd':
				setKeyDPressed(true);
				break;
			case ' ':
				setKeySpacePressed(true);
				break;

			case 'j':
				setKeyJPressed(true);
				break;
			case 'k':
				setKeyKPressed(true);
				break;
			case 'l':
				setKeyLPressed(true);
				break;
			case 'i':
				setKeyIPressed(true);
				break;
			case 'n':
				setKeyNPressed(true);
				break;
			default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}
	 // You can implement to keep moving while pressing the key here .
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'a':
				setKeyAPressed(false);
				break;
			case 's':
				setKeySPressed(false);
				break;
			case 'w':
				setKeyWPressed(false);
				break;
			case 'd':
				setKeyDPressed(false);
				break;
			case ' ':
				setKeySpacePressed(false);
				break;

			case 'j':
				setKeyJPressed(false);
				break;
			case 'k':
				setKeyKPressed(false);
				break;
			case 'l':
				setKeyLPressed(false);
				break;
			case 'i':
				setKeyIPressed(false);
				break;
			case 'n':
				setKeyNPressed(false);
				break;

			default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		 //upper case
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}
	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}


	public boolean isKeySPressed() {
		return KeySPressed;
	}
	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}


	public boolean isKeyDPressed() {
		return KeyDPressed;
	}
	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}


	public boolean isKeyWPressed() {
		return KeyWPressed;
	}
	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}


	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}
	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	}


	public boolean isKeyJPressed() {
		return KeyJPressed;
	}
	public void setKeyJPressed(boolean keyJPressed) {
		KeyJPressed = keyJPressed;
	}

	public boolean isKeyKPressed() {
		return KeyKPressed;
	}
	public void setKeyKPressed(boolean keyKPressed) {
		KeyKPressed = keyKPressed;
	}

	public boolean isKeyLPressed() {
		return KeyLPressed;
	}
	public void setKeyLPressed(boolean keyLPressed) {
		KeyLPressed = keyLPressed;
	}

	public boolean isKeyIPressed() {
		return KeyIPressed;
	}
	public void setKeyIPressed(boolean keyIPressed) {
		KeyIPressed = keyIPressed;
	}

	public boolean isKeyNPressed() {
		return KeyNPressed;
	}
	public void setKeyNPressed(boolean keyNPressed) {
		KeyNPressed = keyNPressed;
	}


}
