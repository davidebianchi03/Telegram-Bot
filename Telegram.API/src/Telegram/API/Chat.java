package Telegram.API;

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
    private ChatPhoto chatPhoto;
    private String bio;
    private boolean has_private_forwards;
    private String description;
    private String invite_link;
    private Message pinned_message;
    
}
