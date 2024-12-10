package GameLogic;

import java.sql.Struct;
import java.util.ArrayList;

public class Player {
    public String name;
    public int score;
    public ArrayList<String> chevalet = new ArrayList<>();
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public void populateChevalet(){
        int nbLetters = 7-chevalet.size();
        for(int i = 0; i < nbLetters; i++){
            chevalet.add(LetterBag.drawLetters());
        }
    }
    public void dropLetter(String letter){
        if (chevalet.contains(letter)){
            chevalet.remove(letter);
            LetterBag.modifyOccurence(letter,false);
        }
    }
    public void addPoints(int points){
        score += points;
    }


}
