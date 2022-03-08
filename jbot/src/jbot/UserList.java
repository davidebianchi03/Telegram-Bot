/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class UserList {

    private List<User> userList;
    private static UserList instance = null;
    private final String fileName = "users.csv";

    private UserList() throws FileNotFoundException {
        userList = new LinkedList<>();
        //carico la lista degli utenti dal file se esiste
        File file = new File(fileName);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line != "") {
                    userList.add(User.LoadFromCsv(line));
                }
            }
        }
    }

    public static UserList GetInstance() {
        if (instance == null) {
            synchronized (UserList.class) {
                try {
                    instance = new UserList();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return instance;
    }

    public synchronized void AddUser(User user) {
        userList.add(user);
        //scrivo il file
        String stringToWrite = user.toCSV();
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            //se non esiste il file lo creo
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(fileName, true);//apro il file in modalità append
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(stringToWrite);
        } catch (IOException ex) {
            Logger.getLogger(ADVList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            } //exception handling left as an exercise for the reader
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
    }

}
