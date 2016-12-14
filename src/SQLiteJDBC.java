/**
 * Created by KaguyaXD on 2016-11-23.
 */
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import javax.print.DocFlavor;
import java.sql.*;

public class SQLiteJDBC {

    static String url = "jdbc:sqlite:magasin_musique.db";
    static Connection conn = connect();

    private static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Base de donné connecté.");
        return conn;
    }

    public static void initialize() {
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "CREATE TABLE Personnes" +
                    "(ID INTEGER PRIMARY KEY     AUTOINCREMENT     NOT NULL," +
                    " PRENOM         TEXT    NOT NULL, " +
                    " NOM            TEXT    NOT NULL, " +
                    " MDP            TEXT    NOT NULL, " +
                    " EMAIL          TEXT    NOT NULL, " +
                    " TEL            INT     NOT NULL, " +
                    " ADRESSE        TEXT    NOT NULL, " +
                    " TYPE           TEXT    NOT NULL, " +
                    " TOTAL_ACHAT    INT, " +
                    " TOTAL_VENTE    INT)";
            stmt.executeUpdate(sql);
            sql =   "CREATE TABLE Produits" +
                    "(ID INTEGER PRIMARY KEY     AUTOINCREMENT     NOT NULL," +
                    " TYPE           TEXT    NOT NULL, " +
                    " MARQUE         TEXT    NOT NULL, " +
                    " DESC           TEXT    NOT NULL, " +
                    " PRIX           REAL    NOT NULL, " +
                    " PRIX_COS       REAL    NOT NULL, " +
                    " QUANTITE       INT     NOT NULL)";
            stmt.executeUpdate(sql);
            sql =   "INSERT INTO Personnes (PRENOM,NOM,MDP,EMAIL,TEL,ADRESSE,TYPE) " +
                    "VALUES ('System','Root','mansys','admin@admin.com','1234567890','Drummondville','Admin');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static String[][] get_person(String field,String value){
        int max = 50;
        PreparedStatement pstmt = null;
        String[][] persons = new String[max][9];
        try{
            String sql = "SELECT * FROM Personnes " +
                         "WHERE " + field + " = ?";
            pstmt = conn.prepareStatement(sql);
            if(field == "TEL" || field == "ID"){
                pstmt.setInt(1,Integer.parseInt(value));
            }else {
                pstmt.setString(1,value);
            }
            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()){
                if (i <= max) {
                    persons[i][0] = String.valueOf(rs.getInt("ID"));
                    persons[i][1] = rs.getString("PRENOM");
                    persons[i][2] = rs.getString("NOM");
                    persons[i][3] = rs.getString("MDP");
                    persons[i][4] = rs.getString("EMAIL");
                    persons[i][5] = String.valueOf(rs.getInt("TEL"));
                    persons[i][6] = rs.getString("ADRESSE");
                    persons[i][7] = rs.getString("TYPE");
                    if (persons[i][7] == "Client" || persons[i][7] == "Employe") {
                        persons[i][8] = String.valueOf(rs.getInt("TOTAL_ACHAT"));
                        if (persons[i][7] == "Employe") {
                            persons[i][9] = String.valueOf(rs.getInt("TOTAL_VENTE"));
                        }
                    }
                    i++;
                }
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return persons;
    }

    public static void create_user(){

    }

    public static void close(){
        try{
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Base de donné déconnecté.");
    }

    public static boolean authenticate(String[] credentials) {
        boolean successful = false;
        PreparedStatement pstmt = null;
        try{
            String sql = "SELECT * FROM Personnes " +
                    "WHERE ID = ? AND MDP = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(credentials[0]));
            pstmt.setString(2,credentials[1]);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                successful = true;
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return successful;
    }
}
