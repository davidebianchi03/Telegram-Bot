package Telegram.API;

/**
 *
 * @author Davide
 * Classe principale che gestisce la comu
 */
public class Telegram {
    
    private static Telegram instance = null;
    private String apiKey; 
    
    private Telegram(String apiKey){
        this.apiKey = apiKey;
    }
    
    //metodo da usare per ottenere l'instanza
    public static Telegram Init(String apiKey){
        
        if(instance == null){
            synchronized (Telegram.class) {
                if(instance == null){
                    instance = new Telegram(apiKey);
                }
            }
        }
        return instance;
    }
    
    //
    
}
