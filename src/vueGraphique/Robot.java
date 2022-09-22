package vueGraphique;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Robot extends Elements {
    public Robot(int x, int y) {
        super(x, y);
        initRobot();
    }

    /**
     * va chercher l'image du robot
     */
    private void initRobot() {
        ImageIcon icon = new ImageIcon("src/vueGraphique/ImageGif/Droite.gif");
        Image image = icon.getImage();
        setImage(image);
    }

    public void move(int x, int y) {
        int dx = x() + x;
        int dy = y() + y;  
        setX(dx);
        setY(dy);
    }
}
