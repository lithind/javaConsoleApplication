package com.aspiresys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;


public class Customer {
    DigiCafe digiCafe=new DigiCafe();
    Scanner scanner = new Scanner(System.in);

    public void showMenu() throws SQLException, ClassNotFoundException 
    {
        
        DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
        Connection connection=databaseConnection.getConnection();
        Statement statement= connection.createStatement();

        String format1 = "| %-10s |\n";
        System.out.format(format1, "----------");
        System.out.format(format1, "Category");
        System.out.format(format1, "----------");

        ResultSet resultSet = statement.executeQuery("select category from menu group by category;");
        while(resultSet.next())
        {
            System.out.format(format1,resultSet.getString(1));

        }
        System.out.format(format1, "----------");
        System.out.println();
        System.out.println("Select category which you need ....");
        String category=scanner.next();
        String format2 = "| %-10s | %-10s |\n";
        System.out.format(format2, "----------","----------");
        System.out.format(format2, "Item Name","Price");
        System.out.format(format2, "----------","----------");
        ResultSet resultSet2 = statement.executeQuery("select * from menu where category='"+category+"';");
        while(resultSet2.next())
        {
            System.out.format(format2,resultSet2.getString(1),resultSet2.getString(3));
        }
        System.out.format(format2, "----------","----------");
        databaseConnection.closeConnection(connection,statement);
        System.out.println();
    }


    public void showMenu(String admin) throws SQLException, ClassNotFoundException 
    {
        DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
        Connection connection=databaseConnection.getConnection();
        Statement statement= connection.createStatement();
   
   
        String format = "| %-10s | %-10s | %-10s |\n";
        System.out.format(format, "----------", "----------", "----------");
        System.out.format(format, "Item name","Category","Price");
        System.out.format(format, "----------", "----------", "----------");
   
        ResultSet resultSet = statement.executeQuery("select * from menu;");
        while(resultSet.next())
        {
            System.out.format(format,resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
   
        }
        System.out.format(format, "----------", "----------", "----------");
        System.out.println();
        databaseConnection.closeConnection(connection,statement);

    }


    public  void  orderItem() 
    {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe", "root", "Aspire@1");
            Connection connection = databaseConnection.getConnection();
            System.out.println("----------------------------");
            System.out.println("        Order an item        ");
            System.out.println("----------------------------");
            System.out.println("how many types items you are needed ? ");
            int itemCount=scanner.nextInt();

            String tableNumber ;
            String quantity;

            List<MenuItem> orderList=new ArrayList<MenuItem>();
            
            while(true)
            {
                System.out.println("Table number : ");
                tableNumber = scanner.next();
                if(Pattern.matches("[1-9]*",tableNumber))
                {
                    break;

                }
                else{
                    System.err.println("Enter valid table number");

                }
            }

            int totalSum=0;
            for(int i=0;i<itemCount;i++)
            {
                System.out.println("Item name : ");
                String name = scanner.next();
                

                while(true)
                {
                    System.out.println("Quantity : ");
                    quantity = scanner.next();
                    if(Pattern.matches("[0-9]*",quantity))
                    {
                        break;

                    }
                    else{
                        System.err.println("Enter valid quantity");

                    }
                }
                String query = "SELECT price FROM menu WHERE name= ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                int price = 0;
                while (resultSet.next()) 
                {
                    price = resultSet.getInt("price");

                }

                int total = Integer.parseInt(quantity )* price;
                String sql = "insert into orders values(?,?,?,?,?);";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, tableNumber);
                preparedStatement.setString(2, quantity);
                preparedStatement.setString(3, name);
                preparedStatement.setInt(4, price);
                preparedStatement.setInt(5, total);

                try {
                    preparedStatement.executeUpdate();
                    totalSum=totalSum+total;
                    orderList.add(new MenuItem(name,price,quantity,total));
                } 
                catch (SQLException e) 
                {
                    System.out.println("no such item in menu");
                    i=i-1;
                }
                if(i==itemCount-1)
                {
                    
                    break;

                }
                else
                {
                    showMenu();
                }
            }
            String format=   "| %-10s | %-10s | %-10s | %-10s |\n";
            System.out.format(format, "----------", "----------","----------","----------");
            System.out.format(format, "Item name","Price","Quantity","          ");
            System.out.format(format, "----------", "----------","----------","----------");
            for (MenuItem menuItem : orderList) {
                
                System.out.format(format,menuItem.getName(),menuItem.getPrice(),menuItem.getQuantity(),menuItem.getTotal());
            }
            System.out.format(format, "----------", "----------","----------","----------");
            System.out.format(format,"          ","          ","   Total  ",totalSum);
            System.out.format(format, "----------", "----------","----------","----------");
            System.out.println();
            System.out.println("Ordered Successfully ! ");
            
            while (true)
            {
                System.out.println("press 1 to back Home");
                int backHome=scanner.nextInt();
                if(backHome==1)
                {
                    break;
    
                }
                else{
                    System.out.println("invalid input ");
                }
            }
            digiCafe.viewHome();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}