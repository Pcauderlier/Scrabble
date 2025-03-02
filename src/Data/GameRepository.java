package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    public int createGame(String jsonBoard){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        int gameID = 0;
        try{
            String statement = "INSERT INTO Game (BOARD, CURRENT_PLAYER_INDEX, LETTER_BAG,IS_ACTIVE) VALUES (?,?,?,?)";
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
            String statement = "SELECT ID FROM Game";

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);

        }
        return gameIDs;
    }
}
