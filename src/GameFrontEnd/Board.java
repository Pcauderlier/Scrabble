package GameFrontEnd;

import java.util.ArrayList;

public class Board {
    public static String[][] grid;
    public static void resetBoard(int size) {
        Board.grid = new String[size][size];
        // Initialisation de chaque cellule avec une valeur par défaut
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Board.grid[i][j] = "-"; // Case vide
            }
        }
    }
    public static void printBoard() {
        for (int i = 0; i < Board.grid.length; i++) {
            for (int j = 0; j < Board.grid[i].length; j++) {
                System.out.print("| "+Board.grid[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
