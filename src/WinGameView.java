import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinGameView extends JPanel {

    static GameModel model = new GameModel();



    public WinGameView(GameModel model) {
        this.model = model;
    }

    public void updateview() {
        this.repaint();
    }

    public void paintComponent(Graphics g2) {

        super.paintComponent(g2);
//        Graphics2D g = (Graphics2D) g2;

        drawBackground(g2);
//        System.out.println("game over!!");

    }

    private void drawBackground(Graphics g) {
//		File TextureToLoad = new File("res/spacebackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
        File TextureToLoad = new File("res/win.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
//			g.drawImage(myImage, 0,0, 0, 0, 10 , 10, 0, 0, null);
            g.drawImage(myImage, 0,0, 1164, 764, 0 , 0, 1164, 764, null); //0, 0, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}





