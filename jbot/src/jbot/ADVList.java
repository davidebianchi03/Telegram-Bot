/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Davide classe che gestisce la lista delle pubblicità (si tratta di un
 * singleton)
 */
public class ADVList {

    private static ADVList instance = null;
    private List<ADV> advList = null;
    private final String fileName = "list.csv";//nome del file dove è salvata la lista delle pubblicità

    private ADVList() {
        advList = new LinkedList<>();
        //controllo se esiste il file e in caso carico la lista
        File file = new File(fileName);
        if (file.exists()) {
            try {
                //carico la lista
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line == "") {
                        break;
                    }

                    advList.add(ADV.LoadFromCSV(line));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ADVList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ADVList GetInstance() {
        if (instance == null) {
            synchronized (ADVList.class) {
                if (instance == null) {
                    instance = new ADVList();
                }
            }
        }
        return instance;
    }

    //metodo per aggiungere una pubblicità alla lista
    public synchronized void AddAdv(ADV adv) {
        //aggiungo la pubblicità nella lista
        advList.add(adv);

        //salvo la pubblicità nel file
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
            out.println(adv.ToCsv());
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
