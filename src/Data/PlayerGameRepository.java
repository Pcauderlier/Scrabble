package Data;

import Entity.Chevalet;
import Entity.Game;
import Entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerGameRepository {
    public boolean playerGameExists(Player player, Game game) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getConnection();
        try{
            String statement = "SELECT SCORE FROM playergame WHERE PLAYER_ID = ? AND GAME_ID = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1,player.getPlayerId());
            stmt.setInt(2, game.getGameID());
            rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);
        }
        return false;
    }

    public void updatePlayerGame(Player player, Game game,int score, String chevalet){
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        try{
            String statement = "UPDATE playergame SET SCORE = ?, CHEVALET = ?  WHERE PLAYER_ID = ? AND GAME_ID = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1,score);
            stmt.setString(2,chevalet);
            stmt.setInt(3,player.getPlayerId());
            stmt.setInt(4,game.getGameID());
            stmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, null);
        }
    }
    public void createPlayerGame(Player player, Game game, int playerIndex,int score, String chevalet){
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        try{
            String statement = "INSERT INTO playergame ( PLAYER_INDEX, SCORE, CHEVALET,PLAYER_ID, GAME_ID) VALUES (?, ?, ?, ?, ?);";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1,playerIndex);
            stmt.setInt(2,score);
            stmt.setString(3,chevalet);
            stmt.setInt(4,player.getPlayerId());
            stmt.setInt(5,game.getGameID());
            stmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, null);
        }
    }

}
