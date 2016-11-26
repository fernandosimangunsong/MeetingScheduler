/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Model;

/**
 *
 * @author ucong18
 */
public class User {
        private String email;
	private String password;
	private String name;
	private boolean isAdmin;
       
      
      public User(){
          
      }
      
      public User(String email){
          this.email=email;
      }
      
      public User(String email, String nama){
          this.email= email;
          this.name = nama;
      }
              
      
      public User(String Email, String password, String name, boolean isAdmin){
          this.email=Email;
          this.name=name;
          this.password=password;
          this.isAdmin = isAdmin;
      }
      public  void setEmail(String email){
            this.email = email;
       }
        
      public String getEmail(){
            return this.email;
       }
        
      public  void setPassword(String password){
            this.password = password;
        }
        
      public  String getPassword(){
            return this.password;
        }
        
      public  void setName(String name){
            this.name = name;
       }
        
      public  String getName(){
            return this.name;
      }
       
     public void setIsAdmin(boolean isAdmin){
         this.isAdmin = isAdmin;
     }
     
     public boolean getIsAdmin(){
         return this.isAdmin;
     }
}
