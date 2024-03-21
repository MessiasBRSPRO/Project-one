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
            System.out.println("Username");
            user = input.nextLine();
            System.out.println("Password");
            pass = input.next().trim();
            connection = DriverManager.getConnection(urlDB, user, pass);
            if(connection != null){
                System.out.println("Connection with " + databaseName + "DB established");
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
