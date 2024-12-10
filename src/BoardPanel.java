import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public BoardPanel() {
        setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 225; i++) {
            JButton cell = new JButton();
            cell.setBackground(Color.LIGHT_GRAY); // Exemple de couleur pour les cases
            add(cell);
        }
    }
}
