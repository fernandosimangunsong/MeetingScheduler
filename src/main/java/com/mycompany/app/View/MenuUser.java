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






public class MenuUser {
    
    
    CheckData cd = new CheckData();
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
    
    /* ============================================================================== */
    
     /*                  List Meeting Berdasarkan status Pertemuan                     */    
     
     /* ============================================================================== */
    
    
    public void printAllMeeting(String email) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        
        String status =null;
        
        boolean info = false;
        status = "NEGOTIATING";
      
        
        
        System.out.println("List Meeting " + status +" :");
        
        for(Meeting mt:lstMeeting){
            if(email.equals(mt.getEmailM()) && status.equals(mt.getStatusM())){
                  System.out.print  ("(ID = "+mt.getId()+")");
                  System.out.println("   Agenda        = "+mt.getJudulM());
                  info = true;
            }
        }
        
        if(!info){System.out.println("Empty");}
        
        info = false;
        status = "CANCELED";
        System.out.println("List Meeting " + status +" :");
        
        for(Meeting mt:lstMeeting){
            if(email.equals(mt.getEmailM()) && status.equals(mt.getStatusM())){
                  System.out.print  ("(ID = "+mt.getId()+")");
                  System.out.println("   Agenda        = "+mt.getJudulM());
                  info = true;
            }
        }
        if(!info){System.out.println("Empty");}
        
        info = false;
        status = "CONFIRMED";
        System.out.println("List Meeting " + status +" :");
        
        for(Meeting mt:lstMeeting){
            if(email.equals(mt.getEmailM()) && status.equals(mt.getStatusM())){
                  System.out.print  ("(ID = "+mt.getId()+")");
                  System.out.println("   Agenda        = "+mt.getJudulM());
                  info = true;
            }
        }
        if(!info){System.out.println("Empty");}
        
        info = false;
        status = "RUNNING";
        System.out.println("List Meeting " + status +" :");
        
        for(Meeting mt:lstMeeting){
            if(email.equals(mt.getEmailM()) && status.equals(mt.getStatusM())){
                  System.out.print  ("(ID = "+mt.getId()+")");
                  System.out.println("   Agenda        = "+mt.getJudulM());
                  info = true;
            }
        }
        if(!info){System.out.println("Empty");}
        
        
        info = false;
        status = "FINISHED";
        System.out.println("List Meeting " + status +" :");
        
        for(Meeting mt:lstMeeting){
            if(email.equals(mt.getEmailM()) && status.equals(mt.getStatusM())){
                  System.out.print  ("(ID = "+mt.getId()+")");
                  System.out.println("   Agenda        = "+mt.getJudulM());
                  info = true;
            }
        }
        if(!info){System.out.println("Empty");}
        
        
        
           
    }
    
    
    
    /* ============================================================================== */
    
     /*           Detail Meeting Berdasarkan id   dan data invitation               */    
     
     /* ============================================================================== */
    
    public void detailMeeting(int id, String email) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        
        List<Meeting> lstMeeting = mapper.readValue(new File("meeting.json"),new TypeReference<List<Meeting>>(){});
        List<Invitation> datainvit = mapper.readValue(new File("invitation.json"),new TypeReference<List<Invitation>>(){});
        boolean info = false;
        for(Meeting usr:lstMeeting){
            if(email.equals(usr.getEmailM()) && id==(usr.getId())){
                 System.out.println("Title : " +usr.getJudulM() );
                 System.out.println("Agenda : " +usr.getAgendaM());
                 System.out.println("Lokasi : " +usr.getLokasiM());
                 System.out.println("Durasi (Jam) : " +usr.getDurasiM());
                 System.out.println("Proposed Date : " +usr.getTawal()+ " - " +usr.getTAkhir());
                 System.out.println("Participant Vip : " +usr.getVip());
                 System.out.println("Participant : " +usr.getParcipantM());
                 
                 info = true;
            } 
            
        }
        
        for(Invitation data:datainvit){
                if( id ==(data.getId())){
                  System.out.print("(Email : "+data.getEmail()+") ");
                  System.out.print(", ");
                  System.out.print("Status  : "+data.getStatus());
                  System.out.println("");
                }
         }
        if(!info){System.out.println("id meeting tidak ditemukan ");}
    }
    
    
    
    
    /* ============================================================================== */
    
             /*                    Form Add Meeting                            */    
     
    /* ============================================================================== */
    
     public List formAddMeeting() throws ParseException, IOException{
        List addDataMeeting = new ArrayList<>();
        String peserta=null,vip=null,tempDate=null, judul=null, agenda=null,lokasi=null,email=null;
        Integer durasi;
               
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.print("Judul  :");
        judul =reader.readLine();

        while(!cd.checkLengthMeeting(judul)){
            System.out.println("Judul minimal 5 karakter");
            System.out.print("Judul  :");
            judul = reader.readLine();
        }

        System.out.print("Agenda :");
        agenda =reader.readLine();

        while(!cd.checkLengthMeeting(agenda)){
            System.out.println("Agenda minimal 5 karakter");
            System.out.print("Agenda  :");
            agenda = reader.readLine();
        }

        System.out.print("Lokasi (Room01 - Room05) :");
        lokasi =reader.readLine();
        //memeriksa apakah data diisi atau tidak
        while(!cd.checkString(lokasi)){
            System.out.println("Data tidak boleh kosong");
            System.out.print("Lokasi (Room01 - Room05) :");
            lokasi = reader.readLine();
            while(!cd.checkLokasi(lokasi)){
                System.out.println("Ruangan tidak ditemukan");
                System.out.print("Lokasi (Room01 - Room05)  :");
                lokasi = reader.readLine();
            }
        }

        System.out.print("Durasi (1 - 6) :");
        String temp = reader.readLine();
        durasi = Integer.parseInt(temp);

        while(!cd.checkDurasi(durasi)){
            System.out.println("Inputan tidak valid");
            System.out.print("Durasi (1 - 6) :");
            temp = reader.readLine();
            durasi = Integer.parseInt(temp);
        }


        boolean valid=false;
        List dataVip = null;
        while(!valid){
            System.out.print("Peserta VIP :");
            vip = reader.readLine();
            if(vip.isEmpty()){
                valid = false;
            } else {
                dataVip = cd.splitData(vip);
                for (int i = 0; i < dataVip.size(); i++) {
                    if(cd.cekEmailExist((String) dataVip.get(i))){
                        valid = true;
                    } else {
                        valid = false;
                    }
                }
            }
        }

        valid=false;
        while(!valid){
            System.out.print("Peserta Umum :");
            peserta = reader.readLine();
            if(peserta.isEmpty()){
                valid = false;
            } else {
                List dataPeserta = cd.splitData(peserta);
                for (int i = 0; i < dataPeserta.size(); i++) {
                    if(cd.cekEmailExist((String) dataPeserta.get(i))){
                        valid = true;
                    } else {
                        valid = false;
                    }
                }
                for (int i = 0; i < dataPeserta.size(); i++) {
                    for (int j = 0; j < dataVip.size(); j++) {
                        if(dataPeserta.get(i).equals(dataVip.get(j))){
                            valid = false;
                        }
                    }

                }
            }
        }

        System.out.println("Rentang Tanggal Yang Dapat diusulkan");
        System.out.println("Contoh  Inputan : DD/MM/YYYY-DD/MM/YYYY");
        System.out.print  ("Masukkan Tanggal:");
        tempDate = reader.readLine();
        //memeriksa apakah data diisi atau tidak
        while(!cd.checkString(tempDate)){
            System.out.println("Data tidak boleh kosong");
            System.out.print("Tanggal  :");
            tempDate = reader.readLine();
        }


        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date StartDate = format.parse(tempDate.substring(0, 10));
        Date EndDate = format.parse(tempDate.substring(11, 21));

        Calendar cal = Calendar.getInstance();
        cal.setTime(StartDate);
        cal.add(Calendar.DATE, -3);
        Date negotiationDeadline = cal.getTime();
        System.out.println(negotiationDeadline);
                
        
        addDataMeeting.add(judul);
        addDataMeeting.add(agenda);
        addDataMeeting.add(lokasi);
        addDataMeeting.add(durasi);
        
        addDataMeeting.add(vip);
        addDataMeeting.add(peserta);
        
        addDataMeeting.add(StartDate);
        addDataMeeting.add(EndDate);
        addDataMeeting.add(negotiationDeadline);
        
        
                
        
        return addDataMeeting;
       
    }
     
     /* ============================================================================== */
    
     /*                           Form Edit Meeting                                    */    
     
     /* ============================================================================== */
    
     public Meeting formEditMeeting() throws ParseException, IOException{
        Meeting meeting = new Meeting();
        
        List addDataMeeting = new ArrayList<>();
        
        String peserta=null,vip=null,tempDate=null, judul=null, agenda=null,lokasi=null,email=null;
        Integer durasi;
               
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.print("Judul  :");
        judul =reader.readLine();

        while(!cd.checkLengthMeeting(judul)){
            System.out.println("Judul minimal 5 karakter");
            System.out.print("Judul  :");
            judul = reader.readLine();
        }

        System.out.print("Agenda :");
        agenda =reader.readLine();

        while(!cd.checkLengthMeeting(agenda)){
            System.out.println("Agenda minimal 5 karakter");
            System.out.print("Agenda  :");
            agenda = reader.readLine();
        }

        System.out.print("Lokasi (Room01 - Room05) :");
        lokasi =reader.readLine();
        //memeriksa apakah data diisi atau tidak
        while(!cd.checkString(lokasi)){
            System.out.println("Data tidak boleh kosong");
            System.out.print("Lokasi (Room01 - Room05) :");
            lokasi = reader.readLine();
            while(!cd.checkLokasi(lokasi)){
                System.out.println("Ruangan tidak ditemukan");
                System.out.print("Lokasi (Room01 - Room05)  :");
                lokasi = reader.readLine();
            }
        }

        System.out.print("Durasi (1 - 6) :");
        String temp = reader.readLine();
        durasi = Integer.parseInt(temp);

        while(!cd.checkDurasi(durasi)){
            System.out.println("Inputan tidak valid");
            System.out.print("Durasi (1 - 6) :");
            temp = reader.readLine();
            durasi = Integer.parseInt(temp);
        }




        System.out.println("Rentang Tanggal Yang Dapat diusulkan");
        System.out.println("Contoh  Inputan : DD/MM/YYYY-DD/MM/YYYY");
        System.out.print  ("Masukkan Tanggal:");
        tempDate = reader.readLine();
        //memeriksa apakah data diisi atau tidak
        while(!cd.checkString(tempDate)){
            System.out.println("Data tidak boleh kosong");
            System.out.print("Tanggal  :");
            tempDate = reader.readLine();
        }


        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date StartDate = format.parse(tempDate.substring(0, 10));
        Date EndDate = format.parse(tempDate.substring(11, 21));

        Calendar cal = Calendar.getInstance();
        cal.setTime(StartDate);
        cal.add(Calendar.DATE, -3);
        Date negotiationDeadline = cal.getTime();
        System.out.println(negotiationDeadline);
                
        
        meeting.setJudulM(judul);
        meeting.setAgendaM(agenda);
        meeting.setLokasiM(lokasi);
        meeting.setDurasiM(durasi);
        
        /* participant gk di hapus tapi di tambah */
        //addDataMeeting.add(vip);
        //addDataMeeting.add(peserta);
        
        meeting.setTawal(StartDate);
        meeting.setTAkhir(EndDate);
        meeting.setDeadLine(negotiationDeadline);
        
        
        return meeting;
       
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
    
             /*                    Kumpulan Menu User                             */    
     
    /* ============================================================================== */
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

                    case "create-meeting":
                            MC.createMeeting(email);
                        break;

                    case "list-meeting":
                            printAllMeeting(email);
                        break;

                    case "detail-meeting":
                            id =input.nextInt();
                            detailMeeting(id,email);
                        break;
                        
                    case "cancel-meeting":
                            id =input.nextInt();
                            MC.cancelMeeting(id,email);
                        break;

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
                    
                    case "edit-meeting":
                            id =input.nextInt();
                            MC.editMeeting(id,email);
                            
                            break;
                            
                    case "run-scheduler":
                            id =input.nextInt();
                            break;
                    
                    case "e":
                           MC.printemail();
                            //id =input.nextInt();
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
