/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import util.Input;

/**
 *
 * @author dell
 */
public class Admin implements Menu{
    private ArrayList<String> arrAdmin = new ArrayList();
    
    public void addNewOption() {
        arrAdmin.add("Logout");
        arrAdmin.add("Exit");

    }
    
    public void printMenu() {
        for (int i = 0; i < arrAdmin.size(); i++) {
            System.out.println((i + 1) + "- " + arrAdmin.get(i));
        }
    }
    
    public int getChoice() {
        int choice = Input.getInteger("Choose [1.." + arrAdmin.size() + "]: ", "Please enter again!", 1, arrAdmin.size());
        return choice;
    }
}
