/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.mycompany.app.Model.Meeting;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Invitation;
import com.mycompany.app.Model.MeetingStatus;
import com.mycompany.app.Services.CheckData;


import com.mycompany.app.View.MenuUser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ucong18
 */
public class MeetingController {
    private final  Meeting mt;
    private final  Invitation iV;
    private final  MenuUser menuUser;
    
    CheckData CD = new CheckData();
    
    public MeetingController(Meeting dataMeeting, MenuUser mu, Invitation iV ){
        this.mt = dataMeeting;
        this.menuUser = mu;
        this.iV = iV;
    }
    
    public List bacaFileMeeting(String namafile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
         List<Meeting> dataMeeting = mapper.readValue(new File(namafile),new TypeReference<List<Meeting>>(){});
        return dataMeeting;
        
    }
    
    public List bacaFileInvitation(String namafile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
         List<Invitation> data = mapper.readValue(new File(namafile),new TypeReference<List<Invitation>>(){});
        return data;
        
    }
    
    
    public void saveFileMeeting(List dM, String namafile) throws IOException  {
	ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(namafile),dM);
    }
    
    
    public Integer getLastId() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(new File("meeting.json"));
        int id=0;
        if(rootArray.size() == 0){
            id=1;
        }else{
            id =(rootArray.get(rootArray.size()-1).path("id").asInt());
        }
        
        
        return id;
    }
    
    public void printemail() throws IOException, ParseException{
       
       
       List data = menuUser.formAddMeeting();
       
       String emailInisiator = (String) data.get(9);
    }
    
     /* ============================================================================== */
    
     /*                  Create Meeting Berdasarkan id                               */    
     
     /* ============================================================================== */
    
    public void createMeeting(String email) throws IOException, ParseException{
      
       
       ObjectMapper mapper = new ObjectMapper();
       
       List<Meeting> tempData = bacaFileMeeting("meeting.json");
       
       
       
       int id = getLastId()+1;
       
       
       List data = menuUser.formAddMeeting();
       List participant = new ArrayList<>();
       List vip = new ArrayList<>();
       
       String judul  =   (String)  data.get(0);
       String agenda  =  (String)  data.get(1);
       String lokasi  =  (String)  data.get(2);
       Integer durasi =  (Integer) data.get(3);
       
       String temp = null, temp1=null;
       temp = (String) data.get(4);
       participant = CD.splitData(temp);
       
       temp1 = (String) data.get(5);
       vip = CD.splitData(temp1);
       
       Date tglAwal  =  (Date) data.get(6);
       Date tglAhir  =  (Date) data.get(7);
       Date deadLine =  (Date) data.get(8);
       
      
        
       mt.setIdM(id);
       mt.setEmailM(email);
       mt.setVip(participant);
       mt.setParcipantM(vip);
       
       mt.setJudulM(judul);
       mt.setAgendaM(agenda);
       mt.setLokasiM(lokasi);
       mt.setDurasiM(durasi);
       
       mt.setTawal(tglAwal);
       mt.setTAkhir(tglAhir);
       mt.setDeadLine(deadLine);
       
       mt.setStatusM("NEGOTIATING");
      // mt.setStatusM(MeetingStatus.NEGOTIATING);
       
       tempData.add(mt);
       saveFileMeeting(tempData,"meeting.json");
       
       /* Invitation  */
       String dataUndangan = data.get(4) +", " + data.get(5);
       System.out.println(dataUndangan);
       
       List undangan = CD.splitData(dataUndangan);
       System.out.println(undangan);
       
       for(int i=0; i<undangan.size();i++){
           List<Invitation> tempInv = bacaFileInvitation("invitation.json");
           iV.SetId(id);
           iV.setEmail((String) undangan.get(i));
           iV.setStatus("WAITING");
           tempInv.add(iV);
           saveFileMeeting(tempInv,"invitation.json");
       }
       
    }
    
    
    
     /* ============================================================================== */
    
     /*                  Edit Meeting Berdasarkan id                               */    
     
     /* ============================================================================== */
    
    public void editMeeting(int id, String email ) throws IOException, ParseException{
        
        Meeting meeting = new Meeting();
        Invitation invit = new Invitation();
        
        ObjectMapper mapper = new ObjectMapper();
        
        List<Meeting> dataMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        List<Invitation> datainvit = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        String vip = null, participant = null;
        
         
        /*
        
            set status, set datefix, set id, set email iniator, 
        */
        
        List p = null, v = null;
        String s = null;
        
       
        for(Meeting usr:dataMeeting){
            if(email.equals(usr.getEmailM()) && id==(usr.getId())){
                  p = usr.getParcipantM();
                  v = usr.getVip();
                  s = usr.getStatusM();
            }
           
        }
        
//        System.out.println(p);
//        System.out.println(v);
//        System.out.println(s);
        
        
        /* check email dan id harus sama, id yang di edit sesuai email iniator yang terdaftar */
       if(CD.checkIdandEmailInitiator(id, email)){
                 Integer index = CD.getIndexMeeting(id, email);
                
                 meeting = menuUser.formEditMeeting();
                
                 meeting.setIdM(id);
                 meeting.setEmailM(email);
                 meeting.setVip(v);
                 meeting.setParcipantM(p);
                 meeting.setStatusM(s);
                 
                 dataMeeting.set(index, meeting);
                  
        
                 saveFileMeeting(dataMeeting,"meeting.json");
                 
           
       }else{
           System.out.println("Salah");
       }
    }
    
    /* ============================================================================== */
    
     /*                  Cancel Meeting Berdasarkan id                               */    
     
     /* ============================================================================== */
    
    public void cancelMeeting(int id, String email) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        List<Invitation> invit = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        
        boolean info = false;
        
        
        for(Meeting usr:lstMeeting){
            if(email.equals(usr.getEmailM()) && id==(usr.getId())){
                 usr.setStatusM("CANCELED");
                 info = true;
            } 
        }
        
        for(Invitation inv:invit){
            if(id==(inv.getId())){
                 inv.setStatus("CANCELED");
            } 
        }
        
        if(!info){
            System.out.println("id meeting tidak ditemukan ");
        }else{
            saveFileMeeting(lstMeeting,"meeting.json");
            saveFileMeeting(invit,"invitation.json");
        }
    }
}
