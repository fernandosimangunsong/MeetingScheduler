/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Model;
import java.util.ArrayList;
/**
 *
 * @author ucong18
 */
public class Invitation {
    private Integer id;
    private String email;
    private ArrayList Availability;
    private String status;
//    private String judul;
//    private String agenda;
    

    public Invitation(){}
    
    public Invitation(String status){
        this.status = status;
    }
    
    public Invitation(String email, ArrayList Availability, String status, int id) {
        this.id = id;
        this.email = email;
        this.Availability = Availability;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void SetId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList getAvailability() {
        return Availability;
    }

    public void setAvailability(ArrayList Availability) {
        this.Availability = Availability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
//    public String getJudul() {
//        return judul;
//    }
//
//    public void setJudul(String judul) {
//        this.judul = judul;
//    }
//    
//    public String getAgenda(String agenda) {
//        return agenda;
//    }
//
//    public void setAgenda(String agenda) {
//        this.agenda = agenda;
//    }
    

   
    
    
    
}