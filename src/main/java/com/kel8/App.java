package com.kel8;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class App 
{
   
    public static void main( String[] args )
    {
         App obj = new App();
         obj.run();
         
    }
    
    private void run() {
        ObjectMapper mapper = new ObjectMapper();
        User user = createDummyObject();
        
       
        
        try{
            mapper.writeValue(new File("user.json"),user);
        } catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
        
	
    }
    
    private User createDummyObject(){
        User user = new User();
        
        user.setEmail("fernando@gmail.com");
        user.setPassword("12345");
        user.setName("fernando");
        user.setIsAdmin(1);
        
        return user;
    }
}
