package Logic;

import Data.PlayerRepository;
import Entity.Player;
import Entity.Chevalet;
import GUI.GameProgress;

import java.util.ArrayList;
import java.util.List;

public class PlayerLogic {
    private Player currentPlayer;
    private LetterBagLogic letterLogic;
    private final PlayerRepository playerRepository;

    public PlayerLogic() {
        letterLogic = new LetterBagLogic();
        playerRepository = new PlayerRepository();
    }

    public Player initPlayer(String name) {
        Player player = playerRepository.getPlayerByName(name);
        if (player == null) {
            player = playerRepository.createPlayer(name);
        }
        currentPlayer = player;
        return player;
    }
    public void setPlayer(Player player) {
        currentPlayer = player;
    }

    public List<String> getChevalet() {
        return currentPlayer.getChevalet().getLettres();
    }

    public void populateChevalet() {
        Chevalet chevalet = currentPlayer.getChevalet();
        int nbLetters = 7 - chevalet.taille();
        for (int i = 0; i < nbLetters; i++) {
            chevalet.ajouterLettre(letterLogic.drawLetters());
        }
    }
    public void updateChevalet(List<String> nouvellesLettres) {
        Chevalet chevalet = currentPlayer.getChevalet();
        chevalet.update(nouvellesLettres);
    }

    public void dropLetter(String letter) {
        this.dropLetter(letter, true);
    }

    public void dropLetter(String letter, boolean backToBag) {
        Chevalet chevalet = currentPlayer.getChevalet();
        if (chevalet.getLettres().contains(letter)) {
            chevalet.retirerLettre(letter);
            if (backToBag) {
                letterLogic.modifyOccurence(letter);
            }
        }
    }

    public int addPoints(String word) {
        int initialPoints = currentPlayer.getScore();
        int points = initialPoints + letterLogic.getWorldPoints(word);
        currentPlayer.setScore(points);
        return points;
    }

    public void tradeLetters() {
        System.out.println("Veuillez entrer le numéro de la lettre que vous voulez retirer du chevalet : (-1 pour quitter)");
        int letterIndex = GameProgress.askNum();

        Chevalet chevalet = currentPlayer.getChevalet();

        while (letterIndex > -1 && chevalet.taille() > 0) {
            if (letterIndex >= chevalet.taille()) {
                System.out.println("Aucune lettre pour cet index");
            } else {
                dropLetter(chevalet.getLettre(letterIndex));
            }
            System.out.println("Chevalet : ");
            System.out.println(chevalet.afficher());
            System.out.println("Veuillez entrer le numéro de la lettre que vous voulez retirer (-1 pour quitter) :");
            letterIndex = GameProgress.askNum();
        }

        populateChevalet();
        System.out.println(chevalet.afficher());
    }
    public void updatePlayerPoints(){
        playerRepository.updatePlayer(currentPlayer);
    }
    public ArrayList<Player> loadPlayers(int gameId){
        ArrayList<Player> players = playerRepository.getGamePlayers(gameId);

        return players;
    }
}
