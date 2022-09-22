package vueGraphique;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Caisse extends Elements {
    public Caisse(int x, int y) {
        super(x, y);
        initCaisse();
    }
    
    /**
     * va chercher l'image des caisses
     */
    private void initCaisse() {   
        ImageIcon icon = new ImageIcon("src/vueGraphique/ImageGif/caisse1.gif");
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
