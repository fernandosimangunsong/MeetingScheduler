/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kel8;



 import java.math.BigDecimal;
 import java.util.List;
/**
 *
 * @author ucong18
 */
class User {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        private String email;
	private String password;
	private String name;
	private Integer isAdmin;
      
      User(){
          
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
        

}
