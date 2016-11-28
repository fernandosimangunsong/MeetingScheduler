/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Invitation;
import com.mycompany.app.Model.Meeting;
import com.mycompany.app.Services.CheckData;
import com.mycompany.app.View.MenuParticipant;
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
    private final MenuParticipant menuParticipant;
    
    
    public InvitationController(Invitation invitation, MenuUser menuUser, MenuParticipant menuParticipant){
        this.invitation =invitation;
        this.menuUser =menuUser;
        this.menuParticipant = menuParticipant;
    }
    
    public List bacaFileInvitation(String namafile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        List<Invitation> data = mapper.readValue(new File(namafile),new TypeReference<List<Invitation>>(){});
        return data;
        
    }
    
    public void saveFileInvitation(List dM) throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("invitation.json"),dM);
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
                 
                 data = menuParticipant.formInvitation();
                 
                 data.SetId(id);
                 data.setEmail(email);
                 
                 datainvit.set(index,data);
          
                 saveFileInvitation(datainvit);
                
                
        }else{
            System.out.println("tidak ada");
        }
    }
    
   
    public void rejectInvitation(int id, String email) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        
        List<Invitation> invit = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        boolean info = false;
        
        
        
        for(Invitation inv:invit){
            if(id==(inv.getId()) && email.equals(inv.getEmail())){
                        //System.out.println("ubah");
                        inv.setStatus("REJECTED");
                        info=true;
            } 
        }
        
        if(!info){
            System.out.println("id meeting tidak ditemukan ");
        }else{
            saveFileInvitation(invit);
        }
    }
}
