package vueGraphique;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Destination extends Elements {

    public Destination(int x, int y) {
        super(x, y);
        initDest();
    }
    
    /**
     * va chercher l'image des destinations
     */
    private void initDest() {
        ImageIcon icon = new ImageIcon("src/vueGraphique/ImageGif/but.gif");
        Image image = icon.getImage();
        setImage(image);
    }
}
