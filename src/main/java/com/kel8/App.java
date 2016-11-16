package com.kel8;



import com.kel8.controller.UserController;
import com.kel8.model.User;
import com.kel8.view.Menu;
import com.kel8.view.ViewUser;
import java.io.IOException;
import java.util.Scanner;


public class App 
{
   
    public static void main(String[] args) throws IOException {
        
       ViewUser view = new ViewUser();
       
       User model = new User();
       UserController userController = new UserController(model, view);
        
       
        int pilihan;
        
        System.out.println("MENU");
       
        System.out.println("1. Create User");
        System.out.println("2. List User");
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan Menu (1/2) = ");
        pilihan= input.nextInt();
        switch(pilihan){
            case 1: 
                    userController.createUser();
                    userController.simpan();
                 break;
            case 2:
                    userController.printUser();
                  break;
        default:System.out.println("pilihan tidak ada");
        
        }
       
      
    }
    
    
    
    
    
   

    

   
    
}
