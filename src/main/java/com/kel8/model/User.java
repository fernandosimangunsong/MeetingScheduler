/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kel8.model;

import com.kel8.view.ViewUser;
import java.util.Scanner;

/**
 *
 * @author ucong18
 */
public class User {
       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        private String email;
	private String password;
	private String name;
	private int isAdmin;
       
      
      public User(){
          
      }
      
      public User(String Email, String password, String name, int isAdmin){
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
       
     public void setIsAdmin(int isAdmin){
         this.isAdmin = isAdmin;
     }
     
     public int getIsAdmin(){
         return this.isAdmin;
     }
     
     /*
     public void setSkills(List<String> skills){
         this.skills = skills;
     }
        
     public List<String> getSkills(){
         return this.skills;
     }

     */

  
    
}
