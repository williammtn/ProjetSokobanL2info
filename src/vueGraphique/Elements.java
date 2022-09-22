package vueGraphique;

import java.awt.Image;

public class Elements {
    private static final int TAILLE = 30;
    private int x;
    private int y;
    private Image image;

    public Elements(int x, int y) {    
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {  
        return x;
    }

    public int y() {   
        return y;
    }

    public void setX(int x) {   
        this.x = x;
    }

    public void setY(int y) {  
        this.y = y;
    }

    public boolean collisionGauche(Elements elements) { 
        return x() - TAILLE == elements.x() && y() == elements.y();
    }

    public boolean collisionDroite(Elements elements) { 
        return x() + TAILLE == elements.x() && y() == elements.y();
    }

    public boolean collisionHaut(Elements elements) {
        return y() - TAILLE == elements.y() && x() == elements.x();
    }

    public boolean collisionBas(Elements elements) {
        return y() + TAILLE == elements.y() && x() == elements.x();
    }
}
