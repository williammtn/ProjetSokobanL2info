package vueGraphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PlateauGraphique extends JPanel {
    private static final int OFFSET = 30;
    private static final int TAILLE = 30;
    private static final int GAUCHE_COLLISION = 1;
    private static final int DROITE_COLLISION = 2;
    private static final int HAUT_COLLISION = 3;
    private static final int BAS_COLLISION = 4;
    private ArrayList<Mur> murs;
    private ArrayList<Caisse> caisse;
    private ArrayList<Destination> dest;
    private ArrayList<Sol> sol;
    private Robot robot;
    private int w = 0;
    private int h = 0;
    private boolean aReussi = false;
    private int niveau = 1;
    private String niveau1 = "//####///\n"
                           + "###  ####\n"
                           + "#     $ #\n"
                           + "# #  #$ #\n"
                           + "# . .#@ #\n"
                           + "#########\n";

    private String niveau2 = "/////////////////////\n"
                           + "/////#####///////////\n"
                           + "/////#   #///////////\n"
                           + "/////#$  #///////////\n"
                           + "///###  $##//////////\n"
                           + "///#  $ $ #//////////\n"
                           + "/### # ## #///######/\n"
                           + "/#   # ## #####  ..#/\n"
                           + "/# $  $          ..#/\n"
                           + "/##### ### #@##  ..#/\n"
                           + "/////#     #########/\n"
                           + "/////#######/////////\n"
                           + "/////////////////////\n";

    private String niveau3 = "#####/####//////\n"
                             +"#...#/#  ####///\n"
                             +"#...###  $  #///\n"
                             +"#....## $  $###/\n"
                             +"##....##   $  #/\n"
                             +"###... ## $ $ #/\n"
                             +"# ##    #  $  #/\n"
                             +"#  ## # ### ####\n"
                             +"# $ # #$  $    #\n"
                             +"#  $ @ $    $  #\n"
                             +"#   # $ $$ $ ###\n"
                             +"#  ######  ###//\n"
                             +"# ##////####////\n"
                             +"###/////////////\n";

    public PlateauGraphique() {
        initPlateauGraphique();
    }

    private void initPlateauGraphique() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initPlateau(niveau1);
    }

    public int getPlateauGraphiqueWidth() {
        return this.w;
    }

    public int getPlateauGraphiqueHeight() {
        return this.h;
    }

    private void initPlateau(String niveau) {     
        murs = new ArrayList<>();
        caisse = new ArrayList<>();
        dest = new ArrayList<>();
        sol = new ArrayList<>();
        int x = OFFSET;
        int y = OFFSET;
        Mur Mur;
        Caisse b;
        Destination a;
        Sol s;
        for (int i = 0; i < niveau.length(); i++) {
            char indice = niveau.charAt(i);
            switch (indice) {
                case '\n':
                    y += TAILLE;
                    if (this.w < x) {
                        this.w = x;
                    }
                    x = OFFSET;
                    break;
                
                case '/':
                    x += TAILLE;
                    break;    

                case '#':
                    Mur = new Mur(x, y);
                    murs.add(Mur);
                    x += TAILLE;
                    break;

                case '$':
                    b = new Caisse(x, y);
                    caisse.add(b);
                    x += TAILLE;
                    break;

                case '.':
                    a = new Destination(x, y);
                    dest.add(a);
                    x += TAILLE;
                    break;

                case '@':
                    robot = new Robot(x, y);
                    x += TAILLE;
                    break;

                case ' ':
                    s = new Sol(x,y);
                    sol.add(s);
                    x += TAILLE;
                    break;

                default:
                    break;
            }
            h = y;
        }
    }

    private void buildWorld(Graphics g) {
        g.setColor(new Color(0, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ArrayList<Elements> world = new ArrayList<>();
        world.addAll(sol);
        world.addAll(murs);
        world.addAll(dest);
        world.addAll(caisse);
        world.add(robot);
        
        for (int i = 0; i < world.size(); i++) {
            Elements indice = world.get(i);
            if (indice instanceof Robot || indice instanceof Caisse) {        
                g.drawImage(indice.getImage(), indice.x() + 2, indice.y() + 2, this);
            } else {       
                g.drawImage(indice.getImage(), indice.x(), indice.y(), this);
            }
            if (aReussi) {       
                g.setColor(new Color(0, 0, 0));
                g.drawString("Niveau terminé !", 25, 20);
            }
        }
    }

    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        buildWorld(graph);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (aReussi) {
                return;
            }
            int key = e.getKeyCode();
            switch (key) {         
                case KeyEvent.VK_LEFT:       
                    if (checkMurCollision(robot,GAUCHE_COLLISION)) {
                        return;
                    }
                    
                    if (checkCollisionCaisses(GAUCHE_COLLISION)) {
                        return;
                    } 
                    robot.move(-TAILLE, 0); 
                    break;
                    
                case KeyEvent.VK_RIGHT:   
                    if (checkMurCollision(robot, DROITE_COLLISION)) {
                        return;
                    }
           
                    if (checkCollisionCaisses(DROITE_COLLISION)) {
                        return;
                    } 
                    robot.move(TAILLE, 0);  
                    break;
                    
                case KeyEvent.VK_UP: 
                    if (checkMurCollision(robot, HAUT_COLLISION)) {
                        return;
                    }
                    
                    if (checkCollisionCaisses(HAUT_COLLISION)) {
                        return;
                    }
                    robot.move(0, -TAILLE); 
                    break;
                    
                case KeyEvent.VK_DOWN:  
                    if (checkMurCollision(robot, BAS_COLLISION)) {
                        return;
                    }
                    
                    if (checkCollisionCaisses(BAS_COLLISION)) {
                        return;
                    }   
                    robot.move(0, TAILLE);  
                    break;
                    
                case KeyEvent.VK_R: 
                    restartniveau(); 
                    break;

                case KeyEvent.VK_F: 
                    System.exit(0);
                    break;
                    
                default:
                    break;
            }
            repaint();
        }
    }

    private boolean checkMurCollision(Elements Elements, int type) {
        switch (type) {
            case GAUCHE_COLLISION:      
                for (int i = 0; i < murs.size(); i++) {     
                    Mur Mur = murs.get(i);    
                    if (Elements.collisionGauche(Mur)) {        
                        return true;
                    }
                } 
                return false;
                
            case DROITE_COLLISION: 
                for (int i = 0; i < murs.size(); i++) {     
                    Mur Mur = murs.get(i);    
                    if (Elements.collisionDroite(Mur)) {
                        return true;
                    }
                }
                return false;
                
            case HAUT_COLLISION:
                for (int i = 0; i < murs.size(); i++) {  
                    Mur Mur = murs.get(i);
                    if (Elements.collisionHaut(Mur)) {  
                        return true;
                    }
                }
                return false;
                
            case BAS_COLLISION: 
                for (int i = 0; i < murs.size(); i++) { 
                    Mur Mur = murs.get(i);
                    if (Elements.collisionBas(Mur)) {  
                        return true;
                    }
                }
                return false;
                
            default:
                break;
        }
        return false;
    }

    private boolean checkCollisionCaisses(int type) {
        switch (type) {      
            case GAUCHE_COLLISION:          
                for (int i = 0; i < caisse.size(); i++) {
                    Caisse caisses = caisse.get(i);
                    if (robot.collisionGauche(caisses)) {
                        for (int j = 0; j < caisse.size(); j++) {                   
                            Caisse indice = caisse.get(j);                    
                            if (!caisses.equals(indice)) {                        
                                if (caisses.collisionGauche(indice)) {
                                    return true;
                                }
                            }                    
                            if (checkMurCollision(caisses, GAUCHE_COLLISION)) {
                                return true;
                            }
                        }                  
                        caisses.move(-TAILLE, 0);
                        aReussi();
                    }
                }          
                return false;
                
            case DROITE_COLLISION:       
                for (int i = 0; i < caisse.size(); i++) {
                    Caisse caisses = caisse.get(i);         
                    if (robot.collisionDroite(caisses)) {             
                        for (int j = 0; j < caisse.size(); j++) {
                            Caisse indice = caisse.get(j);                     
                            if (!caisses.equals(indice)) {        
                                if(caisses.collisionDroite(indice)) {
                                    return true;
                                }
                            }                    
                            if (checkMurCollision(caisses, DROITE_COLLISION)) {
                                return true;
                            }
                        }                   
                        caisses.move(TAILLE, 0);
                        aReussi();
                    }
                }
                return false;
                
            case HAUT_COLLISION:          
                for (int i = 0; i < caisse.size(); i++){
                    Caisse caisses = caisse.get(i);          
                    if (robot.collisionHaut(caisses)){                 
                        for (int j = 0; j < caisse.size(); j++){
                            Caisse indice = caisse.get(j);
                            if (!caisses.equals(indice)){                              
                                if (caisses.collisionHaut(indice)){
                                    return true;
                                }
                            }                         
                            if (checkMurCollision(caisses, HAUT_COLLISION)){
                                return true;
                            }
                        }                     
                        caisses.move(0, -TAILLE);
                        aReussi();
                    }
                }
                return false;
                
            case BAS_COLLISION:            
                for (int i = 0; i < caisse.size(); i++){
                    Caisse caisses = caisse.get(i);         
                    if (robot.collisionBas(caisses)){           
                        for (int j = 0; j < caisse.size(); j++){
                            Caisse indice = caisse.get(j);            
                            if (!caisses.equals(indice)){                   
                                if (caisses.collisionBas(indice)){
                                    return true;
                                }
                            }   
                            if (checkMurCollision(caisses,BAS_COLLISION)){              
                                return true;
                            }
                        }
                        caisses.move(0, TAILLE);
                        aReussi();
                    }
                }           
                break;            
            default:
                break;
        }
        return false;
    }

    public void aReussi(){
        int nbCaisse = caisse.size();
        int caisseRemplie = 0;
        for (int i = 0; i < nbCaisse; i++){
            Caisse caisses = caisse.get(i);
            for (int j = 0; j < nbCaisse; j++){
                Destination dests =  dest.get(j); 
                if (caisses.x() == dests.x() && caisses.y() == dests.y()){    
                    caisseRemplie += 1;
                }
            }
        }
        if (caisseRemplie == nbCaisse){   
            aReussi = true;
            repaint();
        }
        niveauSuivant();
        
    }

    private void restartniveau(){
        dest.clear();
        caisse.clear();
        murs.clear();
        initPlateau(niveau1);
        if (aReussi) {
            aReussi = false;
        }
    }

    private void niveauSuivant(){
        if(aReussi && niveau == 1){
            aReussi = false;
            niveau++;
            initPlateau(niveau2);
        }  
        if(aReussi && niveau == 2){
            aReussi = false;
            niveau++;
            initPlateau((niveau3));
        }
        if(aReussi && niveau == 3){
            System.out.println("Fini !");
        }
    }    
}