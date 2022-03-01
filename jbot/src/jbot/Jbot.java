package jbot;

import GeoUtilis.Coordinate;
import GeoUtilis.Geocoding;
import GeoUtilis.Place;
import Telegram.API.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Davide
 */
public class Jbot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Telegram telegram = Telegram.Init("5190973445:AAG3B58T4vgxFXAKTegU_rR8eiQuyZnlrJk");
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
                            String msgArgs = text.substring(currentMessage.getEntities().get(0).getLength(), text.length() - currentMessage.getEntities().get(0).getLength() - currentMessage.getEntities().get(0).getOffset());
                            try {
                                List<Place> placeList = Geocoding.SearchPlace(msgArgs);

                                String listString = "";

                                for (int j = 0; j < placeList.size(); j++) {
                                    Place p = placeList.get(j);
                                    String returnString = p.toString();
                                    
                                    if(returnString != "")
                                    telegram.SendMessage(currentMessage.getChat().getId(), returnString);
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
