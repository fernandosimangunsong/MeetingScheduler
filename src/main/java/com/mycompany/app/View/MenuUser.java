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
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ucong18
 */
public class MenuUser {
   
    private String emailUser;
    
    public MenuUser(){}
    
    public MenuUser(String email){
        this.emailUser = email;
    }
    
    public void setEmailUser(String email){
        this.emailUser = email;
    }
    
    public String getEmailUser(){
        return emailUser;
    }
    
    public void printAllMeeting() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        
        System.out.println(getEmailUser());
        
        for(Meeting mt:lstMeeting){
            
                  System.out.println("Initiator     = "+mt.getEmailM());
                  System.out.println("Judul         = "+mt.getJudulM());
                  System.out.println("Agenda        = "+mt.getAgendaM());  
        }
           
    }
    
    public List formAddMeeting() throws ParseException, IOException{
        List addDataMeeting = new ArrayList<>();
        String peserta=null,vip=null,tempDate=null, judul=null, agenda=null,lokasi=null,email=null;
        Integer durasi;
        
       
       
        
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
        
                System.out.print("Judul  :");
                judul =reader.readLine();

                System.out.print("Agenda :");
                agenda =reader.readLine();

                System.out.print("Lokasi :");
                lokasi =reader.readLine();


                System.out.print("Durasi :");
                String temp = reader.readLine();
                durasi = Integer.parseInt(temp);



                System.out.print("Peserta VIP :");
                vip = reader.readLine();
                
                System.out.print("Peserta Umum :");
                peserta = reader.readLine();


                System.out.println("Rentang Tanggal Yang Dapat diusulkan");
                System.out.println("Contoh  Inputan : DD/MM/YYYY-DD/MM/YYYY");
                System.out.print  ("Masukkan Tanggal:");

                tempDate = reader.readLine();


                String pattern = "DD/MM/YYYY";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date StartDate = format.parse(tempDate.substring(0, 10));
                Date EndDate = format.parse(tempDate.substring(11, 21));


                System.out.println("Batas akhir konfirmasi");
                System.out.println("Contoh  Inputan : DD/MM/YYYY");
                System.out.print  ("Masukkan Tanggal:");

                tempDate = reader.readLine();
                Date negotiationDeadline = format.parse(tempDate);
                
        
        
        
        addDataMeeting.add(judul);
        addDataMeeting.add(agenda);
        addDataMeeting.add(lokasi);
        addDataMeeting.add(durasi);
        
        addDataMeeting.add(peserta);
        addDataMeeting.add(vip);
        
        addDataMeeting.add(StartDate);
        addDataMeeting.add(EndDate);
        addDataMeeting.add(negotiationDeadline);
        
        
                
        
        return addDataMeeting;
       
    }
    
    public Invitation formInvitation() throws IOException, ParseException{
        Invitation iv = new Invitation();
        CheckData cd = new CheckData();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       
        String status,temp;
        List availability = new ArrayList<>();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Invitation");
        System.out.print("Status :");
        status =reader.readLine();
        
        
        /*
            11/11/2016 6, 12/11/2016 7
        */
        
        System.out.print("Waktu yang tersedia :");
        temp =reader.readLine();
        
        /* ubah tanggal yang dalam bentuk string ke format date */
        String pattern = "DD/MM/YYYY";
        availability = cd.splitData1(temp);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        for(int i=0; i<availability.size(); i+=2){
                temp = (String) availability.get(i);                
                Date negotiationDeadline = format.parse(temp);
                availability.set(i, negotiationDeadline);
                
        }
        
        /* ubah tanggal yang dalam bentuk string ke format date */
        
        for(int i=1; i<availability.size(); i+=2){
                 temp =  (String) availability.get(i);
                 int var = Integer.parseInt(temp);
                 availability.set(i, var);
                
        }
       
                
        iv.setStatus(status);
        iv.setAvailability((ArrayList) availability);
        
        return iv;
       
    }
    
    public String operasiUser() throws IOException, ParseException{
        Meeting meeting = new Meeting();
        Invitation invit = new Invitation();
        MenuUser   mu    = new MenuUser();
        
            
        MeetingController MC = new MeetingController(meeting, mu, invit);
        InvitationController IV = new InvitationController(invit, mu);
        
        
        Scanner input = new Scanner(System.in);
        String menu;
        String email = getEmailUser();
        
            int id;
        
            System.out.println("============ Menu User =================== ");
            System.out.print  (">");
            menu = input.next();
            
            
            switch (menu) {

                    case "c":
                            MC.createMeeting(email);
                        break;

                    case "p":
                            printAllMeeting();
                        break;

                    case "i":
                            IV.listAllMyInvitation(email);
                            break;

                    case "e":
                           MC.printemail();
                            //id =input.nextInt();
                            break;
                    
                    case "con":
                            id =input.nextInt();
                            IV.editMyInvitation(id,email);
                            
                            break;
                    
                            
                    case "run-scheduler":
                            id =input.nextInt();
                            break;
                    
                    case "exit":
                         System.exit(0);
                         break;
                    default:
                        System.out.println("Sintaks error");
                        break;
             }
         
       return menu;
    }
}
