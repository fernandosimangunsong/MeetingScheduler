/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.View;

import java.util.*;


/**
 *
 * @author ucong18
 */
public class MenuAdmin {
    public MenuAdmin(){
        
    }
    public void printUser(List dataUser){
        
    }
    
    public List formAddUser(){
        List adduser = new ArrayList<>();
        
        String email,password,name,isAdmin;
        Boolean status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Create User");
        System.out.print("User Name (Email) :");
        email =input.next();
        adduser.add(email);
        
        System.out.print("Password:");
        password =input.next();
        adduser.add(password);
        
        System.out.print("Name :");
        name = input.next();
        adduser.add(name);
        
        System.out.print("IsAdmin (Yes/No):");
        isAdmin = input.next();
        
        switch (isAdmin) {
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
        adduser.add(status);
        
        return adduser;
       
    }
    
    public List formEditUser(){
        List editUser = new ArrayList<>();
        
        String email,password,name,isAdmin;
        Boolean status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Create User");
        System.out.print("User Name (Email) :");
        email =input.next();
        editUser.add(email);
        
        System.out.print("Password:");
        password =input.next();
        editUser.add(password);
        
        System.out.print("Name :");
        name = input.next();
        editUser.add(name);
        
        System.out.print("IsAdmin (Yes/No):");
        isAdmin = input.next();
        
        switch (isAdmin) {
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
        editUser.add(status);
        
        return editUser;
       
    }
    
    
}
