package vueGraphique;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Mur extends Elements {
    public Mur(int x, int y) {
        super(x, y);
        initMur();
    }
    
    /**
     * va chercher l'image des murs
     */
    private void initMur() {
        ImageIcon icon = new ImageIcon("src/vueGraphique/ImageGif/mur.gif");
        Image image = icon.getImage();
        setImage(image);
    }
}
