package TelegramAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Davide Class used for making requests
 */
public class Telegram {

    private static Telegram instance = null;
    private String apiKey;
    private int lastUpdateId;
    private String basePath;

    private Telegram(String apiKey) {
        this.apiKey = apiKey;
        lastUpdateId = 0;
        basePath = "https://api.telegram.org/bot" + apiKey;
    }

    //method for getting the instance
    public static Telegram Init(String apiKey) {

        if (instance == null) {
            synchronized (Telegram.class) {
                if (instance == null) {
                    instance = new Telegram(apiKey);
                }
            }
        }
        return instance;
    }

    //method for getting the list of new messages
    public List<Message> GetUpdates() {
        List<Message> messages = new LinkedList<>();

        //load the list of available messages
        String urlPath = basePath + "/getUpdates?offset=" + Integer.toString(lastUpdateId + 1);
        try {
            URL url = new URL(urlPath);
            Scanner urlScanner = new Scanner(url.openStream());
            urlScanner.useDelimiter("/u000a");
            String response = urlScanner.next();

            //messages list parsing
            JSONObject jsonObj = new JSONObject(response);
            if (jsonObj.has("ok")) {
                if (jsonObj.getBoolean("ok")) {
                    if (jsonObj.has("result")) {
                        JSONArray resultsArray = jsonObj.getJSONArray("result");

                        for (int i = 0; i < resultsArray.length(); i++) {
                            JSONObject resultObj = resultsArray.getJSONObject(i);
                            //load message
                            if (resultObj.has("message")) {
                                messages.add(Message.LoadFromJson(resultObj.getJSONObject("message")));
                            }
                            //load update_id
                            if (resultObj.has("update_id")) {
                                lastUpdateId = resultObj.getInt("update_id");
                            }
                        }
                        return messages;
                    }
                }
            }
            return null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    //metodo utilizzato per inviare i messaggi
    public void SendMessage(int chat_id, String text) {
        String urlString = basePath + "/sendMessage?chat_id=" + Integer.toString(chat_id) + "&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8) + "&parse_mode=html";
        URL url;
        try {
            url = new URL(urlString);
            Scanner scanner = new Scanner(url.openStream());
            scanner.useDelimiter("/u000a");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //metodo utilizzato per inviare una posizione sulla mappa
    public void SendLocation(long chat_id, double latitude, double longitude){
        //https://api.telegram.org/bot[botToken]/sendlocation?chat_id=[UserID]&latitude=51.6680&longitude=32.6546
        String urlString = basePath + "/sendlocation?chat_id=" + Long.toString(chat_id) + "&latitude=" + Double.toString(latitude) + "&longitude=" + Double.toString(longitude);
        URL url;
        try {
            url = new URL(urlString);
            Scanner scanner = new Scanner(url.openStream());
            scanner.useDelimiter("/u000a");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
