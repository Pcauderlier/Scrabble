package GameLogic;

import GameFrontEnd.Board;
import GameFrontEnd.GameProgress;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public int score;
    private final ArrayList<String> chevalet ;
    public Player(String name ) {
        this.chevalet = new ArrayList<>();
        this.name = name;
        this.score = 0;
    }
    public void populateChevalet(){
        int nbLetters = 7 - chevalet.size();
        for(int i = 0; i < nbLetters; i++){
            chevalet.add(LetterBag.drawLetters());
        }
    }

    public void dropLetter(String letter){
        this.dropLetter(letter,true);
    }
    public void dropLetter(String letter , boolean backToBag){
        if (chevalet.contains(letter)){
            chevalet.remove(letter);
            if (backToBag){
                LetterBag.modifyOccurence(letter);
            }
        }
    }
    public int addPoints(String word){
        int points = LetterBag.getWorldPoints(word);
        score += points;
        return points;
    }
    public ArrayList<String> getChevalet(){
        return new ArrayList<>( this.chevalet);
    }
    public void updateChevalet(ArrayList<String> newChevalet){
        this.chevalet.clear();
        this.chevalet.addAll(newChevalet);
    }
    private String printChevalet(){
        String str = new String("");
        for(String s : chevalet){
            str += "| "+s + " ";
        }

        str += "|\n";
        for(int i = 0; i < chevalet.size(); i++){
            str += "| " + i + " ";
        }
        return str;
    }
    public void tradeLetters(){
        String str = new String("Veillez entrer le numéros de la lettre que vous voulez retirer du chevalet : (-1 pour quitter)");
        System.out.println(str);
        int letterIndex = GameProgress.askNum();
        while(letterIndex > -1 && chevalet.size() > 0){
            if (letterIndex > chevalet.size()-1){
                System.out.println("Aucune lettre pour cette index");
                continue;
            }
            this.dropLetter(chevalet.get(letterIndex));
            System.out.println("Chevalet : ");
            System.out.println(this.printChevalet());
            System.out.println(str);
            letterIndex = GameProgress.askNum();
        }
        this.populateChevalet();
        System.out.println(this.printChevalet());
    }

    public String toString(){
        String str = new String("");
        str += "Joueur : " + this.name + "\n";
        str += "Score : " + this.score + "\n";
        str += "Chevalet : \n";
        str += this.printChevalet() + "\n";

        return str;
    }


}
