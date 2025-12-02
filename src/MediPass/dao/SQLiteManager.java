package MediPass;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import MediPass.Consultation.statuts;


public class SQLiteManager {

    private static final String URL = "jdbc:sqlite:data/mediPass.db";

    public static Connection  connect(){

        
        Connection conn =  null;
        try{
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver SQLite introuvable : vérifiez le JAR dans lib/");
                e.printStackTrace();
            }
           
            conn = DriverManager.getConnection(URL);
            System.out.println("Connexion a SQLite 🆗🆗!");

        }catch (SQLException ex){
            System.err.println("❌❌ Erreur de connexion à la base de données ❌❌");
            System.out.println(ex.getMessage());
        }
        return conn;
        
    }
    

   
    public static void closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }

        }catch(SQLException ex){
            System.out.println("Erreur lors de la fermeture de la connexion avec la base de données" + ex.getMessage());
        }

       
    }


    public static void initialiserBaseDeDonnees(){
        String sqlUtilisateur  = "CREATE TABLE IF NOT EXISTS Utilisateur("
        + "idUtilisateur TEXT PRIMARY KEY, "
        + "nom TEXT,"
        + "prenom TEXT, "
        + "role TEXT NOT NULL,"
        + "motDePasseHash TEXT NOT NULL,"
        + "doitChangerMdp INTEGER DEFAULT 1"
        + ");";
        
        String sqlPatient = "CREATE TABLE IF NOT EXISTS Patient ("
        		+ "idUtilisateur TEXT NOT NULL PRIMARY KEY,"
        		+ "idDossier TEXT PRIMARY KEY,"
        		+ "age TEXT,"
        		+"taille REAL,"
        		+ "poids REAL,"
        		+ "FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur),"
        		+ "FOREIGN KEY (idDossier) REFERENCES DossierMedical(idDossier)"
        		
        		+")";
        
        String sqlProfessionnelSante = "CREATE TABLE IF NOT EXISTS PATIENT("
        		+ "idUtilisateur TEXT PRIMARY KEY,"
        		+ "specialite TEXT NOT NULL"
        		+ "FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur)"
        		+ ")";
        String sqlDossierMedical = "CREATE TABLE IF NOT EXISTS DossierMedical ("
        		+ "idDossier TEXT NOT NULL PRIMARY KEY,"
        		+ "dateCreation TEXT NOT NULL "
        		+ ")";
       String sqlConsultation = "CREATE TABLE IF NOT EXISTS Consultation ("
                + "idConsultation TEXT PRIMARY KEY,"
                + "idDossier TEXT NOT NULL,"
                + "idProfessionnelSante TEXT NOT NULL,"
                + "dateConsultation TEXT NOT NULL,"
                + "motif TEXT,"
                + "statut TEXT,"
                + "FOREIGN KEY(idDossier) REFERENCES DossierMedical(idDossier),"
                + "FOREIGN KEY(idProfessionnelSante) REFERENCES ProfessionnelSante(idUtilisateur)"
                + ");";
        
                String sqlObservation = "CREATE TABLE IF NOT EXISTS Observation (" 
                + "idObservation INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + "idConsultation TEXT NOT NULL, " 
                + "contenu TEXT NOT NULL, " 
                + "FOREIGN KEY(idConsultation) REFERENCES Consultation(idConsultation)" 
                + ");";

        //Exécution des requetes
        
        try (Connection conn = connect();
            	Statement stmt = conn.createStatement()){
        	stmt.execute(sqlUtilisateur);
        	stmt.execute(sqlProfessionnelSante);
        	stmt.execute(sqlPatient);
        	stmt.execute(sqlDossierMedical);
            stmt.execute(sqlObservation);
        	System.out.println("Base de données initialisée avec succès. Table vérifiées")
        	
        	
        	
        }catch(SQLException e) {
        	System.err.println("Erreur lors de l'exécution des requêtes SQL d'initialisation: " + e.getMessage());
        }

        Connection conn = connect();

    }
    
   
}
