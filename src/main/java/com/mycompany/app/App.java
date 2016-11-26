package com.mycompany.app;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.app.Controller.MenuController;
import java.text.ParseException;







public class App 
{
    public static void main( String[] args ) throws ParseException 
    {
       
        try {
                MenuController mainmenu = new MenuController();
                mainmenu.login();
            
       } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
    }
}
