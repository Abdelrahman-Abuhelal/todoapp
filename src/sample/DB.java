package sample;

import java.sql.*;

public class DB {

    public static Connection Connector() {
    try {
    Class.forName("org.sqlite.JDBC");
    Connection connect=DriverManager.getConnection("jdbc:sqlite:dataBase.db");
    return connect;
    }
    catch (Exception e){
        System.out.println(e);
    return  null;
    }
    }
}
