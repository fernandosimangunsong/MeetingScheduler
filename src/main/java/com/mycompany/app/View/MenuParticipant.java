/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Controller.InvitationController;
import com.mycompany.app.Controller.MeetingController;
import com.mycompany.app.Model.Invitation;
import com.mycompany.app.Model.Meeting;
import com.mycompany.app.Services.CheckData;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ucong18
 */






public class MenuParticipant {
    
    
    CheckData cd = new CheckData();
    private String emailUser;
    
    public MenuParticipant(){}
    
    public MenuParticipant(String email){
        this.emailUser = email;
    }
    
    public void setEmailUser(String email){
        this.emailUser = email;
    }
    
    public String getEmailUser(){
        return emailUser;
    }
    
     /* ============================================================================== */
    
             /*                    Form Invitaion                             */    
     
    /* ============================================================================== */
    
    public Invitation formInvitation() throws IOException, ParseException{
        Invitation iv = new Invitation();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       
        String status,temp;
        List availability = new ArrayList<>();
        
//        Scanner input = new Scanner(System.in);
        
//        System.out.println("Invitation");
//        System.out.print("Status :");
//        status =reader.readLine();       
        /*
          contoh isi temp:  11/11/2016 6, 12/11/2016 7
        */
        
        System.out.print("Waktu yang tersedia :");
        temp =reader.readLine();
        
        /* ubah tanggal yang dalam bentuk string ke format date */
        availability = cd.splitData2(temp);
        

                
        iv.setStatus("CONFIRMED");
        iv.setAvailability((ArrayList) availability);
        
        return iv;
       
    }
    
     /* ============================================================================== */
    
             /*                   List All Invitation By Email                           */    
     
    /* ============================================================================== */
    
    public void listAllMyInvitation(String email) throws IOException{
        /* menampilkan undangan berdasarkan email */
        CheckData cd = new CheckData();
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> listdata = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        
        List id = new ArrayList();
        id = cd.getIdInvitation(email);
       
        
        if(!id.isEmpty()){
          for(Invitation data:listdata){
                if(email.equals(data.getEmail())){
                  System.out.print("(Id : "+data.getId()+") ");
                  System.out.print(", ");
                  System.out.print("Status  : "+data.getStatus());
                  System.out.println("");
                }
           }
          
        }else{
            System.out.println("Tidak ada invitation untuk anda");
        }
        
    }
    
     /* ============================================================================== */
    
         /*          Detail Invitation By Id meeting & email                   */    
     
    /* ============================================================================== */
    public void detailMyInvitation(int idx, String email) throws IOException{
        /* menampilkan undangan berdasarkan email */
        CheckData cd = new CheckData();
        ObjectMapper mapper = new ObjectMapper();
        List<Invitation> listdata = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        
        List id = new ArrayList();
        id = cd.getIdInvitation(email);
       
        
        if(!id.isEmpty()){
          for(Invitation data:listdata){
                if(email.equals(data.getEmail()) && idx==(data.getId())){
                  System.out.print("(Id : "+data.getId()+") ");
                  System.out.print(", ");
                  System.out.print("Status  : "+data.getStatus());
                  System.out.println("");
                }
           }
          
           for(Meeting usr:lstMeeting){
                if( idx==(usr.getId())){
                     System.out.println("Title : " +usr.getJudulM() );
                     System.out.println("Agenda : " +usr.getAgendaM());
                     System.out.println("Lokasi : " +usr.getLokasiM());
                     System.out.println("Durasi (Jam) : " +usr.getDurasiM());
                     System.out.println("Proposed Date : " +usr.getTawal()+ " - " +usr.getTAkhir());
                     System.out.println("Participant Vip : " +usr.getVip());
                     System.out.println("Participant : " +usr.getParcipantM());

                     
                } 
            }
        }else{
            System.out.println("Tidak ada invitation untuk anda");
        }
        
    }
    
    /* ============================================================================== */
    
             /*                    Menu Help                             */    
     
    /* ============================================================================== */
    
    public void formHelpParticipant(){
        System.out.println("1. list-invitation, melihat daftar semua invitation.");
        System.out.println("2. detail-invitation <meeting-id>, melihat detil sebuah invitation.");
        System.out.println("3. accept-invitation <meeting-id>, menerima invitation.");
        System.out.println("4. reject-invitation <meeting-id>, menolak invitation.");
    }
    
    /* ============================================================================== */
    
             /*                    Kumpulan Menu User                             */    
     
    /* ============================================================================== */
    public String operasiParticipant() throws IOException, ParseException{
        Meeting meeting = new Meeting();
        Invitation invit = new Invitation();
        MenuUser   mu    = new MenuUser();
        MenuParticipant mp = new MenuParticipant();
        
            
        MeetingController MC = new MeetingController(meeting, mu, invit);
        InvitationController IV = new InvitationController(invit, mu, mp);
        
        
        Scanner input = new Scanner(System.in);
        String menu;
        String email = getEmailUser();
        
            int id;
        
            System.out.println("============ Menu Participant =================== ");
            System.out.print  (">");
            menu = input.next();
            
            
            switch (menu) {

                    case "list-invitation":
                            listAllMyInvitation(email);
                            break;
                            
                            
                    case "detail-invitation":
                            id =input.nextInt();
                            detailMyInvitation(id,email);
                            break;
                    
                    case "reject-invitation":
                            id =input.nextInt();
                            IV.rejectInvitation(id,email);
                            break;   
                            
                    case "accept-invitation":
                            id =input.nextInt();
                            IV.editMyInvitation(id,email);
                            break;
                    
                    
                    case "help":
                            formHelpParticipant();
                            break;
                    
                            
                    case "exit":
//                         System.exit(0);
                         break;
                    default:
                        System.out.println("Sintaks error");
                        break;
             }
         
       return menu;
    }
}
