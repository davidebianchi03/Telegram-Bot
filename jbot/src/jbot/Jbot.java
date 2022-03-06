package jbot;


/**
 *
 * @author Davide
 */
public class Jbot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ADVList list = ADVList.GetInstance();        
        //creo il thread che ascolta i messaggi in arrivo e li elabora
        BotUpdate bot = new BotUpdate("5190973445:AAG3B58T4vgxFXAKTegU_rR8eiQuyZnlrJk");
        bot.start();
        
        //creo e visualizzo l'interfaccia grafica da cui aggiungere i negozi
        GUI gui = new GUI();

    }

}
