package by.issoft.store.helpers.dbconnector;


import com.ibatis.common.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;


public class DBConnector implements Runnable {
    private static final String url = "jdbc:h2:file:/OnlineShop_parent/store/src/main/resources/data/shop;AUTO_SERVER=TRUE";
    private static final String user = "";
    private static final String password = "";

    private DBConnector() {}

    private static volatile DBConnector instance;
    public static DBConnector getInstance() {
        DBConnector localInstance = instance;
        if (localInstance == null) {
            synchronized (DBConnector.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBConnector();
                }
            }
        }
        return localInstance;
    }

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    @Override
    public void run() {
        try {
            Driver driver = new org.h2.Driver();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Connection is established.");
            }
            ScriptRunner sr = new ScriptRunner(con,true,true);
            Reader reader = new BufferedReader(new FileReader("OnlineShop_parent/store/src/main/resources/schema.sql"));
            sr.runScript(reader);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}




