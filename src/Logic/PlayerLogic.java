package Logic;

import Entity.LetterBag;
import Entity.Player;
import GUI.GameProgress;

import java.util.ArrayList;

public class PlayerLogic {
    private Player currentPlayer;
    private LetterBagLogic letterLogic;
    public PlayerLogic(){
        letterLogic = new LetterBagLogic();
    }
    public void setPlayer(Player player){
        currentPlayer = player;
    }
    public void updateChevalet(ArrayList<String> chevalet){
        currentPlayer.updateChevalet(chevalet);
    }
    public ArrayList<String> getChevalet(){
        return currentPlayer.getChevalet();
    }
    public void populateChevalet() {
        ArrayList<String> chevalet = currentPlayer.getChevalet();
        int nbLetters = 7 - chevalet.size();
        for(int i = 0; i < nbLetters; i++){
            chevalet.add(letterLogic.drawLetters());
        }
        currentPlayer.updateChevalet(chevalet);
    }
    public void dropLetter(String letter) {
        this.dropLetter(letter,true);
    }
    public void dropLetter(String letter,boolean backToBag){
        ArrayList<String> chevalet = currentPlayer.getChevalet();
        if (chevalet.contains(letter)){
            chevalet.remove(letter);
            if (backToBag){
                letterLogic.modifyOccurence(letter);
            }
        }
        currentPlayer.updateChevalet(chevalet);
    }

    public int addPoints(String word){
        int initialPoints =currentPlayer.getScore();
        int points = initialPoints + letterLogic.getWorldPoints(word);
        currentPlayer.setScore(points);
        return points;
    }
    public void tradeLetters(){
        String str = new String("Veillez entrer le numéros de la lettre que vous voulez retirer du chevalet : (-1 pour quitter)");
        System.out.println(str);
        int letterIndex = GameProgress.askNum();
        while(letterIndex > -1 && currentPlayer.chevalet.size() > 0){
            if (letterIndex > currentPlayer.chevalet.size()-1){
                System.out.println("Aucune lettre pour cette index");
                System.out.println(str);
                letterIndex = GameProgress.askNum();

                continue;
            }
            dropLetter(currentPlayer.getLetter(letterIndex));
            System.out.println("Chevalet : ");
            System.out.println(currentPlayer.printChevalet());
            System.out.println(str);
            letterIndex = GameProgress.askNum();
        }
        populateChevalet();
        System.out.println(currentPlayer.printChevalet());
    }
}
