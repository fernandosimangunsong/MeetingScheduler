package com.mycompany.app;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Controller.AdminController;
import com.mycompany.app.Model.User;

import com.mycompany.app.View.MenuAdmin;
import com.mycompany.app.View.MenuUtama;

import java.util.*;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class App 
{
    public static void main( String[] args ) 
    {
       
        try {
            String menu;
            
            User user = new User();
            MenuAdmin menuadmin = new MenuAdmin();
            AdminController admin = new AdminController(user, menuadmin);
            
            Scanner input = new Scanner(System.in);
            System.out.print(">");
            menu = input.next();
            switch (menu) {
                case "list-user":
                        admin.listUser();
                    break;
                    
                case "add-user":
                        admin.createUser();
                    break;
                    
                case "update-user":
                    String email ="fernando@gmail.com";
                    admin.editUser(email);
                    break;
                    
                default:
                    System.out.println("Operasi tidak ada. Sintaks error");
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
}
