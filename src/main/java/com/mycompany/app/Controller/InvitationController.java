/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Invitation;
import com.mycompany.app.Services.CheckData;
import com.mycompany.app.View.MenuUser;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ucong18
 */
public class InvitationController {
    
    private final Invitation invitation;
    private final MenuUser menuUser;
    
    
    public InvitationController(Invitation invitation, MenuUser menuUser){
        this.invitation =invitation;
        this.menuUser =menuUser;
    }
    
    public List bacaFileInvitation(String namafile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        List<Invitation> data = mapper.readValue(new File(namafile),new TypeReference<List<Invitation>>(){});
        return data;
        
    }
    
    public void saveFileMeeting(List dM) throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("invitation.json"),dM);
    }
    
    public void listAllMyInvitation(String email) throws IOException{
        /* menampilkan undangan berdasarkan email */
        CheckData cd = new CheckData();
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> listdata = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        
        List id = new ArrayList();
        id = cd.getIdInvitation(email);
        
        if(!id.isEmpty()){
          for(Invitation data:listdata){
                if(email.equals(data.getEmail())){
                  System.out.println("Id     = "+data.getId());
                  System.out.println("Status  = "+data.getStatus());
                }
           }  
        }else{
            System.out.println("Tidak ada invitation untuk anda");
        }
        
    }
    
    public void editMyInvitation(int id,String email) throws IOException, ParseException{
        CheckData cd = new CheckData();
        Invitation data = new Invitation();
        
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> datainvit = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        if(cd.checkIdInvitation(id,email)){
                System.out.println("konfirmasi untuk meeting dengan id =" + id);
                
                 Integer index = cd.getIndexSetEditInvitation(id, email);
                 
                 System.out.println(index);
                 
                 data = menuUser.formInvitation();
                 
                 data.SetId(id);
                 data.setEmail(email);
                 
                 datainvit.set(index,data);
          
                 saveFileMeeting(datainvit);
                
                
        }else{
            System.out.println("tidak ada");
        }
    }
    
   
    
}
