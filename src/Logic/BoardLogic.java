package Logic;

import Entity.Board;
import Entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardLogic {
    private boolean isFirstMove = true;
    private Board board;
    public BoardLogic(Board board) {
        this.board = board;
    }
    public boolean getIsFirstMove() {
        return isFirstMove;
    }
    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }
    public boolean placeWorld(String word, boolean isHorizontal, int num, String letter , PlayerLogic playerLogic ){
        int letterIndex = Board.alphabet.indexOf(letter.toUpperCase());
        String[][] copiedGrid = board.getGrid();
        ArrayList<String> chevalet = playerLogic.getChevalet();
        boolean isLegal = worldExists(word);
        try {
            if (isHorizontal && isLegal){
                for (int i = 0; i < word.length(); i++) {

                    String l = word.substring(i,i+1);
                    chevalet = isLegal(l,copiedGrid[num][letterIndex+i],chevalet);
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
                    chevalet = isLegal(l,copiedGrid[num+i][letterIndex],chevalet);
                    if (chevalet == null){
                        isLegal = false;
                        break;
                    }
                    copiedGrid[num+i][letterIndex] = l;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Le mot que vous avez placer sort de la grille");
            isLegal = false;
        }
        catch (Exception e){
            System.out.println("Une erreur est survenue, veillez réessayer");
            isLegal = false;
        }

        if(isLegal){
            board.setGrid(copiedGrid);
            int points = playerLogic.addPoints(word);
            playerLogic.updateChevalet(chevalet);
            playerLogic.populateChevalet();
            System.out.println("Vous avez réussis a placer le mot : " + word + "\n Vous avez donc gagner " + points + " points");
            return true;
        }
        System.out.println("Le mot : '" + word + "' ne peut etre placer sur la grille");
        return false;

    }
    private boolean worldExists(String word){
        return true;
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

}
