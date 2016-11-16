/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kel8.view;

import com.kel8.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ucong18
 */
public class ViewUser {
    
    public ViewUser(){
        
    }
    
    
    public List FormAddUser(){
        List lt = new ArrayList<>();
        
        String email,password,name;
        int status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Create User");
        System.out.print("User Name (Email) :");
        email =input.next();
        lt.add(email);
        
        System.out.print("Password:");
        password =input.next();
        lt.add(password);
        
        System.out.print("Name :");
        name = input.next();
        lt.add(name);
        
        System.out.print("Status(admin(1), initiator(2), participant(3)) :");
        status = input.nextInt();   
        lt.add(status);
        
        return lt;
       
    }
    
   

    
    public void listUser(String email, String name, int status){
        String status1;
        if(status == 1){
            status1 = "Admin";
        }else if(status == 2){
            status1 = "Initiator";
        }else{
            status1 ="Participant" ;
        }
        System.out.println("List User");
        System.out.println("Email : " +email );
        System.out.println("Nama : " +name );
        System.out.println("Status : " +status1 );
    }
   
}
