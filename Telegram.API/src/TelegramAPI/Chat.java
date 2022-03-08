package TelegramAPI;

import org.json.JSONObject;

/**
 *
 * @author Davide
 * Class for saving informations about the chat
 * https://core.telegram.org/bots/api#chat
 */
public class Chat {
    
    private int id;
    private String type;
    private String title;
    private String username;
    private String first_name;
    private String last_name;
    private ChatPhoto photo;
    private String bio;
    private boolean has_private_forwards;
    private String description;
    private String invite_link;
    
    public Chat(){
        id = 0;
        type = "";
        title = "";
        username = "";
        first_name = "";
        last_name = "";
        photo = null;
        bio = "";
        has_private_forwards = false;
        description = "";
        invite_link = "";
    }

    public Chat(int id, String type, String title, String username, String first_name, String last_name, ChatPhoto photo, String bio, boolean has_private_forwards, String description, String invite_link, Message pinned_message) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo = photo;
        this.bio = bio;
        this.has_private_forwards = has_private_forwards;
        this.description = description;
        this.invite_link = invite_link;
    }
    
    //method for loading object from org.json JSONObject
    public static Chat LoadFromJson(JSONObject jsonObj){
        Chat chat = new Chat();
        
        if(jsonObj.has("id")){
            chat.id = jsonObj.getInt("id");
        }
        if(jsonObj.has("type")){
            chat.type = jsonObj.getString("type");
        }
        if(jsonObj.has("title")){
            chat.title = jsonObj.getString("title");
        }
        if(jsonObj.has("username")){
            chat.username = jsonObj.getString("username");
        }
        if(jsonObj.has("first_name")){
            chat.first_name = jsonObj.getString("first_name");
        }
        if(jsonObj.has("last_name")){
            chat.last_name = jsonObj.getString("last_name");
        }
        if(jsonObj.has("photo")){
            chat.photo = ChatPhoto.LoadFromJson(jsonObj.getJSONObject("photo"));
        }
        if(jsonObj.has("bio")){
            chat.bio = jsonObj.getString("bio");
        }
        if(jsonObj.has("has_private_forwards")){
            chat.has_private_forwards = jsonObj.getBoolean("has_private_forwards");
        }
        if(jsonObj.has("description")){
            chat.description = jsonObj.getString("description");
        }
        if(jsonObj.has("invite_link")){
            chat.invite_link = jsonObj.getString("invite_link");
        }        
        
        return chat;
    }
    
    
    //get & set methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ChatPhoto photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isHas_private_forwards() {
        return has_private_forwards;
    }

    public void setHas_private_forwards(boolean has_private_forwards) {
        this.has_private_forwards = has_private_forwards;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvite_link() {
        return invite_link;
    }

    public void setInvite_link(String invite_link) {
        this.invite_link = invite_link;
    }
}
