package GUI;

import Data.GameRepository;
import Entity.Board;
import Entity.LetterBag;
import Entity.Player;
import Logic.BoardLogic;
import Logic.GameLogic;
import Logic.PlayerLogic;

import java.util.ArrayList;
import java.util.Scanner;

public class GameProgress {
    private Board board;
    private PlayerLogic playerLogic;
    private BoardLogic boardLogic;
    private GameLogic gameLogic;
    private ArrayList<Player> players;
    private int playerIndex;
    private static Scanner sc;
    public GameProgress(){
        sc = new Scanner(System.in);
        gameLogic = new GameLogic(new GameRepository());
        playerLogic = new PlayerLogic();
        playerIndex= 0;

    }
    public void home(){
        System.out.println("Bienvenue dans Scrabble !");
        System.out.println("Voulez commencer une nouvelle partie (1) ou continuer une partie existante");
        int num = askNum();
        while(num != 1 || num != 2){
            System.out.println("Voulez commencer une nouvelle partie (1) ou continuer une partie existante");
            num = askNum();
        }
        if (num == 1){
            System.out.println("Lancement d'une nouvelle partie");
            this.startGame();
        }
        else if (num == 2){

        }
    }
    public void startGame(){
        LetterBag.initialise();
        initPlayers();
        initBoard();

        boolean keepGoing = true;
        while(keepGoing){
            keepGoing = playerTurn();
        }
        System.out.println("Fin de la partie");


    }
    private void initBoard(){
        board = new Board();
        boardLogic = new BoardLogic(board);
        gameLogic.createGame(board);
        board.printBoard();
    }
    private void initPlayers(){
        System.out.println("Combiens de joueurs ?");
        int nbPlayers = askNum();
        players = new ArrayList<>();
        for(int i = 0; i < nbPlayers; i++){
            System.out.println("Quel est le nom du Joueur : " + (i+1));
            String name = sc.nextLine();
            Player player = playerLogic.initPlayer(name);
            playerLogic.populateChevalet();

            players.add(player);
        }
    }
    private boolean playerTurn(){
        Player currentPlayer = players.get(playerIndex);
        playerLogic.setPlayer(currentPlayer);
        System.out.println(currentPlayer);
        String str = new String("");
        str += "Liste des actions possibles : \n";
        str += "(0) : Quitter le jeu \n";
        str += "(1) : Passer mon tour \n";
        str += "(2) : Echanger des lettres \n";
        str += "(3) : Placer un mot \n";
        str += "(4) : Voir le contenus de letterBag \n";
        str += "(5) : Voir le plateau de jeu \n";
        System.out.println(str);
        int choice = askNum();
        boolean keepGoing = true;
        switch (choice){
            case 0:
                keepGoing = false;
                break;
            case 1:
                nextTurn();
                break;
            case 2:
                playerLogic.tradeLetters();
                break;
            case 3:
                if (placeWord(currentPlayer)){
                    nextTurn();
                }
                break;
            case 4:
                LetterBag.printLetterBag();
                break;
            case 5:
                board.printBoard();
                break;

            default:
                break;
        }
        return keepGoing;
    }
    public boolean placeWord(Player currentPlayer){
        System.out.println("Veillez entrer le mot que vous voulez jouer :");
        String word = sc.nextLine().toUpperCase();
        if (boardLogic.getIsFirstMove()){
            boolean isOk =  boardLogic.placeWorld(word,true,8,"G",playerLogic);
            if(isOk){
                boardLogic.setIsFirstMove(false);
            }
            return isOk;
        }
        System.out.println("Veillez entrer le sens du mot (v) -> vertical, (h) -> horizontal :");
        String dir = sc.nextLine().toUpperCase();
        while(!(dir.equals("V") || dir.equals("H"))){
            System.out.println("Veillez entrer le sens du mot (v) -> vertical, (h) -> horizontal :");
            dir = sc.nextLine().toUpperCase();
        }
        System.out.println("Veillez entrer la colonne (lettre) de la premiere lettre :");
        String letter = sc.nextLine().toUpperCase();
        System.out.println("Veillez entrer la ligne (chiffre) de la premiere lettre :");
        int num = askNum();

        return boardLogic.placeWorld(word,dir.equals("H"),num,letter,playerLogic);

    }
    private void nextTurn(){
        int nextIndex = playerIndex + 1;
        playerIndex = nextIndex >= players.size() ? 0 : nextIndex;
        System.out.println("Player index : " + playerIndex);
        System.out.println("playersize : " + players.size());

    }
    public static int askNum(){
        int num;
        while (true) {
            if (sc.hasNextInt()) {
                num = sc.nextInt();
                sc.nextLine();
                return num;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                sc.nextLine();
            }
        }
    }
    public static void main (String[] args){
        GameProgress game = new GameProgress();
        game.startGame();
    }
}
