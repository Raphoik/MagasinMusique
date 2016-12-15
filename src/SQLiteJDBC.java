/**
 * Created by KaguyaXD on 2016-11-23.
 */
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import javax.print.DocFlavor;

/**
 * <h1>SQLiteJDBC</h1>
 * La classe <b>SQLiteJDBC</b> est la classe responsable de gérer toutes les relations entre la base de données et le programme.
 *
 * @author Raphael Valois
 * @since 20 décembre 2016
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class SQLiteJDBC {

    static private String url = "jdbc:sqlite:magasin_musique.db";
    static private Connection conn = connect();

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
                    " UTILISATEUR    TEXT    NOT NULL, " +
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
            sql =   "INSERT INTO Personnes (PRENOM,NOM,UTILISATEUR,MDP,EMAIL,TEL,ADRESSE,TYPE,TOTAL_ACHAT,TOTAL_VENTE) " +
                    "VALUES ('System','Root','Admin','mansys','admin@admin.com','1234567890','Drummondville','Admin',NULL,NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
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
                    "WHERE UTILISATEUR = ? AND MDP = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,credentials[0]);
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

    public static boolean valid_username(String username) {
        boolean valid = true;
        PreparedStatement pstmt = null;
        try{
            String sql = "SELECT * FROM Personnes " +
                    "WHERE UTILISATEUR = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                valid = false;
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return valid;
    }

    public static void add_user(Personne personne,String utilisateur,String mdp){
        PreparedStatement pstmt = null;
        ArrayList<String> person_info = personne.export_person_info();
        try{
            String sql =    "INSERT INTO Personnes (PRENOM,NOM,UTILISATEUR,MDP,EMAIL,TEL,ADRESSE,TYPE,TOTAL_ACHAT,TOTAL_VENTE) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,person_info.get(1));
            pstmt.setString(2,person_info.get(2));
            pstmt.setString(3,utilisateur);
            pstmt.setString(4,mdp);
            pstmt.setString(5,person_info.get(3));
            pstmt.setString(6,person_info.get(4));
            pstmt.setString(7,person_info.get(5));
            pstmt.setString(8,person_info.get(6));
            if (person_info.get(6).equals("Client") || person_info.get(6).equals("Employe")) {
                pstmt.setString(9,person_info.get(7));
                if (person_info.get(6).equals("Employe")) {
                    pstmt.setString(10,person_info.get(8));
                }else{
                    pstmt.setNull(10, java.sql.Types.INTEGER);
                }
            }else{
                pstmt.setNull(9, java.sql.Types.INTEGER);
                pstmt.setNull(10, java.sql.Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void add_product(Produit produit){
        PreparedStatement pstmt = null;
        ArrayList<String> product_info = produit.export_product_info();
        try{
            String sql =    "INSERT INTO Produits (TYPE,MARQUE,DESC,PRIX,PRIX_COS,QUANTITE) " +
                            "VALUES (?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product_info.get(0));
            pstmt.setString(2,product_info.get(1));
            pstmt.setString(3,product_info.get(2));
            pstmt.setString(4,product_info.get(3));
            pstmt.setString(5,product_info.get(4));
            pstmt.setString(6,product_info.get(5));
            pstmt.executeUpdate();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static ArrayList<Personne> get_person(String[] field,String[] value){
        PreparedStatement pstmt = null;
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        ArrayList<String> personne_info = new ArrayList<String>();
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM Personnes WHERE ("+field[i]+" = ?");
            for (i = 1;i < field.length;i++) {
                stringBuilder.append(" AND "+field[i]+" = ?");
            }
            stringBuilder.append(")");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            for (i = 0;i < field.length;i++) {
                pstmt.setString(i+1, value[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                personne_info = new ArrayList<String>();
                personne_info.add(rs.getString("ID"));
                personne_info.add(rs.getString("PRENOM"));
                personne_info.add(rs.getString("NOM"));
                personne_info.add(rs.getString("MDP"));
                personne_info.add(rs.getString("EMAIL"));
                personne_info.add(rs.getString("TEL"));
                personne_info.add(rs.getString("ADRESSE"));
                personne_info.add(rs.getString("TYPE"));
                if (personne_info.get(7).equals("Client") || personne_info.get(7).equals("Employe")) {
                    personne_info.add(rs.getString("TOTAL_ACHAT"));
                    if (personne_info.get(7).equals("Employe")) {
                        personne_info.add(rs.getString("TOTAL_VENTE"));
                    }
                }
                Personne singleperson = PersonFactory.makePerson(personne_info);
                personnes.add(singleperson);
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return personnes;
    }

    public static ArrayList<Personne> get_person(String field,String value){
        PreparedStatement pstmt = null;
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        ArrayList<String> personne_info = new ArrayList<String>();
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM Personnes WHERE ("+field+" = ?)");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                personne_info = new ArrayList<String>();
                personne_info.add(rs.getString("ID"));
                personne_info.add(rs.getString("PRENOM"));
                personne_info.add(rs.getString("NOM"));
                personne_info.add(rs.getString("MDP"));
                personne_info.add(rs.getString("EMAIL"));
                personne_info.add(rs.getString("TEL"));
                personne_info.add(rs.getString("ADRESSE"));
                personne_info.add(rs.getString("TYPE"));
                if (personne_info.get(7).equals("Client") || personne_info.get(7).equals("Employe")) {
                    personne_info.add(rs.getString("TOTAL_ACHAT"));
                    if (personne_info.get(7).equals("Employe")) {
                        personne_info.add(rs.getString("TOTAL_VENTE"));
                    }
                }
                Personne singleperson = PersonFactory.makePerson(personne_info);
                personnes.add(singleperson);
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return personnes;
    }

    public static ArrayList<Produit> get_product(String[] field,String[] value){
        PreparedStatement pstmt = null;
        ArrayList<Produit> products = new ArrayList<Produit>();
        ArrayList<String> product_info = new ArrayList<String>();
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM Produits WHERE ("+field[i]+" = ?");
            for (i = 1;i < field.length;i++) {
                stringBuilder.append(" AND "+field[i]+" = ?");
            }
            stringBuilder.append(")");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            for (i = 0;i < field.length;i++) {
                pstmt.setString(i+1, value[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                product_info = new ArrayList<String>();
                product_info.add(rs.getString("ID"));
                product_info.add(rs.getString("TYPE"));
                product_info.add(rs.getString("MARQUE"));
                product_info.add(rs.getString("DESC"));
                product_info.add(rs.getString("PRIX"));
                product_info.add(rs.getString("PRIX_COS"));
                product_info.add(rs.getString("ADRESSE"));
                product_info.add(rs.getString("QUANTITE"));
                Produit single_product = ProductFactory.makeProduct(product_info);
                products.add(single_product);
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return products;
    }

    public static ArrayList<Produit> get_product(String field,String value){
        PreparedStatement pstmt = null;
        ArrayList<Produit> products = new ArrayList<Produit>();
        ArrayList<String> product_info = new ArrayList<String>();
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM Produits WHERE ("+field+" = ?)");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(i+1, value);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                product_info = new ArrayList<String>();
                product_info.add(rs.getString("ID"));
                product_info.add(rs.getString("TYPE"));
                product_info.add(rs.getString("MARQUE"));
                product_info.add(rs.getString("DESC"));
                product_info.add(rs.getString("PRIX"));
                product_info.add(rs.getString("PRIX_COS"));
                product_info.add(rs.getString("ADRESSE"));
                product_info.add(rs.getString("QUANTITE"));
                Produit single_product = ProductFactory.makeProduct(product_info);
                products.add(single_product);
            }
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }return products;
    }

    public static void update_user(String id,String[] field,String[] value){
        PreparedStatement pstmt = null;
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE Personnes SET "+field[i]+" = ?");
            for (i = 1;i < field.length;i++) {
                stringBuilder.append(", "+field[i]+" = ?");
            }
            stringBuilder.append(" WHERE ID = ?");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            for (i = 0;i < field.length;i++) {
                pstmt.setString(i+1, value[i]);
            }
            pstmt.setString(i+1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void update_user(String id,String field,String value){
        PreparedStatement pstmt = null;
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE Personnes SET "+field+" = ?");
            stringBuilder.append(" WHERE ID = ?");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(i+1, value);
            pstmt.setString(i+1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void update_product(String id,String[] field,String[] value){
        PreparedStatement pstmt = null;
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE Produits SET "+field[i]+" = ?");
            for (i = 1;i < field.length;i++) {
                stringBuilder.append(", "+field[i]+" = ?");
            }
            stringBuilder.append(" WHERE ID = ?");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            for (i = 0;i < field.length;i++) {
                pstmt.setString(i+1, value[i]);
            }
            pstmt.setString(i+1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void update_product(String id,String field,String value){
        PreparedStatement pstmt = null;
        try{
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE Produits SET "+field+" = ?");
            stringBuilder.append(" WHERE ID = ?");
            String sql = stringBuilder.toString();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(i+1, value);
            pstmt.setString(i+1,id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    }
