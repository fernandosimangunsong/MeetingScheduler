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
        
        String email,password;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please Login");
        System.out.print("User Name (Email) : ");
        email =input.nextLine();
        
        System.out.print("Password          : ");
        password =input.nextLine();
        
        datalogin.add(email);
        datalogin.add(password);
        
        return datalogin;
    }
    
    /*
    public String role(){
        
        Scanner input = new Scanner(System.in);
        System.out.print("Select Your Role As (Admin (A)/ Inititaor( (I) / Participant (P)) : ");
        
        String loginIs = input.nextLine();
        return loginIs;       
    }
    
    */
}
