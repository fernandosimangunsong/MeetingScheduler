/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Model;

/**
 *
 * @author ucong18
 */
import java.util.Date;
import java.util.List;

/**
 *
 * @author Novan
 */
public class Meeting{
   private Integer id;
   private String emailInisiatorM;
   private List particpantM;
   private List vipM;
   
   private String judulM;
   private String agendaM;
   private String lokasiM;
   private Integer durasiM;
  
   
   private Date tglAwal;
   private Date tglAkhir;
   private Date deadLine;
   private Date dateFix;
   
   private String statusM;
   
   
   
   public Meeting(){}
   
   
  
   public Meeting(Integer id,String email,List vip,List particpant, String judul,String agenda,
                    String lokasi,Integer durasi){
       this.id = id;
       this.emailInisiatorM=email;
       this.vipM = vip;
       this.particpantM=particpant;
  
       this.judulM=judul;
       this.agendaM=agenda;
       this.lokasiM=lokasi;
       this.durasiM=durasi;
   }
   
  /* seter  */
    public void setIdM(Integer id){
          this.id = id;
    }
    public void setEmailM(String email){
          this.emailInisiatorM = email;
    }
  
    public void setVip(List vip){
         this.vipM = vip;
    }
    public void setParcipantM(List particpant){
         this.particpantM = particpant;
    }

    public void setJudulM(String judulM){
         this.judulM = judulM;
    }

   public void setAgendaM(String agendaM){
       this.agendaM = agendaM;
   }
   
   public void setLokasiM(String lokasiM){
       this.lokasiM = lokasiM;
   }
   
   public void setDurasiM(Integer durasiM){
       this.durasiM = durasiM;
   }
   
   public void setTawal(Date awal){
       this.tglAwal = awal;
   }
   
   public void setTAkhir(Date akhir){
       this.tglAkhir = akhir;
   }
   
   public void setDeadLine(Date deadLine){
       this.deadLine = deadLine;
   }
   
   public void setDateFix(Date fix){
       this.dateFix = fix;
   }
   
   public void setStatusM(String statusM){
       this.statusM = statusM;
   }
   
   
   /* getter */
   public Integer getId(){
       return id;
   }
   public String getEmailM(){
       return emailInisiatorM ;
   }
   
   public List getVip(){
       return vipM ;
   }
   public List getParcipantM(){
       return particpantM ;
   }
   
  
   public String getJudulM(){
       return judulM ;
   }
  
   public String getAgendaM(){
        return agendaM ;
   }
   
   public String getLokasiM(){
        return lokasiM ;
   }
   
   public Integer getDurasiM(){
       return durasiM ;
   }
   
   public Date getTawal(){
       return tglAwal;
   }
   
   public Date getTAkhir(){
       return tglAkhir;
   }
   
   public Date getDeadLine(){
       return deadLine;
   }
   
   public Date getDateFix(){
       return dateFix;
   }
  
   public String getStatusM(){
        return statusM ;
   }
}
