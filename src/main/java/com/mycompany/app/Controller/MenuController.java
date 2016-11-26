/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Meeting;
import com.mycompany.app.Model.User;
import com.mycompany.app.View.MenuAdmin;
import com.mycompany.app.View.MenuUser;
import com.mycompany.app.View.MenuUtama;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;



/**
 *
 * @author ucong18
 */
public class MenuController {
    
    
    public MenuController(){
        
    }
    public void login() throws IOException, ParseException{
        User user =new User();
        
        
        MenuUtama mu = new MenuUtama();
        MenuAdmin ma = new MenuAdmin();
       
        
        
        
        
        ObjectMapper mapper = new ObjectMapper();
        List<User> datauser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        List datalogin = mu.formLogin();
        
        
        String  menu  =null;
        String  email =null;
        
        if(checklogin(datalogin, datauser)){
            System.out.println("Login Succses");
             email =(String) datalogin.get(0);
             
           
                   boolean status = true;
                    
                    if(checkIsAdmin(datalogin,datauser,status)){
                        
                           System.out.println("Welcome Admin " +email);  
                          
                               while(!"exit".equals(menu)){
                                       
                                       menu=ma.operasiAdmin(); 
                                }
                    }else{
                            System.out.println("Welcome " +email);                                   
                                while(!"exit".equals(menu)){
                                        MenuUser  mUser = new MenuUser(email);
                                        menu = mUser.operasiUser();
                                 }
                    }
                     
            
        }else{
            System.out.println("Login Failed");
        }
        
       
    }
    
    
    public boolean checklogin(List datalogin, List datauser) throws IOException{
       boolean statuslog=false;
       List<User> listuser = datauser;
  
        for(User usr:listuser){
            if( (datalogin.get(0).equals(usr.getEmail())) && (datalogin.get(1).equals(usr.getPassword())) ){
                statuslog=true;
            }
        }
       
        return statuslog;
        
    }
    
    public boolean checkIsAdmin(List datalogin, List datauser, Boolean status) throws IOException{
       boolean statuslog=false;
       
       List<User> listuser = datauser;
       
        for(User usr:listuser){
            if( (datalogin.get(0).equals(usr.getEmail())) && (status.equals(usr.getIsAdmin())) ){
                statuslog=true;
            }
        }
       
        return statuslog;
        
    }
    
    
    
    
}
