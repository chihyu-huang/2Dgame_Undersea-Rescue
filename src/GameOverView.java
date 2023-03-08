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

public class GameOverView extends JPanel {
//    private JLabel scoreLabel;
//    private JButton restartButton;
//    private JButton quitButton;
//
//    public GameOverView(int score) {
//        scoreLabel = new JLabel("Final Score: " + score);
//        restartButton = new JButton("Restart");
//        quitButton = new JButton("Quit");
//
//        // add components to the panel
//        add(scoreLabel);
//        add(restartButton);
//        add(quitButton);
//    }
//
//    // getters for the buttons
//    public JButton getRestartButton() {
//        return restartButton;
//    }
//
//    public JButton getQuitButton() {
//        return quitButton;
//    }

    static Model model1 = new Model();
    static GameModel model = new GameModel();



    public GameOverView(GameModel World) {
        this.model = World;
        // TODO Auto-generated constructor stub
    }

    public void updateview() {
        this.repaint();
        // TODO Auto-generated method stub
    }

    public void paintComponent(Graphics g2) {

        super.paintComponent(g2);
//        Graphics2D g = (Graphics2D) g2;

        drawBackground(g2);

    }

    private void draw(Graphics g) {
        File TextureToLoad = new File("res/startscreen.png");
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            g.drawImage(myImage, 0,0, 1164, 764, 0 , 0, 1164, 764, null); //0, 0, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawBackground(Graphics g) {
//            File TextureToLoad = new File("res/win.png");
        File TextureToLoad = new File("res/gameOver.jpeg");
        try {
            Image myImage = ImageIO.read(TextureToLoad);
//			g.drawImage(myImage, 0,0, 0, 0, 10 , 10, 0, 0, null);
            g.drawImage(myImage, 0,0, 1164, 764, 0 , 0, 1164, 764, null); //0, 0, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawWinBackground(Graphics g) {
        File TextureToLoad = new File("res/win.png");
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            g.drawImage(myImage, 0,0, 1164, 764, 0 , 0, 1164, 764, null); //0, 0, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
