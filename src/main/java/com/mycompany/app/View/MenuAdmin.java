/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Controller.AdminController;
import com.mycompany.app.Model.User;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 *
 * @author ucong18
 */
public class MenuAdmin {
    public MenuAdmin(){
        
    }
    
    public void printUser() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(new File("user.json"));
       
        
        int i=1;
        
            System.out.println("===================  Data User =====================");
            System.out.println("====================================================");
            
        for(JsonNode root : rootArray){
           
            String email = root.path("email").asText();
            String name  = root.path("name").asText();
            
            System.out.println("User Ke-" +i);
            System.out.println("Email  : "+ email);
            System.out.println("name   : "+ name);
            System.out.println("================================================");
            i++;
        }  
    }
    
    public void printUserbyEmail(String email) throws IOException{
        //User user =new User();
        ObjectMapper mapper = new ObjectMapper();
        List<User> listuser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        
        int index =0;
        boolean info =false;
        for(User usr:listuser){
            if(email.equals(usr.getEmail())){
                  System.out.println("Email     = "+usr.getEmail());
                  System.out.println("Password  = "+usr.getPassword());
                  System.out.println("Nama      = "+usr.getName());
                  info =true;
            }
        }
        if(!info){
            System.out.println("Data tidak ada");
        }
        
    }
    public List formAddUser(){
        List adduser = new ArrayList<>();
        
        String email,password,name,isAdmin;
        Boolean status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Create User");
        System.out.print("User Name (Email) :");
        email =input.nextLine();
        adduser.add(email);
        
        System.out.print("Password:");
        password =input.nextLine();
        adduser.add(password);
        
        System.out.print("Name :");
        name = input.nextLine();
        adduser.add(name);
        
        System.out.print("IsAdmin (Yes/No):");
        isAdmin = input.nextLine();
        
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
    
    public User formEditUser(){
        User user = new User();
        
        String email,password,name,isAdmin;
        Boolean status;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Edit User");
        System.out.print("User Name (Email) :");
        email =input.nextLine();
        user.setEmail(email);
        
        System.out.print("Password:");
        password =input.nextLine();
        user.setPassword(password);
        
        System.out.print("Name :");
        name = input.nextLine();
        user.setName(name);
        
        System.out.print("IsAdmin (Yes/No):");
        isAdmin = input.nextLine();
        
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
        user.setIsAdmin(status);
        
        return user;
       
    }
    
    public String operasiAdmin() throws IOException{
        User user = new User();
        MenuAdmin menuadmin = new MenuAdmin();
            
        AdminController admin = new AdminController(user, menuadmin);
        
        Scanner input = new Scanner(System.in);
        String menu, email;
        
        
            System.out.println("============ Menu Admin =================== ");
            System.out.print  (">");
            menu = input.next();
            
            
            switch (menu) {

                    case "list-user":
                            printUser();
                        break;

                    case "add-user":
                            admin.createUser();
                        break;

                    case "edit-user":

                            email =input.next();
                            admin.editUser(email);
                            break;

                    case "del-user":
                            email =input.next();
                            admin.deleteUser(email);
                            break;
                    case "exit":
                         System.exit(0);
                         break;
                    default:
                        System.out.println("Sintaks error");
                        break;
             }
         
       return menu;
    }
    
    
}
