package Logic;

import Data.GameRepository;
import Entity.Board;
import Entity.LetterBag;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;

public class GameLogic {
    GameRepository gameRepository;
    public GameLogic(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    public void createGame(Board board){
        String jsonBoard = convertBoardToJson(board);
        gameRepository.createGame(jsonBoard);
    }
    private String convertBoardToJson(Board board){
        Gson gson = new Gson();
        return gson.toJson(board.getGrid());
    }
}
