import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Scrabble");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajouter le plateau au centre
        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Ajouter une barre latérale pour les joueurs
        SidebarPanel sidebarPanel = new SidebarPanel();
        add(sidebarPanel, BorderLayout.EAST);
    }

    public static class ScrabbleGame {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            });
        }
    }
}
