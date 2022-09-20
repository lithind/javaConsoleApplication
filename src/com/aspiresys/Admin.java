package com.aspiresys;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Admin extends Customer {
     private String name;
     private  String price;
     private String category;
     
     Scanner scanner=new Scanner(System.in);





     public void  getMenu()
     {
          System.out.println("Enter Item name :");
          name=scanner.next();
          System.out.println("Enter category: ");
          category=scanner.next();
          while(true)
                {
                    System.out.println("Enter Item Price :");
                    price=scanner.next();
                    if(Pattern.matches("[0-9]*",price))
                    {
                        break;

                    }
                    else{
                        System.err.println("Enter valid price");

                    }
                }
          
     }

     public void insertMenu() throws SQLException, ClassNotFoundException
     {
          try {
               DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
               Connection connection=databaseConnection.getConnection();

               String sql="insert into menu values(?,?,?);";
               PreparedStatement preparedStatement=connection.prepareStatement(sql);

               preparedStatement.setString(1,name);
               preparedStatement.setString(2,category);
               preparedStatement.setString(3,price);
               preparedStatement.execute();
               System.out.println("Menu item inserted Successfully ! ");

               databaseConnection.closeConnection(connection,preparedStatement);
          }
          catch (Exception e)
          {
               System.out.println(e.getMessage());
          }
     }

     public void viewOrder()
     {
          try {
               DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
               Connection connection=databaseConnection.getConnection();

               Statement statement= connection.createStatement();
               ResultSet resultSet = statement.executeQuery("select * from orders;");

               String format = "| %-10s | %-10s | %-10s | %-10s | %-10s |\n";
               System.out.format(format, "----------", "----------", "----------", "----------", "----------");
               System.out.format(format, "table No","Item","Price","Quantity","Total");
               System.out.format(format, "----------", "----------", "----------", "----------", "----------");

               while(resultSet.next())
               {
                    System.out.format(format,resultSet.getString(1),resultSet.getString(3),resultSet.getString(4),resultSet.getString(2),resultSet.getString(5));
               }
               System.out.format(format, "----------", "----------", "----------", "----------", "----------");
               System.out.println();
               databaseConnection.closeConnection(connection,statement);

          }
          catch (Exception e)
          {
               System.out.println(e.getMessage());
          }
          
          
     }

     public void deleteItem() throws ClassNotFoundException, SQLException 
     {
          DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
          Connection connection=databaseConnection.getConnection();
          Statement statement= connection.createStatement();
          String getName="";
          while(true)
          {
               System.out.println("Enter item name to delete : ");
               String item=scanner.next();
               String CheckQuery="select name from menu where name='"+item+"'";
               ResultSet resultSet = statement.executeQuery(CheckQuery);

               while(resultSet.next())
               {
                    getName = resultSet.getString("name");

               }
               if(item.equals(getName))
               {
                    String query="delete from menu where name=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(query);
                    preparedStatement.setString(1,item);
                    preparedStatement.execute();
                    System.out.println("Menu item deleted Successfully ! ");
                    showAdminHome();
                    databaseConnection.closeConnection(connection,preparedStatement);
               }
               else
               {
                    System.out.println("No such item in menu !!!");
               }
          }
          
     }

     public void rateUpdate() throws ClassNotFoundException, SQLException 
     {
          DatabaseConnection databaseConnection=new DatabaseConnection("jdbc:mysql://localhost:3306/digicafe","root","Aspire@1");
          Connection connection=databaseConnection.getConnection();
          Statement statement=connection.createStatement();
          String getName="";

          while(true)
          {
               System.out.println("Enter item name to Update rate: ");
               String name=scanner.next(); 
               String CheckQuery="select name from menu where name='"+name+"'";
               ResultSet resultSet = statement.executeQuery(CheckQuery);
     
               while(resultSet.next())
               {
                    getName = resultSet.getString("name");
     
               }
               if(name.equals(getName))
               {
                    System.out.println("Enter item's new price  : ");
                    int price=scanner.nextInt();
                    String query="update menu set price =? where name=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(query);
                    preparedStatement.setInt(1, price);
                    preparedStatement.setString(2,name);
                    preparedStatement.executeUpdate();
                    System.out.println("Menu item price updated Successfully ! ");
                    showAdminHome();
                    databaseConnection.closeConnection(connection,preparedStatement);
               }
               else
               {
                    System.out.println("No such item in menu !!!");
     
               }
          } 
     }


     public void showAdminHome() throws SQLException, ClassNotFoundException 
     {
          System.out.println();
          System.out.println("**************** Hey Admin !!!! ****************");
          System.out.println();
          System.out.println("\n1. ADD MENU ITEM\n2. VIEW ORDERS\n3. UPDATE ITEM RATE\n4. DELETE ITEM\n5. SHOW MENU\n6. EXIT");
          System.out.println("----------------------------");
          System.out.println("      Choose an option      ");
          System.out.println("----------------------------");

          Scanner scanner=new Scanner(System.in);
          int choice=scanner.nextInt();
          switch(choice)
          {
               case 1:
                    showMenu("Hello Admin");
                    getMenu();
                    insertMenu();
                    showAdminHome();
                    break;

               case 2:
                    viewOrder();
                    showAdminHome();
                    break;
               case 3:
                    showMenu("Hello Admin");
                    rateUpdate();
                    showAdminHome();
                    break;
               case 4:
                    showMenu("Hello Admin");
                    deleteItem();
                    showAdminHome();
                    break;
               case 5:
                    showMenu("Hello Admin");
                    showAdminHome();
                    break;
               case 6:
                    System.out.println("Logout Successfully...!");
                    System.out.println();
                    digiCafe.viewHome();
                    break;
               default:
                    System.out.println("Enter valid option !");
                    showAdminHome();
                    break;
          }
     }
}






