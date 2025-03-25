package Data;

import Entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerRepository {
    public Player createPlayer(String name){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        Player player = null;
        try{
            String statement = "INSERT INTO Player (NAME) VALUES (?)";
            stmt = conn.prepareStatement(statement,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            if (stmt.executeUpdate() == 1){
                rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    player = new Player(name, rs.getInt(1),0);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);
        }
        return player;
    }
    public Player getPlayerByName(String name){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        Player player = null;
        try{
            String statement = "SELECT * FROM Player WHERE NAME = ?";
            stmt = conn.prepareStatement(statement);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if(rs.next()){
                player = new Player(name, rs.getInt("ID"),rs.getInt("SCORE_TOT"));

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return player;
    }
    public void updatePlayer(Player player){
        PreparedStatement stmt = null;
        Connection conn = Conexion.getConnection();
        int scoreTot = player.getScore() + player.getGlobalScore();
        try{
            String statement = "UPDATE Player SET SCORE_TOT = ? WHERE ID = ?";
            stmt = conn.prepareStatement(statement);
            stmt.setInt(1, scoreTot);
            stmt.setInt(2,player.getPlayerId());
            stmt.executeUpdate();

        }
        catch(Exception e){
            System.out.printf(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, null);
        }
    }
    public ArrayList<Player> getGamePlayers(int gameId){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = Conexion.getConnection();
        ArrayList<Player> players = new ArrayList<>();
        try{
            String statement = "SELECT * FROM PlayerGame pg JOIN Player p ON pg.PLAYER_ID = p.ID WHERE pg.GAME_ID = ? ORDER BY pg.PLAYER_INDEX ASC";
            stmt = conn.prepareStatement(statement);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            while(rs.next()){
                Player player = new Player(
                        rs.getString("NAME"),
                        rs.getInt("PLAYER_ID"),
                        rs.getInt("SCORE_TOT")
                );
                player.setScore(rs.getInt("SCORE"));
                player.getChevalet().jsonToChevalet(rs.getString("CHEVALET"));
                players.add(player);
            }
        }
        catch(Exception e){
            System.out.printf(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, rs);
        }
        return players;
    }
}
