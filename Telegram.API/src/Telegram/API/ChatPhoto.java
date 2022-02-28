package Telegram.API;
import org.json.*;
/**
 *
 * @author Davide Class for saving informations about chat photos
 * https://core.telegram.org/bots/api#chatphoto
 */
public class ChatPhoto {

    private String small_file_id;
    private String small_file_unique_id;
    private String big_file_id;
    private String big_file_unique_id;

    public ChatPhoto() {
        small_file_id = "";
        small_file_unique_id = "";
        big_file_id = "";
        big_file_unique_id = "";
    }

    public ChatPhoto(String small_file_id, String small_file_unique_id, String big_file_id, String big_file_unique_id) {
        this.small_file_id = small_file_id;
        this.small_file_unique_id = small_file_unique_id;
        this.big_file_id = big_file_id;
        this.big_file_unique_id = big_file_unique_id;
    }
    
    //method for loading chat photo from JSONObject
    public static ChatPhoto LoadFromJson(JSONObject jsonObj){
        ChatPhoto photo = new ChatPhoto();
        
        if(jsonObj.has("small_file_id")){
            photo.small_file_id = jsonObj.getString("small_file_id");
        }
        
        if(jsonObj.has("small_file_unique_id")){
            photo.small_file_unique_id = jsonObj.getString("small_file_unique_id");
        }
        
        if(jsonObj.has("big_file_id")){
            photo.big_file_id = jsonObj.getString("big_file_id");
        }
        
        if(jsonObj.has("big_file_unique_id")){
            photo.big_file_unique_id = jsonObj.getString("big_file_unique_id");
        }
        
        return photo;
    }

    //get & set methodss

    public String getSmall_file_id() {
        return small_file_id;
    }

    public void setSmall_file_id(String small_file_id) {
        this.small_file_id = small_file_id;
    }

    public String getSmall_file_unique_id() {
        return small_file_unique_id;
    }

    public void setSmall_file_unique_id(String small_file_unique_id) {
        this.small_file_unique_id = small_file_unique_id;
    }

    public String getBig_file_id() {
        return big_file_id;
    }

    public void setBig_file_id(String big_file_id) {
        this.big_file_id = big_file_id;
    }

    public String getBig_file_unique_id() {
        return big_file_unique_id;
    }

    public void setBig_file_unique_id(String big_file_unique_id) {
        this.big_file_unique_id = big_file_unique_id;
    }
    
    
    
}
