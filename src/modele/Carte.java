package modele;

import vuetexte.Outil;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe carte qui gere le jeu grace a un import de la map effectué dans la classe Lecture
 */
public class Carte extends Lecture {
    private int tailleLongueur = getLargeurLignes();
    private int tailleLargeur = getnbLignes();
    private ArrayList<String> listeLigne = getListeLignes();
    private String fin = "";
    private int i;
    private int j;
    private int x;
    private int y;
    private int c;
    private int d;
    private int nbDeplacements;
    private int compteurVictoire;
    private int compteurDestination;
    private String[][] plateau = new String[tailleLargeur][tailleLongueur];
    private static int niv = 1;

    /**
     * Prend en compte le niveau du jeu pour changer d'import de map
     */
    public Carte() {
        super(niv);
    }

    /**
    * @param i position dans tailleLargeur
    * @param j position dans tailleLongueur
    * @return retourne le caractère du plateau present aux coordonnees i et j
    */
    public String getCoordonnees(int i, int j) {
        return plateau[i][j];
    }

    public void impossible(){
        System.out.println("Impossible de se deplacer par la");
    }

    /**
    * initialise le plateau de jeu a partir de l'arraylist de la classe Lecture
    */
    public void init() {
        nbDeplacements = 0;
        int a = 0;
        for (int h = 1; h < tailleLargeur; h++) {
            listeLigne.remove((h * tailleLongueur));
        }
        for (i = 0; i < tailleLargeur; i++) {
            for (j = 0; j < tailleLongueur; j++) {
                plateau[i][j] = listeLigne.get(a);
                a++;
                if("@".equals(getCoordonnees(i, j))){
                    x = i;
                    y = j;
                }
                if(".".equals(getCoordonnees(i,j))){
                    compteurDestination++;
                }
            }
        }
        deplacement();    
    }
    /**
     * reinitialise le plateau
     */
    public void reinit(){
        nbDeplacements = 0;
                int e = 0;
                for (i = 0; i < tailleLargeur; i++) {
                    for (j = 0; j < tailleLongueur; j++) {
                        plateau[i][j] = listeLigne.get(e);
                        e++;
                        if("@".equals(getCoordonnees(i, j))){
                            x = i;
                            y = j;
                        }
                        else if(".".equals(getCoordonnees(i,j))){
                            compteurDestination++;
                        }
                    }
                }
                deplacement();
    }
    /**
     * affiche le plateau et affecte x à i et y à j pour garder en memoire les coordonnees du robot
     * meme chose avec c et d qui gardent en memoire la superposition du robot sur une destination
     */
    public void affichage(){
        System.out.print("Nombre deplacements : " + nbDeplacements +"\n");
        for (i = 0; i < tailleLargeur; i++) {
            for (j = 0; j < tailleLongueur; j++) {
                if("@".equals(getCoordonnees(i, j))){
                    x = i;
                    y = j;
                }
                if("+".equals(getCoordonnees(i,j))){
                    c = i;
                    d = j;
                }
                System.out.print(" " + plateau[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    /**
     * methode qui permet de deplacer le robot grace a la classe Outil dans le package vuetexte
     */
    public void deplacement(){
        affichage();
        System.out.println("Z pour haut | Q pour gauche | S pour bas | D pour droite");
        System.out.println("F pour quitter la partie");
        System.out.println("R pour relancer le niveau");
        while(!testVictoire()){
            char lecture = Outil.lireCaractere();
            if(lecture == 'z'){
                enHaut();
            }
            if(lecture == 'q'){
                aGauche();
            }
            if(lecture == 'd'){
                aDroite();
            }
            if(lecture == 's'){
                enBas();
            }
            if(lecture == 'f'){
                System.exit(0);
            }
            if(lecture == 'r'){
                reinit();
            }
            else{
                deplacement();
            } 
        }   
    }
    /**
     * methode qui gere les déplacements vers le haut
     */
    public void enHaut(){
        if(!"#".equals(getCoordonnees(x-1, y))){
            if(!"*".equals(getCoordonnees(x-1, y))){
                if("+".equals(getCoordonnees(c,d))){
                    plateau[c][d]= ".";
                    plateau[c-1][d] = "@";
                    nbDeplacements++;
                }
                else if(".".equals(getCoordonnees(x-1,y))){
                    plateau[x][y]= " ";
                    plateau[x-1][y] = "+";
                    nbDeplacements++;

                }
                else if(!"$".equals(getCoordonnees(x-1, y))){
                    plateau[x][y] = " ";
                    plateau[x-1][y] = "@";
                    nbDeplacements++;
                    }

                else if(" ".equals(getCoordonnees(x-2,y))){
                    plateau[x][y]= " ";
                    plateau[x-1][y] = "@";
                    plateau[x-2][y] = "$";
                    nbDeplacements++;
                }
                else if(".".equals(getCoordonnees(x-2,y))){
                    plateau[x][y]= " ";
                    plateau[x-1][y] = "@";
                    plateau[x-2][y] = "*";
                    nbDeplacements++;
                    compteurVictoire++;    
                }  
                else{
                    impossible();
                }    
            }
            else{
                impossible();
            }                     
        }
        else{
            impossible();
        }   
        testVictoire();                    
        deplacement();
    }
    /**
     * methode qui gere les deplacements à gauche
     */
    public void aGauche(){
        if(!"#".equals(getCoordonnees(x, y-1))){
            if(!"*".equals(getCoordonnees(x, y-1))){
                if("+".equals(getCoordonnees(c,d))){
                    plateau[c][d]= ".";
                    plateau[c][d-1] = "@";
                    nbDeplacements++;
                }
                if(".".equals(getCoordonnees(x,y-1))){
                    plateau[x][y]= " ";
                    plateau[x][y-1] = "+";
                    nbDeplacements++;
                }         
                else if(!"$".equals(getCoordonnees(x, y-1))){
                    plateau[x][y]= " ";
                    plateau[x][y-1] = "@";
                    nbDeplacements++;
                }
                else if(" ".equals(getCoordonnees(x,y-2))){
                    plateau[x][y]= " ";
                    plateau[x][y-1] = "@";
                    plateau[x][y-2] = "$";
                    nbDeplacements++;
                }     
                else if(".".equals(getCoordonnees(x,y-2))){
                    plateau[x][y]= " ";
                    plateau[x][y-1] = "@";
                    plateau[x][y-2] = "*";
                    nbDeplacements++;
                    compteurVictoire++;
                }  
                else{
                    impossible();
                }    
            }
            else{
                impossible();
            }
        }
        else{
            impossible();
        }    
        testVictoire();                   
        deplacement();
    } 
    /**
     *  methode qui gere les deplacements a droite
     */
    public void aDroite(){
        if(!"#".equals(getCoordonnees(x, y+1))){
            if(!"*".equals(getCoordonnees(x, y+1))){
                if("+".equals(getCoordonnees(c,d))){
                    plateau[c][d]= ".";
                    plateau[c][d+1] = "@";
                    nbDeplacements++;
                }
                else if(".".equals(getCoordonnees(x,y+1))){
                    plateau[x][y]= " ";
                    plateau[x][y+1] = "+";
                    nbDeplacements++;
                }
                else if(!"$".equals(getCoordonnees(x, y+1))){
                    plateau[x][y]= " ";
                    plateau[x][y+1] = "@";
                    nbDeplacements++;
                }
                else if(" ".equals(getCoordonnees(x,y+2))){
                    plateau[x][y]= " ";
                    plateau[x][y+1] = "@";
                    plateau[x][y+2] = "$";
                    nbDeplacements++;
                }   
                else if(".".equals(getCoordonnees(x,y+2))){
                    plateau[x][y]= " ";
                    plateau[x][y+1] = "@";
                    plateau[x][y+2] = "*";
                    nbDeplacements++;
                    compteurVictoire++;
                }
                else{
                    impossible();
                }    
            }
            else{
                impossible();
            }
        }
        else{
            impossible();
        }
        testVictoire();
        deplacement();                      
    }
    /**
     *  methode qui gere les deplacements en bas
     */
    public void enBas(){
        if(!"#".equals(getCoordonnees(x+1, y))){
            if(!"*".equals(getCoordonnees(x+1, y))){
                if("+".equals(getCoordonnees(c,d))){
                    plateau[c][d]= ".";
                    plateau[c+1][d] = "@";
                    nbDeplacements++;
                }
                else if(".".equals(getCoordonnees(x+1,y))){
                    plateau[x][y]= " ";
                    plateau[x+1][y] = "+";
                    nbDeplacements++;
                }
                else if(!"$".equals(getCoordonnees(x+1, y))){
                    plateau[x][y]= " ";
                    plateau[x+1][y] = "@";
                    nbDeplacements++; 
                }    
                else if(" ".equals(getCoordonnees(x+2,y))){
                    plateau[x][y]= " ";
                    plateau[x+1][y] = "@";
                    plateau[x+2][y] = "$";
                    nbDeplacements++;
                }
                else if(".".equals(getCoordonnees(x+2,y))){
                    plateau[x][y]= " ";
                    plateau[x+1][y] = "@";
                    plateau[x+2][y] = "*";
                    nbDeplacements++;
                    compteurVictoire++;
                }
                else{
                    impossible();
                }    
            }
            else{
                impossible();
            }
        }
        else{
            impossible();
        }                
        testVictoire();
        deplacement();
    } 
    /**
     * methode qui test les conditions de victoire en fonction du nombre de caisse sur le nombre de destination
     * @return true si verifié, false sinon
     */
    public boolean testVictoire(){
        if(compteurVictoire == compteurDestination){
            System.out.println("Bravo niveau" + niv + "reussi");
            return true;
        }
        return false;
    }

    /*public int nivSuivant(){
        if(testVictoire()){
            niv++;
        }
        return niv;
    }*/

    /**
     * methode toString() qui gere le demarrage du jeu en affichage texte sur la console
     */
    public String toString() {
        System.out.println("Bienvenue dans le jeu Sokoban en mode Texte, \nIl y a 3 niveaux dans notre Jeu Sokoban :\n-1\n-2\n-3\n \nEntrez 1 pour commencer au 1er niveau");
        try (Scanner e = new Scanner(System.in)) {
            if (e.nextInt() == 1) {
                System.out.println("RAPPEL des regles du Sokoban : Vous devez bouger, avec le charactere '@', les caisses('$') sur les destinations('.')\n");
                init();    
            }
            else {
                System.exit(0);
            }
        }
        return fin;
    }   
}