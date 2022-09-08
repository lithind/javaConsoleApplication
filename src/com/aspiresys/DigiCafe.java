/*
Title: Digi Cafe
Author: Lithin D
Created at: 
Updated at: 05/09/2022
Reviewed by:  
Reviewed at: 
*/
package com.aspiresys;
import java.io.Console;
import java.sql.*;
import java.util.Scanner;

public class DigiCafe {

    public static String consoleFunc()
    {
        Console console = System.console();
        char[] passwordArray = console.readPassword(" Enter Password: ");
        for (int i = 0; i < passwordArray.length; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        return new String(passwordArray);
    }


    public static void viewHome() throws SQLException, ClassNotFoundException
    {
        Admin admin=new Admin();
        Customer customer=new Customer();
        System.out.println("**************** Welcome To our Cafe ****************");
        System.out.println("=====================================================");
        System.out.println("**************** CHOOSE AN OPTION ****************");
        System.out.println("\n1. Admin\n2. Customer");
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        int choice= scanner.nextInt();
        switch (choice)
        {
            case 1:
                while(true)
                {
                    System.out.println("________________________________________________________________");
                    System.out.println("                       Welcome Admin                            ");
                    System.out.println("________________________________________________________________");

                    System.out.println("                  Enter Admin credentials                    ");
                    System.out.println(" Username : ");
                    String username=scanner.next();
                    String password=consoleFunc();
    
                    if (username.equals("admin") && password.equals("admin"))
                    {
                        admin.showAdminHome();
                        break;
    
                    }
                    else
                    {
                        System.err.println("Invalid password or username");
    
    
                    }
    
                }
                break;
    
            case 2:
                customer.showMenu();
                customer.orderItem();
                break;
            default:
                System.err.println("Enter a valid option");
                viewHome();
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException
     {

    viewHome();

    }
}

