/*
 * Name: Chih-Yu Huang
 * Student number: 22209269
 *
 * */

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

        drawBackground(g2);

    }

    private void drawBackground(Graphics g) {
        File TextureToLoad = new File("res/win.png");try {
            Image myImage = ImageIO.read(TextureToLoad);g.drawImage(myImage, 0,0, 1164, 764, 0 , 0, 1164, 764, null); //0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





