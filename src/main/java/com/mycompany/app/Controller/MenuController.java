/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Model.Meeting;
import com.mycompany.app.Model.User;
import com.mycompany.app.View.MenuAdmin;
import com.mycompany.app.View.MenuParticipant;
import com.mycompany.app.View.MenuUser;
import com.mycompany.app.View.MenuUtama;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author ucong18
 */
public class MenuController {

    public MenuController() {

    }

    public void login() throws IOException, ParseException {
        User user = new User();

        MenuUtama mu = new MenuUtama();
        MenuAdmin ma = new MenuAdmin();
        MenuUser ms = new MenuUser();
        MenuParticipant mp = new MenuParticipant();

        ObjectMapper mapper = new ObjectMapper();
        List<User> datauser = mapper.readValue(new File("user.json"), new TypeReference<List<User>>() {
        });
        List datalogin = mu.formLogin();

        String menu = null;
        String email = null;

        if (checklogin(datalogin, datauser)) {
            System.out.println("Login Succses");
            email = (String) datalogin.get(0);

            boolean status, info = false;
            do {
                String role = mu.role();
                switch (role) {
                    case "A":
                        status = true;
                        if (checkIsAdmin(datalogin, datauser, status)) {
                            info = true;
                            System.out.println("Welcome " + datalogin.get(0));

                            while (!"exit".equals(menu)) {

                                menu = ma.operasiAdmin();
                            }
                            if("exit".equals(menu)){
                                login();
                            }

                        } else {
                            System.out.println("you are not an admin ");

                        }
                        break;

                    case "I":
                        System.out.println("Welcome " + datalogin.get(0));
                        //operasi initiator

                        while (!"exit".equals(menu)) {

                            menu = ms.operasiUser();
                        }
                        if("exit".equals(menu)){
                                login();
                            }
                        break;

                    case "P":
                        System.out.println("Welcome " + datalogin.get(0));
                        //operasi Participant

                        while (!"exit".equals(menu)) {

                            menu = mp.operasiParticipant();
                        }
                        if("exit".equals(menu)){
                                login();
                            }
                        break;

                    default:
                        System.out.println("Operasi tidak ada. Sintaks error");
                        break;
                }

            } while (!info);

        } else {
            System.out.println("Login Failed");
            while (!"exit".equals(menu)) {

                login();
            }
        }

    }

    public boolean checklogin(List datalogin, List datauser) throws IOException {
        boolean statuslog = false;
        List<User> listuser = datauser;

        for (User usr : listuser) {
            if ((datalogin.get(0).equals(usr.getEmail())) && (datalogin.get(1).equals(usr.getPassword()))) {
                statuslog = true;
            }
        }

        return statuslog;

    }

    public boolean checkIsAdmin(List datalogin, List datauser, Boolean status) throws IOException {
        boolean statuslog = false;

        List<User> listuser = datauser;

        for (User usr : listuser) {
            if ((datalogin.get(0).equals(usr.getEmail())) && (status.equals(usr.getIsAdmin()))) {
                statuslog = true;
            }
        }

        return statuslog;

    }

}
