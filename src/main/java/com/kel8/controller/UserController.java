/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kel8.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kel8.model.User;
import com.kel8.view.ViewUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ucong18
 */
public class UserController {
    private final User model;
    private final ViewUser view;
    
    ViewUser v = new ViewUser();
    
    
    public UserController(User model, ViewUser view){
        this.model = model;
        this.view = view;
    }

    public UserController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public  void setUserEmail(String email){
            model.setEmail(email);
       }
        
    public String getUserEmail(){
            return model.getEmail();
    }
    
    public  void setUserPassword(String password){
            this.model.setPassword(password);
        }
        
    public  String getUserPassword(){
            return model.getPassword();
    }
        
    public  void setUserName(String name){
            this.model.getName();
    }
        
    public  String getUserName(){
            return model.getName();
    }
       
    public void setUserIsAdmin(int isAdmin){
         this.model.setIsAdmin(isAdmin);
    }
     
    public int getUserIsAdmin(){
         return model.getIsAdmin();
    }
    
   public void createUser(){
       List tmp = new ArrayList<>();
       tmp = v.FormAddUser();
       
       model.setEmail((String) tmp.get(0));
       model.setPassword((String) tmp.get(1));
       model.setName((String) tmp.get(2));
       model.setIsAdmin((Integer) tmp.get(3));
       
   }
    
    public void printUser(){
        view.listUser(getUserEmail(),getUserName(),getUserIsAdmin());
    }
    
   public void simpan() throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        
       
        
        
        List<User>lstUser = new ArrayList<>();
        try{
        lstUser = mapper.readValue(new File("user.json"),List.class);
        
        }catch (JsonGenerationException e) {
	} catch (JsonMappingException e) {
	} catch (IOException e) {
	}
        
        lstUser.add(model);
        
        try{
           mapper.writeValue(new File("user.json"),lstUser);   
        } catch (JsonGenerationException e) {
	} catch (JsonMappingException e) {
	} catch (IOException e) {
	}
        
        
    }   
    
    
    
    
}

