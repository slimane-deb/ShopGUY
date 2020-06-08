package sd.shopGUY.BDD;


import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sd.shopGUY.Metier.Produit;
import sd.shopGUY.Metier.Promotion;
import sd.shopguy.Metier.ClassProduit;

/**
 * Created by Slimane on 02/05/2016.
 */


public class ServiceDB {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/shopGUY";
    public static final String username = "root";
    public static final String password = "";

    public Connection connecter()   {

        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public List<Produit> getProduits(String density,String classe , String client)  {
        List<Produit> listProduits = new ArrayList<Produit>();
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "select * from produit natural join image  where density=? and classproduit=? and typeclient=?";
        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1,density);
            pst.setString(2,classe);
            pst.setString(3,client);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    Produit produit = new Produit();
                    produit.setNom(rs.getString("nom"));
                    produit.setMarque(rs.getString("marque"));
                    produit.setAnneeFab(rs.getInt("annee"));
                    produit.setPrix(rs.getInt("prix"));
                    produit.setClassProduit(ClassProduit.valueOf(rs.getObject("classproduit").toString()));
                    produit.setTypeClient(rs.getString("typeclient"));
                    produit.setDescription(rs.getString("description"));
                    produit.setQuantite(rs.getInt("quantite"));
                    produit.setFilename(rs.getString("filename"));
                    produit.setImage(Base64.encodeBase64String(rs.getBytes("icon")));

                    rs.next();
                    listProduits.add(produit);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst!=null) pst.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return listProduits;

    }
    public void updateProdQuant (String quantite ,String prodFile){
        List<Produit> listProduits = new ArrayList<Produit>();
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "update produit set quantite =?  where filename=?";
        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1,quantite);
            pst.setString(2,prodFile);
             //pst.executeQuery();
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst!=null) pst.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public List<Promotion> getPromos(){
        List<Promotion> listPromos = new ArrayList<Promotion>();
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "select * from promotion";
        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    Promotion promo = new Promotion();
                    promo.setDate(rs.getDate("date"));
                    promo.setDescription(rs.getString("description"));
                    listPromos.add(promo);
                    rs.next();

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst!=null) pst.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return listPromos;

    }

    public int addCommand(String num,String date,String etat,String montant,String username) {
        Connection conn = connecter();
        PreparedStatement  pst = null;
        String query ="insert into command(id,date,etat,montant,idUser) values(?,?,?,?,?)";
        int i=-1;
        try {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(query);
                pst.setInt(1,Integer.parseInt(num));
                pst.setString(2,date);
                pst.setString(3,etat);
                pst.setDouble(4,Double.parseDouble(montant));
                pst.setString(5,username);
            i  =  pst.executeUpdate();;
            conn.commit();
            } catch (SQLException e1) {
            e1.printStackTrace();
        }


        try {
            if(pst!=null)
                pst.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void updateCommand(String etat, String idUser) {
        Connection conn = connecter();
        PreparedStatement pst = null;
        String query = "update command set etat=? where idUser=?";
        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1, etat);
            pst.setString(2, idUser);
            //pst.executeQuery();
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
