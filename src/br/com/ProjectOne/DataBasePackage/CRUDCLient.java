package br.com.ProjectOne.DataBasePackage;

import br.com.ProjectOne.AccountsType.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        //Thats is a method than will insert a item(br.com.ProjectOne.AccountsType.Client) in Database
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
                System.out.println("Name:"+query.getString(1) + " |Age:"+query.getInt(2) + " |CPF:"+query.getString(3));
            }
            System.out.println("Total rows:"+totalRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void update(Client person){
        Scanner input = new Scanner(System.in);
        System.out.println("You are in client's area. what atribute will be modify?");
        System.out.println("""
                [1]name
                [2]age
                """);
        int inputOption = input.nextInt();
        switch (inputOption){
            case 1:
                sqlCommand = "UPDATE clients SET name = ? WHERE cpf=?";
                System.out.println("you will modify the atribute Name. Whats is the new value?");
                String valueModify = input.next();
                try {
                    PreparedStatement modificator = connectionStarts.prepareStatement(sqlCommand);
                    modificator.setString(1, valueModify);
                    modificator.setString(2, person.getCpf());
                    modificator.executeUpdate();
                    System.out.println("Data(name) updated!");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                input.close();
                break;
            case 2:
                sqlCommand = "UPDATE clients SET age = ? WHERE cpf=?";
                System.out.println("you will modify the atribute age. Whats is the new value?");
                int newAge = input.nextInt();
                try{
                    PreparedStatement modificator = connectionStarts.prepareStatement(sqlCommand);
                    modificator.setInt(1, newAge);
                    modificator.setString(2, person.getCpf());
                    modificator.executeUpdate();
                    System.out.println("Data(Age) updated");
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                input.close();
                break;
            default:
                throw new RuntimeException("This attribute dont exists! try again");
        }
    }
}