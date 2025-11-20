package MediPass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLiteManager {

    private static final String URL = "jdbc:sqlite:data/mediPass.db";

    public static void  connect(){
        Connection conn =  null;
        try{
            conn = DriverManager.getConnexion(URL);
            System.out.println("Connexion a SQLite 🆗🆗!");

        }catch (SQLException ex){
            System.out.println(ex.getMessage());


        }
        

    }
}
