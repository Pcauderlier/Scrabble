package Data;

import Entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
