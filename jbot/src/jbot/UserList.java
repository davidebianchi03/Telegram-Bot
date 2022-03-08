/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jbot;

import java.io.File;
import java.io.FileNotFoundException;
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

}
