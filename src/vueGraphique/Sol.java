package vueGraphique;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sol extends Elements {
    public Sol(int x, int y) {
        super(x, y);
        initSol();
    }

    /**
     * va chercher l'image du sol
     */
    private void initSol() {
        ImageIcon icon = new ImageIcon("src/vueGraphique/ImageGif/sol.gif");
        Image image = icon.getImage();
        setImage(image);
    }
}