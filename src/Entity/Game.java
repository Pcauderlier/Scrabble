package Entity;

import Data.GameRepository;
import GUI.GameProgress;
import Logic.BoardLogic;
import Logic.GameLogic;
import Logic.PlayerLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private int gameID;
    private ArrayList<Player> players;
    private int playerIndex;
    public Game(){
        playerIndex = 0;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public Player getCurrentPlayer(){
        return players.get(playerIndex);
    }
    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    public void nextTurn(){
        int nextIndex = playerIndex + 1;
        playerIndex = nextIndex >= players.size() ? 0 : nextIndex;
    }
}
