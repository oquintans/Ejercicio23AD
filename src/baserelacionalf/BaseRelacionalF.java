/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalf;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalF {

    Connection conn;
    CallableStatement cS;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseRelacionalF brF = new BaseRelacionalF();
        brF.connection();
        brF.procedure(3, 7);
    }

    public void connection() {
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion Establecida.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void procedure(int a, int b) {
        try {
            cS = conn.prepareCall("call pjavaprocoracle(?,?)");
            cS.setInt(1, a);
            cS.setInt(2, b);
            cS.registerOutParameter(2, Types.INTEGER);
            cS.execute();
            System.out.println("a= " + a);
            System.out.println("b= " + b);
            System.out.println("Resultado: " + cS.getInt(2));
            cS.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
