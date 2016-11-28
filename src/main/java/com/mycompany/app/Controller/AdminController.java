/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;


import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.mycompany.app.Model.User;
import com.mycompany.app.Services.CheckData;
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
        //List datauser = mapper.readValue(new File("user.json"),List.class);
        List<User> datauser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        return datauser;
        
    }
    
    
    
    public void saveUser(List datauser) throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("user.json"),datauser);
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
    
    
    
    public void editUser(String email) throws IOException{
        User user =new User();
        CheckData CD = new CheckData();
        ObjectMapper mapper = new ObjectMapper();
        List<User> datauser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        
        if(CD.cekEmailExist(email)){
                
                /* edit user berdasarkan email */
                /*Print data user sebelum edit */
                System.out.println("Data sebelum edit");
                
                menuadmin.printUserbyEmail(email);
                
                 Integer id = CD.getIdExist(email);
                
                 user = menuadmin.formEditUser();
                 datauser.set(id, user);
          
                 saveUser(datauser);
                
                
        }
    }
    
    public void deleteUser(String email) throws IOException{
        
        User user =new User();
        CheckData CD = new CheckData();
        ObjectMapper mapper = new ObjectMapper();
        List<User> datauser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        
        if(CD.cekEmailExist(email)){
            int id = CD.getIdExist(email);
            
            
            datauser.remove(id);
            saveUser(datauser);
            
        }
              
    }
    
    
   
}
