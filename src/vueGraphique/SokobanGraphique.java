package vueGraphique;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SokobanGraphique extends JFrame {
    private static final int OFFSET = 30;

    public SokobanGraphique() {
        initUI();
    }

    private void initUI() {
        PlateauGraphique plateauGraphique = new PlateauGraphique();
        add(plateauGraphique);
        setTitle("Sokoban Graphique");
        setSize(plateauGraphique.getPlateauGraphiqueWidth() + OFFSET,plateauGraphique.getPlateauGraphiqueHeight() + 2 * OFFSET);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SokobanGraphique jeu = new SokobanGraphique();
            jeu.setVisible(true);
        });
    }
}
