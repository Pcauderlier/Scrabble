package GameFrontEnd;

import GameLogic.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {
    private ArrayList<String> alphabet;
    public String[][] grid;
    public Board(int size) {
        this.resetBoard(size);
    alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    }
    public void resetBoard(int size) {
        this.grid = new String[size][size];
        // Initialisation de chaque cellule avec une valeur par défaut
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = "-"; // Case vide
            }
        }
    }
    public boolean placeWorld(String word,boolean isHorizontal, int num, String letter , Player player ){
        int letterIndex = alphabet.indexOf(letter.toUpperCase());
        String[][] copiedGrid = this.copyGrid();
        ArrayList<String> chevalet = player.getChevalet();
        boolean isLegal = true;
        if (isHorizontal){
            for (int i = 0; i < word.length(); i++) {

                String l = word.substring(i,i+1);
                chevalet = this.isLegal(l,copiedGrid[num][letterIndex+i],chevalet);
                if (chevalet == null){
                    isLegal = false;
                    break;
                }
                copiedGrid[num][letterIndex+i] = l;
            }
        }
        else{
            for (int i = 0; i < word.length(); i++) {
                String l = word.substring(i,i+1);
                chevalet = this.isLegal(l,copiedGrid[num+i][letterIndex],chevalet);
                if (chevalet == null){
                    isLegal = false;
                    break;
                }
                copiedGrid[num+i][letterIndex] = l;
            }
        }
        if(isLegal){
            grid = copiedGrid;
            int points = player.addPoints(word);
            player.updateChevalet(chevalet);
            player.populateChevalet();
            System.out.println("Vous avez réussis a placer le mot : " + word + "\n Vous avez donc gagner " + points + " points");
            return true;
        }
        System.out.println("Le mot : '" + word + "' ne peut etre placer sur la grille");
        return false;

    }

    /**
     *
     * @param l
     * @param gridL
     * @param chevalet
     * @return Null quand la lettre ne rentre pas (soit parceque déja présente et pas correspondante, soit psk pas présente dans le chevalet
     */
    private ArrayList<String> isLegal(String l,String gridL, ArrayList<String> chevalet) {
        // Si le chevalet
        if (gridL.equals("-")){
            // Pas de lettre déja inscrite
            if ( chevalet.contains(l)){
                chevalet.remove(l);
            }
            else{
                System.out.println("NULL 1 : (gridL = " + gridL + " / l = " + l);
                return null;
            }
        }
        else if (!gridL.equals(l)){
//             Déja une lettre et elle correspond pas a celle rechercher dans le mot
            System.out.println("NULL 2 : (gridL = " + gridL + " / l = " + l);
            return null;
        }

        return chevalet;

    }
    private String[][] copyGrid() {
        String[][] copy = new String[this.grid.length][this.grid[0].length];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                copy[i][j] = this.grid[i][j];
            }
        }
        return copy;
    }
    public void printBoard() {
        for (int i = -1; i < this.grid.length; i++) {
            if (i < 0){
                String str = new String("    ");
                for (int j = 0; j < this.grid[0].length; j++) {
                    str += "| "+ alphabet.get(j) + " ";
                }
                str += "|";
                System.out.println(str);
                String line = str.replaceAll(".", "_");
                System.out.println(line);

            }

            else{
                System.out.print((i) + "  ");
                if(i <10){
                    System.out.print(" ");
                }
                for (int j = 0; j < this.grid[i].length; j++) {

                    System.out.print("| "+this.grid[i][j] + " ");
                }
                System.out.print("|");
                System.out.println();
            }
        }
        System.out.println();
    }
}
