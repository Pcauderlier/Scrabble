package GameFrontEnd;

import GameLogic.LetterBag;
import GameLogic.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameProgress {
    private static Board board;
    private static ArrayList<Player> players;
    private static int playerIndex;
    private static Scanner sc;
    public static void startGame(){
        sc = new Scanner(System.in);
        System.out.println("Bienvenue dans Scrabble !");
         playerIndex= 0;
        LetterBag.initialise();
        initBoard();
        initPlayers();
        boolean keepGoing = true;
        while(keepGoing){
            keepGoing = playerTurn();
        }
        System.out.println("Fin de la partie");


    }
    private static void initBoard(){
        System.out.println("Quelle est la taille de la grille souhaité (15 recommandé) ? ");
        board = new Board(askNum());
        board.printBoard();
    }
    private static void initPlayers(){
        System.out.println("Combiens de joueurs ?");
        int nbPlayers = askNum();
        players = new ArrayList<>();
        for(int i = 0; i < nbPlayers; i++){
            System.out.println("Quel est le nom du Joueur : " + (i+1));
            String name = sc.nextLine();
            Player player = new Player(name);
            player.populateChevalet();
            players.add(player);
        }
    }
    private static boolean playerTurn(){
        Player currentPlayer = players.get(playerIndex);
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
                currentPlayer.tradeLetters();
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
    public static boolean placeWord(Player currentPlayer){
        System.out.println("Veillez entrer le mot que vous voulez jouer :");
        String word = sc.nextLine().toUpperCase();
        System.out.println("Veillez entrer le sens du mot (v) -> vertical, (h) -> horizontal :");
        String dir = sc.next().toUpperCase();
        while(!(dir.equals("V") || dir.equals("H"))){
            System.out.println("Veillez entrer le sens du mot (v) -> vertical, (h) -> horizontal :");
            dir = sc.nextLine().toUpperCase();
        }
        System.out.println("Veillez entrer la colonne (lettre) de la premiere lettre :");
        String letter = sc.nextLine().toUpperCase();
        System.out.println("Veillez entrer la ligne (chiffre) de la premiere lettre :");
        int num = askNum();

        return board.placeWorld(word,dir.equals("H"),num,letter , currentPlayer);

    }
    private static void nextTurn(){
        int nextIndex = playerIndex + 1;
        playerIndex = (nextIndex) > players.size() ? 0 : (nextIndex);

    }
    public static int askNum(){
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }
}
