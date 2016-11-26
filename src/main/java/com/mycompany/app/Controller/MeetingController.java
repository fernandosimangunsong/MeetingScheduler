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
        int id =(rootArray.get(rootArray.size()-1).path("id").asInt());
        
        
        return id;
    }
    
    public void printemail() throws IOException, ParseException{
       
       
       List data = menuUser.formAddMeeting();
       
       String emailInisiator = (String) data.get(9);
    }
    
    public void createMeeting(String email) throws IOException, ParseException{
       CheckData CD = new CheckData();
       
       ObjectMapper mapper = new ObjectMapper();
       
       List<Meeting> tempData = bacaFileMeeting("meeting.json");
       
       
       
       int id = getLastId()+1;
       
       
       List data = menuUser.formAddMeeting();
       List participant = new ArrayList<>();
       
       String judul  =   (String)  data.get(0);
       String agenda  =  (String)  data.get(1);
       String lokasi  =  (String)  data.get(2);
       Integer durasi =  (Integer) data.get(3);
       
       String temp = null;
       temp = (String) data.get(4);
       participant = CD.splitData(temp);
       
       temp = (String) data.get(5);
       participant = CD.splitData(temp);
       
       Date tglAwal  =  (Date) data.get(6);
       Date tglAhir  =  (Date) data.get(7);
       Date deadLine =  (Date) data.get(8);
       
      
        
       mt.setIdM(id);
       mt.setEmailM(email);
       mt.setVip(participant);
       mt.setParcipantM(participant);
       
       mt.setJudulM(judul);
       mt.setAgendaM(agenda);
       mt.setLokasiM(lokasi);
       mt.setDurasiM(durasi);
       
       mt.setTawal(tglAwal);
       mt.setTAkhir(tglAhir);
       mt.setDeadLine(deadLine);
       
       mt.setStatusM("Negosiasi");
       
       tempData.add(mt);
       saveFileMeeting(tempData,"meeting.json");
       
       /* Invitation */
       String dataUndangan = data.get(4) +", " + data.get(5);
       participant = CD.splitData(dataUndangan);
       
       for(int i=0; i<participant.size();i++){
           List<Invitation> tempInv = bacaFileInvitation("invitation.json");
           iV.SetId(id);
           iV.setEmail((String) participant.get(i));
           iV.setStatus("Negosiasi");
           tempInv.add(iV);
           saveFileMeeting(tempInv,"invitation.json");
       }
       
    }
}
