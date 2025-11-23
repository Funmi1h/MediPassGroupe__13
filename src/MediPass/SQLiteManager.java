package MediPass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import MediPass.Consultation.statuts;


public class SQLiteManager {

    private static final String URL = "jdbc:sqlite:data/mediPass.db";

    public static Connection  connect(){
        Connection conn =  null;
        try{
            conn = DriverManager.getConnection(URL);
            System.out.println("Connexion a SQLite 🆗🆗!");

        }catch (SQLException ex){
            System.err.println("❌❌ Erreur de connexion à la base de données ❌❌");
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(conn != null){
                    conn.close();
                }

            }catch(SQLException ex){
                System.out.println(ex.getMessage());

            }
        }
        return conn;
        
    }

   
    public static Connection closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

         return conn;
    }


    public static void creerTableUtilisateur(){
        String sql = "CREATE TABLE IF NOT EXISTS Utilisateur("
        + "idUtilisateur INTEGER PRIMARY KEY, "
        + "nom TEXT,"
        + "prenom TEXT, "
        + "role TEXT NOT NULL,"
        + "motDePasseHash TEXT NOT NULL,"
        + "doitChangerMdp INTEGER DEFAULT 1"
        + ");";

        try {
            Connection conn = connect();
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   
}
