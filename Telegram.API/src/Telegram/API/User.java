package Telegram.API;
import org.json.*;
/**
 *
 * @author Davide
 * Class for saving users informations
 * https://core.telegram.org/bots/api#user
 */
public class User {
    
    private int id;
    private boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
    private boolean can_join_groups;
    private boolean can_read_all_group_messages;
    private boolean supports_inline_queries;

    public User() {
        id = 0;
        is_bot = false;
        first_name = "";
        last_name = "";
        username = "";
        language_code = "";
        can_join_groups = false;
        can_read_all_group_messages = false;
        supports_inline_queries = false;   
    }

    public User(int id, boolean is_bot, String first_name, String last_name, String username, String language_code, boolean can_join_groups, boolean can_read_all_group_messages, boolean supports_inline_queries) {
        this.id = id;
        this.is_bot = is_bot;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.language_code = language_code;
        this.can_join_groups = can_join_groups;
        this.can_read_all_group_messages = can_read_all_group_messages;
        this.supports_inline_queries = supports_inline_queries;
    }
    
    //method for loading the user from json object
    public static User LoadFromJson(JSONObject jsonObj){
        User user = new User();
        //load the list of attributes
        if(jsonObj.has("id")){
            user.setId(jsonObj.getInt("id"));
        }
        
        if(jsonObj.has("is_bot")){
            user.setIs_bot(jsonObj.getBoolean("is_bot"));
        }
        
        if(jsonObj.has("first_name")){
            user.setFirst_name(jsonObj.getString("first_name"));
        }
        
        if(jsonObj.has("last_name")){
            user.setLast_name(jsonObj.getString("last_name"));
        }
        
        if(jsonObj.has("username")){
            user.setUsername(jsonObj.getString("username"));
        }
        
        if(jsonObj.has("language_code")){
            user.setLanguage_code(jsonObj.getString("language_code"));
        }
        
        if(jsonObj.has("can_join_groups")){
            user.setCan_join_groups(jsonObj.getBoolean("can_join_groups"));
        }
        
        if(jsonObj.has("can_read_all_group_messages")){
            user.setCan_read_all_group_messages(jsonObj.getBoolean("can_read_all_group_messages"));
        }
        
        if(jsonObj.has("supports_inline_queries")){
            user.setSupports_inline_queries(jsonObj.getBoolean("supports_inline_queries"));
        }
        
        return user;
    }
    
    //get & sets methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_bot() {
        return is_bot;
    }

    public void setIs_bot(boolean is_bot) {
        this.is_bot = is_bot;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public boolean isCan_join_groups() {
        return can_join_groups;
    }

    public void setCan_join_groups(boolean can_join_groups) {
        this.can_join_groups = can_join_groups;
    }

    public boolean isCan_read_all_group_messages() {
        return can_read_all_group_messages;
    }

    public void setCan_read_all_group_messages(boolean can_read_all_group_messages) {
        this.can_read_all_group_messages = can_read_all_group_messages;
    }

    public boolean isSupports_inline_queries() {
        return supports_inline_queries;
    }

    public void setSupports_inline_queries(boolean supports_inline_queries) {
        this.supports_inline_queries = supports_inline_queries;
    }
    
}
