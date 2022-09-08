package com.aspiresys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{
    String url;
    String username;
    String password;

    public DatabaseConnection(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException 
    {
        try 
        {
            Connection connection=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
            return connection;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void closeConnection(Connection connection, Statement statement) throws SQLException 
    {
        try 
        {
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
