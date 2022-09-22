package modele;

import java.util.ArrayList;
import java.io.*;

public class Lecture {
    private int nbLignes;
    private int largeurLignes;
    private ArrayList<String> listeLignes = new ArrayList<>();

    /**
     * Constructeur de la classe qui gère quelle map importer
     * @param niv le nombre de niv definie la map à charger
     */
    public Lecture(int niv) {
        if (niv == 1) {
            try {
                File file = new File("map/map1.txt");
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    int c = 0;
                    while ((c = br.read()) != -1) {
                        char ch = (char) c;
                        String sch = Character.toString(ch);
                        listeLignes.add(sch);
                    }
                }
            }

            catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
            largeurLignes = 9;
            nbLignes = 6;
        }

        if (niv == 2) {
            try {
                File file = new File("map/map2.txt");
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)){
                    int c = 0;
                    while ((c = br.read()) != -1) {
                        char ch = (char) c;
                        String sch = Character.toString(ch);
                        listeLignes.add(sch);
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
            largeurLignes = 21;
            nbLignes = 13;
        }

        if (niv == 3) {
            try {
                File file = new File("map/map3.txt");
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    int c = 0;
                    while ((c = br.read()) != -1) {
                        char ch = (char) c;
                        String sch = Character.toString(ch);
                        listeLignes.add(sch);
                    }
                }
            }
            catch (Exception e){
                System.out.println("Erreur : " + e.getMessage());
            }
            largeurLignes = 21;
            nbLignes = 13;
        }
    }

    /**
     * 
     * @return va chercher le nb de lignes soit la hauteur du futur plateau
     */
    public int getnbLignes() {
        return nbLignes;
    }

    /**
     * 
     * @return va chercher la largeur des lignes soit la largeur du futur plateau
     */
    public int getLargeurLignes() {
        return largeurLignes;
    }

    /**
     * 
     * @return permet de recuperer les caracteres de l'import depuis l'array list listeLignes
     */
    public ArrayList<String> getListeLignes() {
        return listeLignes;
    }

}
