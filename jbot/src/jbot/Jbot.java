package jbot;

import Telegram.API.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class Jbot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Telegram telegram = Telegram.Init("");
        List<Message> updatesList = null;
        while (true) {
            updatesList = telegram.GetUpdates();
            for (int i = 0; i < updatesList.size(); i++) {
                Message currentMessage = updatesList.get(i);
                String text = currentMessage.getText();
                User sender = currentMessage.getFrom();
                System.out.println("Sender: " + sender.getFirst_name() + " " + sender.getLast_name() + " - Message: " + text);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Jbot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
