/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.Dealer;
import java.io.*;
import java.util.*;
import util.Input;

/**
 *
 * @author dell
 */
public class R001 implements Menu{
    ArrayList<String> arrR001 = new ArrayList();
    ArrayList<Dealer> dealerList = readFromFileDealers();
    public static void main(String[] args) {
    }
    
    public void addNewOption() {
        arrR001.add("Add new dealer");
        arrR001.add("Update a dealer");
        arrR001.add("Search a dealer");
        arrR001.add("Remove a dealer");
        arrR001.add("Print all dealers");
        arrR001.add("Save");
        arrR001.add("Logout");
        arrR001.add("Exit");
    }
    
    public void printMenu() {
        for (int i = 0; i < arrR001.size(); i++) {
            System.out.println((i + 1) + "- " + arrR001.get(i));
        }
    }
    
    public int getChoice() {
        int choice = Input.getInteger("Choose [1.." + arrR001.size() + "]: ", "Please enter again!", 1, arrR001.size());
        return choice;
    }
   
    
   public boolean isEmpty() {
       if (dealerList.isEmpty())
           return true;
       return false;
   }
   public static ArrayList<Dealer> readFromFileDealers() {
       ArrayList<Dealer> dealerList = new ArrayList<>();
        try {
            FileReader frDealer = new FileReader("dealers.csv");
            BufferedReader brDealer = new BufferedReader(frDealer);
            String line = "";
            while ((line = brDealer.readLine()) != null) {
                String data[] = line.split("\\|");
                String id = data[0];
                String name = data[1];
                String address = data[2];
                String phone = data[3];
                String status = data[4];
                dealerList.add(new Dealer(id, name, address, phone, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealerList;
    }
   
   public void addNewDealer() {
    String id, name, address, phone, status;
    boolean cont = false;
    do {           
     id = Input.getStringWithRegex("Enter id (DXXX), X is a digit: ", "Id is invalid!", "^D[0-9]{3}$");
     int pos = searchADealerById(id);
     if (pos >= 0) {
        System.out.println("ID have already exist!");         
        cont = true;
     }
     else 
         cont = false;
    } while (cont);
    name = Input.getString("Enter name: ", "Name cannot be blank!");
    address = Input.getString("Enter address: ", "Address cannot be blank!");
    phone = Input.getStringWithRegex("Enter phone number: ", "Phone number is invalid", "^[+][1][2|9][0-9]{9}$");
    status = "enabled";
    dealerList.add(new Dealer(id, name, address, phone, status));
   }
   
   
   public void updateADealer() {
      String uId, uName, uAddress, uPhone, uStatus;
      if (isEmpty()) 
          System.out.println("There is no dealer actually. Please add new dealers");
      else {
        uId = Input.getStringWithRegex("Enter dealer's id you want to update: ", "Id is invalid!", "^D[0-9]{3}$");
        int pos = searchById(uId);
        if (pos >= 0) {
            uName = Input.getString("Enter name: ", "Name cannot be blank!");
            uAddress = Input.getString("Enter address: ", "Address cannot be blank!");
            uPhone = Input.getStringWithRegex("Enter phone number: ", "Phone number is invalid", "^[+][1][2|9][0-9]{9}$");
            uStatus = Input.getStringWithRegex("Enter status (enabled or disabled): ", "Status is invalid!", "^enabled|disabled|^$");
            dealerList.get(pos).setName(uName);
            dealerList.get(pos).setAddress(uAddress);
            dealerList.get(pos).setPhone(uPhone);
            dealerList.get(pos).setStatus(uStatus);
        }
        else
            System.out.println("Dealer does not exist!");
      }
   }
   
   public int searchById(String sName) {
       int pos = -1;
       if (isEmpty()) 
          System.out.println("There is no dealer actually. Please add new dealers");
      else {
           for (int i = 0; i < dealerList.size(); i++) 
               if (dealerList.get(i).getId().equalsIgnoreCase(sName))
                   pos = i;
       }
       return pos;
   }
   
   public void printAllDealers() {
       if (isEmpty()) 
          System.out.println("There is no dealer actually. Please add new dealers");
       else {
        for (Dealer dealer: dealerList) {
            System.out.println(dealer.toString());
        }
       }
   }
   
   public void removeADealer() {
       String rId;
       if (isEmpty()) 
          System.out.println("There is no dealer actually. Please add new dealers");
       else {
        rId = Input.getStringWithRegex("Enter dealer's id you want to remove: ", "Id is invalid!", "^D[0-9]{3}$");
           for (int i = 0; i < dealerList.size(); i++) 
               if (dealerList.get(i).getId().equalsIgnoreCase(rId))
                dealerList.remove(dealerList.get(i));
       }
   }
   
   public void searchADealerByName() {
       String sName;
       int count = 0;
       if (isEmpty()) 
          System.out.println("There is no dealer actually. Please add new dealers");
      else {
           sName = Input.getString("Enter dealer's name you want to search: ", "Name cannot be blank!");
           for (Dealer dealer: dealerList) {
               if (dealer.getName().contains(sName)) {
                   if (dealer.getName().equalsIgnoreCase("Name"))
                       count = count;
                   else {
                   System.out.println(dealer);
                   count++;
                   }
               }
           }
           if (count == 0)
                System.out.println("Sorry, there is no dealer you are looking for!");
       }
   }
   
   public int searchADealerById(String sID) {
       int pos = -1;
       int i = 0;
       for (Dealer dealer: dealerList) {
           i++;
           if (dealer.getId().equalsIgnoreCase(sID))
               pos = i;
       }
       return pos;
   }
   
   public void writeToFile() {
       try {
           FileWriter fw = new FileWriter("dealers.csv");
           BufferedWriter bw = new BufferedWriter(fw);
           for (Dealer dealer : dealerList) {
               bw.write(dealer.toString());
               bw.newLine();
           }
           bw.close();
           fw.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println("Save to file, SUCCESS!!!");
   }
   
   
}
