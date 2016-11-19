/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ucong18
 */
public class MenuUtama {
    
    public MenuUtama(){
        
    }
    public List formLogin(){
        List datalogin = new ArrayList<>();
        
        String email,password,IsAdmin = null;
        Boolean status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please Login");
        System.out.print("User Name (Email) : ");
        email =input.next();
        datalogin.add(email);
        
        System.out.print("Password: ");
        password =input.next();
        datalogin.add(password);
        
        
        System.out.print("IsAdmin (Yes/No):");
        IsAdmin = input.next();
        
        switch (IsAdmin) {
            case "Yes":
                status = true;
                break;
            case "No":
                status = false;
                break;
            default:
                status = null;
                break;
        }
        
        datalogin.add(status);
        
        return datalogin;
    }
}
