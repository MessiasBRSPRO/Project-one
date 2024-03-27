package br.com.ProjectOne.DataBasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionWithDB {
    //This class will be create to integration of DataBase( will be use PostgreSQL)
    // the database is existent only in my localMachine

    private Scanner input = new Scanner(System.in);
    private Connection connection;
    private String urlDB;
    private String user;
    private String pass;
    //jdbc:postgresql://localhost:5432/pessoas"
    public Connection startsConnectionWithDb(String databaseName, int port, String tableName){
        try {
            urlDB = "jdbc:" + databaseName + "://localhost:"+port+ "/" + tableName;

            // this is a code part than create a login access to database
            //System.out.println("Username");
            //user = input.next();
            //System.out.println("Password");
            //pass = input.next().trim();

            user = "postgres";
            pass = "Afton3444";
            connection = DriverManager.getConnection(urlDB, user, pass);
            if(connection != null){
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
