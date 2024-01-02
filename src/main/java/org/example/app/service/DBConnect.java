package org.example.app.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static DBConnect dbConnect;
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/nimportequoi";
    private static final String user = "root";
    private static final String pwd = "";

    private DBConnect(){
        try {
            this.connection = DriverManager.getConnection(
                    url, user, pwd);
        } catch (Exception ignored){}
    }

    public static Connection getInstance() {
        if (dbConnect == null) {
            dbConnect = new DBConnect();
        }
        return dbConnect.connection;
    }
}
