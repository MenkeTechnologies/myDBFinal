package jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacobmenke
 */
import java.sql.*;

public class MyConnection {

    static Connection myConnection = null;
    static String url = null;

    public static Connection getConnection(String... args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + new IP().getIP() +":" + new IP().getPort() + "/" + args[0] + "?user=" + args[1] + "&password=" + args[2];
            
            System.out.println(url);
            myConnection = DriverManager.getConnection(url);

        } catch (Exception e) {
            System.err.println(e);
        }

        return myConnection;

    }

}
