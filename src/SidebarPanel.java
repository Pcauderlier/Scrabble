import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel playerLabel = new JLabel("Joueur 1");
        add(playerLabel);

        JLabel scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        // Ajouter un espace pour les lettres
        JPanel lettersPanel = new JPanel();
        lettersPanel.setLayout(new FlowLayout());
        for (int i = 0; i < 7; i++) {
            JButton letter = new JButton("A");
            lettersPanel.add(letter);
        }
        add(lettersPanel);
    }
}
