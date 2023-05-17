/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Input {
    private static Scanner sc = new Scanner(System.in);
    public static String getString(String inputMsg, String errorMsg) {
        String s;
        while (true) {
            System.out.println(inputMsg);
            s = sc.nextLine();
            if (s.length() == 0 || s.isEmpty())
                System.out.println(errorMsg);
            else 
                return s;
        }
    }
    
    public static int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();
                else
                    return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getStringWithRegex(String inputMsg, String errorMsg, String format) {
        String s;
        while (true) {
            System.out.println(inputMsg);
            s = sc.nextLine().trim();
            String match = format;
            if (s.length() == 0 || s.isEmpty() || s.trim().matches(match) == false) 
                System.out.println(errorMsg);                  
            else
                return s;
        }
    }
}
