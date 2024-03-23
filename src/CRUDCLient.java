import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDCLient{

    private ConnectionWithDB connection;
    private Connection connectionStarts;
    private String sqlCommand;
    private ResultSet query;

    public CRUDCLient(){
        this.connection = new ConnectionWithDB();
        this.connectionStarts = connection.startsConnectionWithDb("postgresql", 5432, "clients");
    }

    public void insert(Client client){
        //Thats is a method than will insert a item(Client) in Database
        sqlCommand = "INSERT INTO clients (name, age, cpf) VALUES(?, ?, ?)";
        try {
            PreparedStatement insertor = connectionStarts.prepareStatement(sqlCommand);
            insertor.setString(1, client.getName());
            insertor.setInt(2, client.getAge());
            insertor.setString(3, client.getCpf());
            insertor.execute();
            System.out.println("Item Inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(Client client){
        sqlCommand = "DELETE FROM clients WHERE name=? AND age=? AND cpf=?";
        try{
            PreparedStatement deletor = connectionStarts.prepareStatement(sqlCommand);
            deletor.setString(1, client.getName());
            deletor.setInt(2, client.getAge());
            deletor.setString(3, client.getCpf());
            deletor.execute();
            System.out.println("Item removed");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void select(){
        sqlCommand = "SELECT * FROM clients";
        try {
            PreparedStatement querySelector = connectionStarts.prepareStatement(sqlCommand);
            query = querySelector.executeQuery();
            int totalRows = 0;
            while(query.next()){
                totalRows ++;
                System.out.println("Name:"+query.getString(1) + ", Age:"+query.getInt(2) + ", CPF:"+query.getString(3));
            }
            System.out.println("Total rows:"+totalRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void update(){}
}