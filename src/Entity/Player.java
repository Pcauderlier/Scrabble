package Entity;

public class Player {
    private int playerId;
    private int globalScore;
    private String name;
    private int score;
    private final Chevalet chevalet;

    public Player(String name, int playerId , int globalScore ) {
        this.name = name;
        this.playerId = playerId;
        this.globalScore = globalScore;
        this.score = 0;
        this.chevalet = new Chevalet();
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(int globalScore) {
        this.globalScore = globalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Chevalet getChevalet() {
        return chevalet;
    }

    @Override
    public String toString() {
        return "Joueur : " + this.name + "\n" +
                "Score : " + this.score + "\n" +
                "Chevalet : \n" +
                chevalet.afficher() + "\n";
    }
}
