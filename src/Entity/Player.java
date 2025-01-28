package Entity;

import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    public final ArrayList<String> chevalet;
    public Player(String name ) {
        this.chevalet = new ArrayList<>();
        this.name = name;
        this.score = 0;
    }


    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public ArrayList<String> getChevalet(){
        return new ArrayList<>( this.chevalet);
    }
    public void updateChevalet(ArrayList<String> newChevalet){
        this.chevalet.clear();
        this.chevalet.addAll(newChevalet);
    }
    public String getLetter(int index){
        return this.chevalet.get(index);
    }
    public String printChevalet(){
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


    public String toString(){
        String str = new String("");
        str += "Joueur : " + this.name + "\n";
        str += "Score : " + this.score + "\n";
        str += "Chevalet : \n";
        str += this.printChevalet() + "\n";

        return str;
    }
}
