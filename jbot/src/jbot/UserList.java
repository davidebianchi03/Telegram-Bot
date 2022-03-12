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

    public User GetUserAt(int index) {
        return userList.get(index);
    }

    public int GetSize() {
        return userList.size();
    }

    public synchronized boolean AddUser(User user) {
        boolean already_exists = false;
        int pos = -1;
        //trovo la posizione dell'utente se esiste
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getChat_id() == user.getChat_id()) {
                pos = i;
                break;
            }
        }
        //controllo se l'utente esiste di già
        if (pos != -1) {
            already_exists = true;
            //aggiorno l'elemento salvato nella lista
            userList.get(pos).setCoordinate(user.getCoordinate());
            //riscrivo tutto il file per aggiornarlo
            String csvString = "";
            for (int i = 0; i < userList.size(); i++) {
                csvString += userList.get(i).toCSV() + "\n";
            }
            WriteString2File(csvString, fileName, false);

        } else {
            userList.add(user);
            //scrivo il file
            String stringToWrite = user.toCSV();
            WriteString2File(stringToWrite, fileName, true);
        }

        return already_exists;
    }

    private void WriteString2File(String stringToWrite, String filename, boolean append) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            //se non esiste il file lo creo
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(filename, append);//apro il file in modalità append
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
