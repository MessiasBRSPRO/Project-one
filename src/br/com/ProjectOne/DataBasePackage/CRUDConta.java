package br.com.ProjectOne.DataBasePackage;

import br.com.ProjectOne.AccountsType.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CRUDConta {
    private ConnectionWithDB connection;
    private Connection connectionStarter;
    private String sqlCommand;

    public CRUDConta(){
        this.connection = new ConnectionWithDB();
        this.connectionStarter = connection.startsConnectionWithDb("postgresql", 5432, "contasBancarias");
    }

    public void register(Conta conta){
        sqlCommand = "INSERT INTO contasBancarias (name, agency, actualbalance, cpf, typeaccount) VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement insertor = connectionStarter.prepareStatement(sqlCommand);
            insertor.setString(1, conta.getClient().getName());
            insertor.setInt(2, conta.getBankNumber());
            insertor.setDouble(3, conta.getActualBalance());
            insertor.setString(4, conta.getClient().getCpf());
            insertor.setString(5, conta.getAccountType());
            insertor.execute();
            update(conta);

            System.out.println("A account has been registered in Application's DataBase");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void closeAccount(Conta conta){
        sqlCommand = "DELETE FROM contasBancarias WHERE name=? AND agency=? AND actualbalance=? AND cpf=? AND typeaccount=?";
        String turningOffAccount = "INSERT INTO closeAccounts (name, agency, actualbalance, cpf, typeaccount) VALUES(?, ?, ?, ?, ?)";
        try{
            Connection connectionInsert = connection.startsConnectionWithDb("postgresql", 5432, "closeAccounts");
            PreparedStatement statementInsert = connectionInsert.prepareStatement(turningOffAccount);

            // Inserting in database of deactivated accounts
            statementInsert.setString(1, conta.getClient().getName());
            statementInsert.setInt(2, conta.getBankNumber());
            statementInsert.setDouble(3, conta.getActualBalance());
            statementInsert.setString(4, conta.getClient().getCpf());
            statementInsert.setString(5, conta.getAccountType());
            statementInsert.execute();

            //Removing the account from database Active Accounts/ContasBancarias
            PreparedStatement statementDelete = connectionStarter.prepareStatement(sqlCommand);
            statementDelete.setString(1, conta.getClient().getName());
            statementDelete.setInt(2, conta.getBankNumber());
            statementDelete.setDouble(3, conta.getActualBalance());
            statementDelete.setString(4, conta.getClient().getCpf());
            statementDelete.setString(5, conta.getAccountType());
            statementDelete.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void select(){
        sqlCommand = "SELECT * FROM contasBancarias";

        try{
            PreparedStatement selectorAll = connectionStarter.prepareStatement(sqlCommand);
            ResultSet resultSet = selectorAll.executeQuery();
            int totalRows = 0;
            while(resultSet.next()){
                totalRows++;
                System.out.println("Name:" + resultSet.getString(1) + " |Agency:"+resultSet.getInt(2) + " |$ actual:" + resultSet.getDouble(3) +
                        " |CPF:"+resultSet.getString(4) + " |Type Account:"+resultSet.getString(5));
            }
            System.out.println("Total Rows:"+totalRows);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Conta conta){
        sqlCommand = "UPDATE contasBancarias SET actualbalance= ? WHERE cpf=?";

        try{
            PreparedStatement update = connectionStarter.prepareStatement(sqlCommand);
            update.setDouble(1, conta.getActualBalance());
            update.setString(2, conta.getClient().getCpf());
            update.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
