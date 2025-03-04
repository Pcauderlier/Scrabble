package Entity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private String[][] grid;
    private int gameId;
    public static final ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));;
    public Board() {
        this.resetBoard(15);


    }
    private void resetBoard(int size) {
        this.grid = new String[size][size];
        // Initialisation de chaque cellule avec une valeur par défaut
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = "-"; // Case vide
            }
        }
    }


    public String[][] getGrid() {
        String[][] copy = new String[this.grid.length][this.grid[0].length];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                copy[i][j] = this.grid[i][j];
            }
        }
        return copy;
    }
    public void setGrid(String[][] grid) {
        this.grid = grid;
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
    public String convertBoardToJson(){
        Gson gson = new Gson();
        return gson.toJson(grid);
    }
    public  void convertJsonToBoard(String jsonBoard){
        Gson gson = new Gson();
        String[][] grid = gson.fromJson(jsonBoard, String[][].class);
        setGrid(grid);
    }
}
