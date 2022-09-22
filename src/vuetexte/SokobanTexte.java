package vuetexte;
import modele.*;

/*Classe SokobanTexte qui contient la classe main pour le lancement de SokobanTexte
*/
public class SokobanTexte {
    public static void main(String[] args) {
        try {
            Carte carte = new Carte();
            System.out.println(carte);
        } catch (Exception a) {
            System.out.println("il y a une erreur " + a.getMessage());
        }
    }
}
