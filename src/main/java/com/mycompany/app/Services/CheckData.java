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
import com.mycompany.app.Model.Meeting;
import com.mycompany.app.Model.User;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ucong18
 */
public class CheckData {
    ObjectMapper mapper = new ObjectMapper();
    public CheckData(){}
    
    public List splitData(String data){
        List Ldata = new ArrayList<>();
        
        if(!data.isEmpty()){
            // split String by ", " , kemudian memasukkan 1 per 1 ke arrayList
            /* fernando@gmail.com, novan@gmail.com, rizki@gmail.com */
            
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
   
    public List splitData2(String date) throws ParseException{
        List listDate = new ArrayList(); 
        String pattern = "dd/MM/yyyy HH";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        for (String p: date.split(", ")) {
                
                listDate.add(format.parse(p));
            
        }
        return listDate;
    }
   
   
   
    
    public boolean cekEmailExist(String email) throws IOException{
        boolean emailExist =false;
        
       
        
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
       
        
        List<Invitation> listuser = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        List index = new ArrayList();
        
        
        listuser.stream().filter((data) -> (email.equals(data.getEmail()))).forEachOrdered((data) -> {
            index.add(data.getId());
        });
        
       return index;
       
    }
    
    public boolean checkIdInvitation(int id, String email) throws IOException{
        boolean info =false;
        
        JsonNode rootArray = mapper.readTree(new File("invitation.json"));
        for(JsonNode root : rootArray){
            
            if(id == (root.path("id").asInt()) && email.equals(root.path("email").asText())){
                info = true;
            }
            
        } 
        
        return info;
    }
    
    /***************************************************************************/
    
                            /* Uses for edit meeting */
    public boolean checkIdandEmailInitiator(int id, String email) throws IOException{
        boolean info = false;
        
        JsonNode rootArray = mapper.readTree(new File("meeting.json"));
        for(JsonNode root: rootArray){
            if(id == (root.path("id").asInt()) && email.equals(root.path("emailM").asText())){
                info = true;
            }
        }
        return info;
    }
    
    
    public Integer getIndexMeeting(int id, String email) throws IOException{
        int getindex = -999;
        ObjectMapper mapper = new ObjectMapper();
        List<Meeting> data = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        
        int index =0;
        
        for(Meeting usr:data){
            if(email.equals(usr.getEmailM()) && id==(usr.getId())){
                 getindex=index ;
            }
           index ++;
        }
        
       return getindex;
    }
    
    /***************************************************************************/
    
    /* Uses for edit file invitation  */
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

    
    /* tambahan rizki */
    public boolean checkString(String data){
        boolean status = false;
        if(!data.isEmpty()){
            status = true;
        }
        return status;
    }
    
    public Boolean isEmailFormatValid(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }
    
    public boolean checkDomainEmail(String data){
        boolean status = false;
        if(data.contains("@kantor.com")){
            status = true;
        }
        return status;
    }
    
    public boolean checkLengthUser(String data){
        boolean status = false;
        if(data.length() > 2){
            status = true;
        }
        return status;
    } 
            
    public boolean checkName(String data){
        String special = "!@#$%^&*()_";
        String pattern = ".*[" + Pattern.quote(special) + "].*";
        boolean status = false;
        if(data.matches(pattern)){
            status = true;
        }
        return status;
    }

    public boolean checkLengthMeeting(String data){
        boolean status = false;
        if(data.length() > 4){
            status = true;
        }
        return status;
    }
    
    public boolean checkLokasi(String data){
        Pattern room = Pattern.compile("Room01|Room02|Room03|Room04|Room05");
        Matcher foundAMatch = room.matcher(data);
        boolean status = false;
        while(foundAMatch.find()) status = true;
        return status;
    }
    
    public boolean checkDurasi(Integer data){
        boolean status = false;
        if(data > 0 && data < 7){
            status = true;
        }
        return status;
    }
    
    public boolean checkAdmin(String data){
        boolean status = false;
        if(data.equals("Yes") || data.equals("No")){
            status = true;
        }
        return status;
    }
    
//    public boolean checkDate(String tempdate){
//        boolean status = false;
//        String pattern = "dd/MM/yyyy";
//        SimpleDateFormat format = new SimpleDateFormat(pattern);
//        Date StartDate = format.parse(tempDate.substring(0, 10));
//        Date EndDate = format.parse(tempDate.substring(11, 21));
//        if(data.equals("Yes") || data.equals("No")){
//            status = true;
//        }
//        return status;
//    }
    
    
}
