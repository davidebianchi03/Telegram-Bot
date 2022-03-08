/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TelegramAPI;

import org.json.JSONObject;

/**
 *
 * @author Davide
 * "entities": [
                    {
                        "offset": 0,
                        "length": 5,
                        "type": "bot_command"
                    }
                ]
 */
public class Entity {
    
    private int offset;
    private int length;
    private String type;
    
    public Entity(){
        offset = 0;
        length = 0;
        type = "";
    }

    public static Entity LoadFromJson(JSONObject jsonObj){
        Entity e = new Entity();
        
        if(jsonObj.has("offset")){
            e.offset = jsonObj.getInt("offset");
        }
        if(jsonObj.has("length")){
            e.length = jsonObj.getInt("length");
        }
        if(jsonObj.has("type")){
            e.type = jsonObj.getString("type");
        }
        
        return e;
    }
    
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
   
    
}
