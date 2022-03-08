package TelegramAPI;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Davide
 */
public class Message {

    private int message_id;
    private User from;
    private Chat sender_chat;
    private int date;
    private Chat chat;
    private Chat forward_from_chat;
    private int forward_from_message_id;
    private String forward_signature;
    private String forward_sender_name;
    private int forward_date;
    private boolean is_automatic_forward;
    private Message reply_to_message;
    private User via_bot;
    private int edit_date;
    private boolean has_protected_content;
    private String media_group_id;
    private String author_signature;
    private String text;
    private List<Entity> entities;

    public Message() {
        message_id = 0;
        from = null;
        sender_chat = null;
        date = 0;
        chat = null;
        forward_from_chat = null;
        forward_from_message_id = 0;
        forward_signature = "";
        forward_sender_name = "";
        forward_date = 0;
        is_automatic_forward = false;
        reply_to_message = null;
        via_bot = null;
        edit_date = 0;
        has_protected_content = false;
        media_group_id = "";
        author_signature = "";
        text = "";
        entities = null;
    }

    public Message(int message_id, User from, Chat sender_chat, int date, Chat chat, Chat forward_from_chat, int forward_from_message_id, String forward_signature, String forward_sender_name, int forward_date, boolean is_automatic_forward, Message reply_to_message, User via_bot, int edit_date, boolean has_protected_content, String media_group_id, String author_signature, String text, List<Entity> entities) {
        this.message_id = message_id;
        this.from = from;
        this.sender_chat = sender_chat;
        this.date = date;
        this.chat = chat;
        this.forward_from_chat = forward_from_chat;
        this.forward_from_message_id = forward_from_message_id;
        this.forward_signature = forward_signature;
        this.forward_sender_name = forward_sender_name;
        this.forward_date = forward_date;
        this.is_automatic_forward = is_automatic_forward;
        this.reply_to_message = reply_to_message;
        this.via_bot = via_bot;
        this.edit_date = edit_date;
        this.has_protected_content = has_protected_content;
        this.media_group_id = media_group_id;
        this.author_signature = author_signature;
        this.text = text;
        this.entities = entities;
    }

    public static Message LoadFromJson(JSONObject jsonObj) {
        Message message = new Message();

        if (jsonObj.has("message_id")) {
            message.message_id = jsonObj.getInt("message_id");
        }
        if (jsonObj.has("from")) {
            message.from = User.LoadFromJson(jsonObj.getJSONObject("from"));
        }
        if (jsonObj.has("sender_chat")) {
            message.sender_chat = Chat.LoadFromJson(jsonObj.getJSONObject("sender_chat"));
        }
        if (jsonObj.has("date")) {
            message.date = jsonObj.getInt("date");
        }
        if (jsonObj.has("chat")) {
            message.chat = Chat.LoadFromJson(jsonObj.getJSONObject("chat"));
        }
        if (jsonObj.has("forward_from_chat")) {
            message.forward_from_chat = Chat.LoadFromJson(jsonObj.getJSONObject("forward_from_chat"));
        }
        if (jsonObj.has("forward_from_message_id")) {
            message.forward_from_message_id = jsonObj.getInt("forward_from_message_id");
        }
        if (jsonObj.has("forward_signature")) {
            message.forward_signature = jsonObj.getString("forward_signature");
        }
        if (jsonObj.has("forward_sender_name")) {
            message.forward_sender_name = jsonObj.getString("forward_sender_name");
        }
        if (jsonObj.has("forward_date")) {
            message.forward_date = jsonObj.getInt("forward_date");
        }
        if (jsonObj.has("is_automatic_forward")) {
            message.is_automatic_forward = true;
        } else {
            message.is_automatic_forward = false;
        }
        if (jsonObj.has("reply_to_message")) {
            message.reply_to_message = Message.LoadFromJson(jsonObj.getJSONObject("reply_to_message"));
        }
        if (jsonObj.has("via_bot")) {
            message.via_bot = User.LoadFromJson(jsonObj.getJSONObject("via_bot"));
        }
        if (jsonObj.has("edit_date")) {
            message.edit_date = jsonObj.getInt("edit_date");
        }
        if (jsonObj.has("has_protected_content")) {
            message.has_protected_content = true;
        } else {
            message.has_protected_content = false;
        }
        if (jsonObj.has("media_group_id")) {
            message.media_group_id = jsonObj.getString("media_group_id");
        }
        if (jsonObj.has("author_signature")) {
            message.author_signature = jsonObj.getString("author_signature");
        }
        if (jsonObj.has("text")) {
            message.text = jsonObj.getString("text");
        }

        //carico le entities
        if (jsonObj.has("entities")) {
            JSONArray entitiesArray = new JSONArray(jsonObj.getJSONArray("entities"));
            message.entities = new ArrayList<Entity>();

            for (int i = 0; i < entitiesArray.length(); i++) {
                message.entities.add(Entity.LoadFromJson(entitiesArray.getJSONObject(i)));
            }

        }

        return message;
    }

    //get & set methods
    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Chat getSender_chat() {
        return sender_chat;
    }

    public void setSender_chat(Chat sender_chat) {
        this.sender_chat = sender_chat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Chat getForward_from_chat() {
        return forward_from_chat;
    }

    public void setForward_from_chat(Chat forward_from_chat) {
        this.forward_from_chat = forward_from_chat;
    }

    public int getForward_from_message_id() {
        return forward_from_message_id;
    }

    public void setForward_from_message_id(int forward_from_message_id) {
        this.forward_from_message_id = forward_from_message_id;
    }

    public String getForward_signature() {
        return forward_signature;
    }

    public void setForward_signature(String forward_signature) {
        this.forward_signature = forward_signature;
    }

    public String getForward_sender_name() {
        return forward_sender_name;
    }

    public void setForward_sender_name(String forward_sender_name) {
        this.forward_sender_name = forward_sender_name;
    }

    public int getForward_date() {
        return forward_date;
    }

    public void setForward_date(int forward_date) {
        this.forward_date = forward_date;
    }

    public boolean isIs_automatic_forward() {
        return is_automatic_forward;
    }

    public void setIs_automatic_forward(boolean is_automatic_forward) {
        this.is_automatic_forward = is_automatic_forward;
    }

    public Message getReply_to_message() {
        return reply_to_message;
    }

    public void setReply_to_message(Message reply_to_message) {
        this.reply_to_message = reply_to_message;
    }

    public User getVia_bot() {
        return via_bot;
    }

    public void setVia_bot(User via_bot) {
        this.via_bot = via_bot;
    }

    public int getEdit_date() {
        return edit_date;
    }

    public void setEdit_date(int edit_date) {
        this.edit_date = edit_date;
    }

    public boolean isHas_protected_content() {
        return has_protected_content;
    }

    public void setHas_protected_content(boolean has_protected_content) {
        this.has_protected_content = has_protected_content;
    }

    public String getMedia_group_id() {
        return media_group_id;
    }

    public void setMedia_group_id(String media_group_id) {
        this.media_group_id = media_group_id;
    }

    public String getAuthor_signature() {
        return author_signature;
    }

    public void setAuthor_signature(String author_signature) {
        this.author_signature = author_signature;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    

}
