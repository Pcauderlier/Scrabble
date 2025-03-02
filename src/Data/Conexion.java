package Data;
import java.sql.*;
final public class Conexion {
    private static final String DBURL = "jdbc:mysql://localhost:3306/scrabble";
    private static final String DBUSER = "root";
    private static final String DBPASS = "root";
    private static Connection CON;


    public static Connection getConnection() {
        if (CON == null) {
            try {
                CON = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            } catch (SQLException e) {
                System.out.println("Impossible de charger le pilote JDBC MySQL " + e.getMessage());
            }
        }
        return CON;
    }
    public static void closeConnection() {
        if (CON != null) {
            try {
                CON.close();
                CON = null;
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connection : " + e.getMessage());
            }
        }
    }
    public static void closeEverything(PreparedStatement stmt , ResultSet res) {
        if (stmt != null) {
            try{
                stmt.close();
            }
            catch (SQLException e) {
                System.out.println("Erreur lors de la fermeure du prepared statement : " + e.getMessage());
            }
        }
        if (res != null) {
            try{
                res.close();
            }
            catch (SQLException e) {
                System.out.println("Erreur lors de la fermeure du response : " + e.getMessage());
            }
        }
        Conexion.closeConnection();
    }
}
