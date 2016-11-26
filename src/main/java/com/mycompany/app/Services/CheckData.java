/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Invitation;
import com.mycompany.app.Model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ucong18
 */
public class CheckData {
    public CheckData(){}
    
    public List splitData(String data){
        List Ldata = new ArrayList<>();
        
        if(!data.isEmpty()){
            // split String by ", " , kemudian memasukkan 1 per 1 ke arrayList
            Ldata.addAll(Arrays.asList(data.split(", ")));
        }
        return Ldata;
    }
    
   public List splitData1(String date){
        List listDate = new ArrayList(); 
        
        for (String p: date.split(", ")) {
            for (String c: p.split(" ")) {
                listDate.add(c);
            }
        }
        return listDate;
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
    
    public Integer getIdExist(String email) throws IOException{
        
        int getindex = -999;
        ObjectMapper mapper = new ObjectMapper();
        List<User> listuser = mapper.readValue(new File("user.json"),new TypeReference<List<User>>(){});
        
        int index =0;
        
        for(User usr:listuser){
            if(email.equals(usr.getEmail())){
                 getindex=index ;
            }
           index ++;
        }
        
       return getindex;
       
    }
    
    public List getIdInvitation(String email) throws IOException{
       
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> listuser = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        List index = new ArrayList();
        
        
        listuser.stream().filter((data) -> (email.equals(data.getEmail()))).forEachOrdered((data) -> {
            index.add(data.getId());
        });
        
       return index;
       
    }
    
    public boolean checkIdInvitation(int id, String email) throws IOException{
        boolean info =false;
        
       
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(new File("Invitation.json"));
        for(JsonNode root : rootArray){
            
            if(id == (root.path("id").asInt()) && email.equals(root.path("email").asText())){
                info = true;
            }
            
        } 
        
        return info;
    }
    
    public Integer getIndexSetEditInvitation(int id, String email) throws IOException{
        int getindex = -999;
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> data = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        int index =0;
        
        for(Invitation usr:data){
            if(email.equals(usr.getEmail()) && id==(usr.getId())){
                 getindex=index ;
            }
           index ++;
        }
        
       return getindex;
    }

    
    
    
}
