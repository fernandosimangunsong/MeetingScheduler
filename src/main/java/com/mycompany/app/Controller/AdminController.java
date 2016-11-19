/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import com.mycompany.app.Model.User;
import com.mycompany.app.View.MenuAdmin;


import java.util.*;
import java.io.*;


/**
 *
 * @author ucong18
 */
public class AdminController {
    /*
        1. list-user
        2. add-user
        3. edit-user
        4. del-user
    */
    
    private final User usermodel;
    private final MenuAdmin menuadmin;
   
    
    public AdminController(User usermodel, MenuAdmin menuadmin){
        this.usermodel =usermodel;
        this.menuadmin =menuadmin;
    }
    
    public List bacaFileUser() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List datauser = mapper.readValue(new File("user.json"),List.class);
        return datauser;
        
    }
    
    
    
    public void saveUser(List datauser) throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("user.json"),datauser);
    }
    
    
    public boolean cekEmailExist(String email) throws IOException{
        boolean emailExist =false;
        
       
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(new File("user.json"));
        for(JsonNode root : rootArray){
            
            if(email.equals(root.path("email").asText())){
                emailExist = true;
            }
            
        } 
        
        return emailExist;
    }
    
    
    public void listUser() throws IOException{
        
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
    
    
    public void createUser() throws IOException{
       List tmp = menuadmin.formAddUser();
       
       usermodel.setEmail((String) tmp.get(0));
       usermodel.setPassword((String) tmp.get(1));
       usermodel.setName((String) tmp.get(2));
       usermodel.setIsAdmin((Boolean) tmp.get(3));
       
       tmp = bacaFileUser();
       tmp.add(usermodel);
       saveUser(tmp);   
   }
    
    public void updateUser(String email, List newDataUser)throws IOException{

    }
    
    public void editUser(String email) throws IOException{
        List<User> user = new ArrayList<>();
        
        if(cekEmailExist(email)){
            /* edit user berdasarkan email */
            System.out.println("Before Update");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootArray = mapper.readTree(new File("user.json"));
                
                for(JsonNode root : rootArray){
                    
                    if(email.equals(root.path("email").asText())){
                        
                        ((ObjectNode) root).put("email", "fernando18@gmail.com");
                        ((ObjectNode) root).put("nickname", "mkyong");
                        System.out.println("Update berhasil");
                    }     
            
                }
        
                    
                 
        }else{
            System.out.println("User tidak ditemukan");
        }
    }
    
    public void deleteUser(String email) throws IOException{
        //instantiate your json array (e.g. from a string, or a file)
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("user.json"));
            
            
            
    }
    
    
   
}
