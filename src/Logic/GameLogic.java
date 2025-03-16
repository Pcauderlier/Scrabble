package Logic;

import Data.GameRepository;
import Data.PlayerGameRepository;
import Entity.Board;
import Entity.Game;
import Entity.LetterBag;
import Entity.Player;
import GUI.GameProgress;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
    private GameRepository gameRepository;
    private BoardLogic boardLogic;
    private PlayerLogic playerLogic;
    private PlayerGameService playerGameService;
    private Scanner sc;
    private final Game game;

    public GameLogic(Game game){
        gameRepository = new GameRepository();
        playerLogic = new PlayerLogic();
        playerGameService = new PlayerGameService();
        this.game = game;
        sc = new Scanner(System.in);
    }
    public void createGame(Board board){
        String jsonBoard = board.convertBoardToJson();
        String letterBag = LetterBag.convertLetterBagToJson();
        int gameId = gameRepository.createGame(jsonBoard, letterBag);
        game.setBoard(board);
        game.setGameID(gameId);
    }

    public List<Integer> getGameIDs(){
        return gameRepository.getGameIDs();
    }
    public void newGame(){
        initBoard();
        LetterBag.initialise();
        createGame(game.getBoard());
        initPlayers();
        startGame();

    }
    public void loadGame(){
        List<Integer> gameIds = getGameIDs();
        System.out.println("Liste des parties en cours : ");
        for(Integer gameId : gameIds){
            System.out.println(gameId);
        }
        System.out.println("Choisir un ID de partie (-1 pour une nouvelle partie)");
        int id = GameProgress.askNum();
        if (id == -1){
            newGame();
        }
        else{

            HashMap gameData = gameRepository.getGame(id);
            if (gameData == null){
                System.out.println("Le game n'existe pas");
                loadGame();
                return;
            }
            game.setGameID(id);
            Board board = new Board();
            board.convertJsonToBoard((String) gameData.get("board"));
            game.setBoard(board);
            boardLogic = new BoardLogic(board);
            boardLogic.setIsFirstMove(false);
            board.printBoard();
            LetterBag.convertJsonToLetterBag((String) gameData.get("letterBag"));
            game.setPlayerIndex((Integer) gameData.get("currentPlayer"));
            ArrayList<Player> players = playerLogic.loadPlayers(id);
            game.setPlayers(players);
            startGame();

        }
    }
    private void initBoard(){
        Board board = new Board();
        boardLogic = new BoardLogic(board);
        game.setBoard(board);
        board.printBoard();
    }
    public void startGame(){
        boolean keepGoing = true;
        while(keepGoing){
            keepGoing = playerTurn();
        }
        System.out.println("Fin de la partie");


    }

    private void initPlayers(){
        System.out.println("Combiens de joueurs ?");
        int nbPlayers = GameProgress.askNum();
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < nbPlayers; i++){
            System.out.println("Quel est le nom du Joueur : " + (i+1));
            String name = sc.nextLine();
            Player player = playerLogic.initPlayer(name);
            playerLogic.populateChevalet();

            players.add(player);
        }
        game.setPlayers(players);
    }
    private void saveAndNextTurn(){
        /**
         * Créer ou update le playerGame
         * update game avec le board et le letterbag
         *
         */
        playerGameService.upsertPlayerGame(game);
        gameRepository.updateGame(game,true);
        game.nextTurn();

    }
    private void endGame(){
        System.out.println("Fin de la partie");
        /**
         * Update le score Tot du joueur
         * mettre la game en active = false
         */
        for (Player player : game.getPlayers()){
            playerLogic.setPlayer(player);
            playerLogic.updatePlayerPoints();
        }
        gameRepository.updateGame(game,false);

    }
    private boolean playerTurn(){
        Player currentPlayer = game.getCurrentPlayer();
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
        str += "(6) : Mettre fin a la partie \n";
        System.out.println(str);
        int choice = GameProgress.askNum();
        boolean keepGoing = true;
        switch (choice){
            case 0:
                keepGoing = false;
                break;
            case 1:
                saveAndNextTurn();
                break;
            case 2:
                playerLogic.tradeLetters();
                break;
            case 3:
                if (placeWord()){
                    saveAndNextTurn();
                }
                break;
            case 4:
                LetterBag.printLetterBag();
                break;
            case 5:
                game.getBoard().printBoard();
                break;
            case 6:
                endGame();
                break;
            default:
                break;
        }
        return keepGoing;
    }
    public boolean placeWord(){
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
        int num = GameProgress.askNum();

        return boardLogic.placeWorld(word,dir.equals("H"),num,letter,playerLogic);

    }

}
