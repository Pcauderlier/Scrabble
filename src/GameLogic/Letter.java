package GameLogic;

public class Letter {
    public String name;
    private int nbPoints;
    public int nbOccurence;
    boolean isEmpty = false;
    public Letter(String letter, int nbPoints, int nbOccurence) {
        this.name = letter;
        this.nbPoints = nbPoints;
        this.nbOccurence = nbOccurence;
    }
    public int getPoints(){
        return this.nbPoints;
    }
    public void addOccurence(){
        this.nbOccurence++;
    }
    public void removeOccurence(){
        this.nbOccurence--;
    }
    public String toString(){
        return this.name + " " + this.nbOccurence;
    }
}
