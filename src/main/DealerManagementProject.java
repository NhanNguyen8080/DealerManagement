/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.User;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import ui.Admin;
import ui.R001;
import ui.R002;
import util.Input;

/**
 *
 * @author dell
 */
public class DealerManagementProject {
    
    public static void main(String[] args) {
        service();
}
   
    
    public static void service() {
        String role = login();
        R001 r001 = new R001();
        R002 r002 = new R002();
        Admin admin = new Admin();
        
        int getUserChoice;
        if (role.equalsIgnoreCase("R001")) {
                r001.addNewOption();
            do {     
                r001.printMenu();
                getUserChoice = r001.getChoice();
                switch (getUserChoice) {
                    case 1:
                        r001.addNewDealer();
                        break;
                    case 2:
                        r001.updateADealer();
                        break;
                    case 3:
                        r001.searchADealerByName();
                        break;
                    case 4:
                        r001.removeADealer();
                        break;
                    case 5:
                        r001.printAllDealers();
                        break;
                    case 6:
                        r001.writeToFile();
                        break;
                    case 7:
                        service();
                        break;
                    default: 
                        System.out.println("Goodbye!");
                }
            } while (getUserChoice > 0 && getUserChoice < 7);
        }
            
        else if (role.equalsIgnoreCase("R002")) {
            r002.addNewOption();
            r002.printMenu();
            if (r002.getChoice() == 1) 
                service();
            else
                System.out.println("Goodbye!");
        }
            
        else {
            admin.addNewOption();
            admin.printMenu();
            if (admin.getChoice() == 1) 
                service();
            else 
                System.out.println("Goodbye!");
        }
            
    }
    
    public static ArrayList<User> readFromFileUsers() {
        ArrayList<User> user = new ArrayList<>();
        try {
            FileReader frUser = new FileReader("users.csv");
            BufferedReader brUser = new BufferedReader(frUser);
            String line = "";
            while ((line = brUser.readLine()) != null) {
                String data[] = line.split(" \\| ");
                String userName = data[0];
                String password = data[1];
                String role = data[2];
                String status = data[3];
                user.add(new User(userName, password, role, status));
            }
        } catch (Exception e) {
        }
        return user;
    }
    
    public static String login() {
        String userName, password;
        String role = "";
        Boolean cont = false;
        ArrayList<User> user = readFromFileUsers();
        int count = 0;
        System.out.println("Please login to Dealer Management System!");
        do {            
            userName = Input.getString("Your username:", "Username is not allowed blank!");
            password = Input.getString("Your password:", "Password is not allowed blank!");
    //        Check username and password
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getUserName().equalsIgnoreCase(userName)) {
                    count++;
                    if (user.get(i).getPassWord().equalsIgnoreCase(password)) {
                        System.out.println("Your role: " + user.get(i).getRole());
                        role = user.get(i).getRole();
                        cont = false;
                    }
                    else {
                        System.out.println("Password is wrong!");
                        cont = true;
                    }
                }
            }
            if (count == 0) {
                System.out.println("Username does not exist!");
                cont = true;
            }
            count = 0;
        } while (cont);
        return role;
    }
    
}
