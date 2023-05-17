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
public class R002 implements Menu{
    private ArrayList<String> arrR002 = new ArrayList();
    
    public void addNewOption() {
        arrR002.add("Logout");
        arrR002.add("Exit");
    }
    
    public void printMenu() {
        for (int i = 0; i < arrR002.size(); i++) {
            System.out.println((i + 1) + "- " + arrR002.get(i));
        }
    }
    
    public int getChoice() {
        int choice = Input.getInteger("Choose [1.." + arrR002.size() + "]: ", "Please enter again!", 1, arrR002.size());
        return choice;
    }
}
