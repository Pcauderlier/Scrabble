package Data;

import Entity.Board;
import Entity.Game;
import Entity.LetterBag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameRepository {
    public int createGame(String jsonBoard , String letterBag){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        int gameID = 0;
        try{
            String statement = "INSERT INTO Game (BOARD, CURRENT_PLAYER_INDEX, LETTER_BAG,IS_ACTIVE) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(statement,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, jsonBoard);
            stmt.setInt(2, 0);
            stmt.setString(3, letterBag);
            stmt.setBoolean(4, true);
            if (stmt.executeUpdate() > 0){
                rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    gameID = rs.getInt(1);
                    return gameID;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);
        }
        return gameID;
    }
    public List<Integer> getGameIDs(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        List<Integer> gameIDs = new ArrayList<Integer>();
        try{
            String statement = "SELECT ID FROM Game WHERE IS_ACTIVE = 1";
            stmt = conn.prepareStatement(statement);
            rs = stmt.executeQuery();
            while (rs.next()){
                gameIDs.add(rs.getInt(1));
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);

        }
        return gameIDs;
    }
    public HashMap getGame(int gameID){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        HashMap game = null;
        try{
            String statement = "SELECT * FROM Game WHERE ID = ?";
            stmt = conn.prepareStatement(statement);
            stmt.setInt(1, gameID);
            rs = stmt.executeQuery();
            while (rs.next()){
                game = new HashMap();
                game.put("board",rs.getString(2));
                game.put("currentPlayer",rs.getInt(3));
                game.put("letterBag",rs.getString(4));

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return game;
    }
    public void updateGame(Game game, boolean active){
        String board = game.getBoard().convertBoardToJson();
        int currentPlayerindex = game.getPlayerIndex();
        String letterbag = LetterBag.convertLetterBagToJson();

        PreparedStatement stmt = null;
        Connection conn = Conexion.getConnection();
        try{
            String Statement = "UPDATE Game SET BOARD = ? , CURRENT_PLAYER_INDEX = ? , LETTER_BAG = ? , IS_ACTIVE = ? WHERE ID = ?";
            stmt = conn.prepareStatement(Statement);
            stmt.setString(1, board);
            stmt.setInt(2, currentPlayerindex);
            stmt.setString(3, letterbag);
            stmt.setBoolean(4, active);
            stmt.setInt(5, game.getGameID());
            stmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt,null);
        }

    }
}
