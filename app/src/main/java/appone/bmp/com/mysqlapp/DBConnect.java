package appone.bmp.com.mysqlapp;

import java.sql.*;

public class DBConnect {
    public static Connection getConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://192.168.191.8:3306/db1";
            String user="root";
            String pwd="bounmy123456";
            Connection c =DriverManager.getConnection(url,user,pwd);
            return c;

        }catch (Exception e){
            return null;
        }
    }
}
