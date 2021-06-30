/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conecciondb1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class Conecciondb1 {

    Connection con;

    public Conecciondb1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            JOptionPane.showMessageDialog(null, "conexion existosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error:" + e);
        }
    }

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement stmt = null;

        String sDriver = "com.mysql.cj.jdbc.Driver";
        String sURL = "jdbc:mysql://localhost:3306/mydb";

        try {
            Class.forName(sDriver).newInstance();
            con = DriverManager.getConnection(sURL, "root", "root");
            JOptionPane.showMessageDialog(null, "conexion existosa");
            int idusuario = 122556546;
            String Nombre = "Yo, Claudiofgf";
            String correo = "prueba1.@gmail.combbbb";
            String password = "funciona sii";

            stmt = con.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?)");
            stmt.setInt(1, idusuario);
            stmt.setString(2, Nombre);
            stmt.setString(3, correo);
            stmt.setString(4, password);

            int retorno = stmt.executeUpdate();
            if (retorno > 0) {
                System.out.println("Insertado correctamente");
            }

        } catch (SQLException sqle) {
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode:"+ sqle.getErrorCode());
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Conecciondb1 cn = new Conecciondb1();
        Statement st;
        ResultSet rs;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select * from usuario");
            while (rs.next()) {
                System.out.println(rs.getInt("idusuario") + " " + rs.getString("Nombre") + " " + rs.getString("correo") + " " + rs.getString("password"));
            }
            cn.con.close();
        } catch (Exception e) {
        }
    }

}
