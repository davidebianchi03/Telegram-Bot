/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbot;

import GeoUtilis.Coordinate;
import GeoUtilis.Distance;
import GeoUtilis.Geocoding;
import GeoUtilis.Place;
import Telegram.API.Message;
import Telegram.API.Telegram;
import Telegram.API.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Davide
 * Classe utilizzata per ottenere gli aggiornamenti della lista dei messaggi
 */
public class BotUpdate extends Thread {
    
    private String apiKey;
    
    public BotUpdate(String apiKey){
        this.apiKey = apiKey;
    }

    @Override
    public void run() {
        Telegram telegram = Telegram.Init(apiKey);
        List<Message> updatesList = null;
        while (true) {
            updatesList = telegram.GetUpdates();
            if (updatesList != null) {
                for (int i = 0; i < updatesList.size(); i++) {
                    Message currentMessage = updatesList.get(i);
                    String text = currentMessage.getText();
                    User sender = currentMessage.getFrom();
                    
                    if (currentMessage.getEntities() != null && currentMessage.getEntities().get(0).getType().equals("bot_command")) {
                        //se si tratta di un comando per il bot
                        //se il comando è quello per ottere la pubblicità
                        String command = text.substring(currentMessage.getEntities().get(0).getOffset(), currentMessage.getEntities().get(0).getLength());
  
                        if (command.equals("/citta")) {
                            String msgArgs = text.substring(currentMessage.getEntities().get(0).getOffset() + currentMessage.getEntities().get(0).getLength()).trim();

                            try {
                                List<Place> placeList = Geocoding.SearchPlace(msgArgs);

                                String listString = "";
                                
                                if(placeList.size() <= 0){
                                    telegram.SendMessage(currentMessage.getChat().getId(), "<i>No place found with the name: <b>" + msgArgs +"</b></i>");
                                }
                                else{
                                    //rispondo con la lista delle pubblicità in zona
                                    telegram.SendMessage(currentMessage.getChat().getId(), "Ok, ti sei iscritto a questa lista"); 
                                    //salvo l'utente sul file
                                }

                                
                            } catch (ParserConfigurationException ex) {
                                Logger.getLogger(Jbot.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SAXException ex) {
                                Logger.getLogger(Jbot.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Jbot.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jbot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
}
