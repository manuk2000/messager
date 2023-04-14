package user;

import java.util.ArrayList;
import java.util.List;

import chat.Chat;

public class User {
    private String name;
    private final String phone;
    private List<Chat> ownChats = new ArrayList<Chat>();

     User(String name , String phone){
        this.name = name;
        this.phone = phone;
    }

    public List<Chat> getOwnChats() {
        return ownChats;
    }

    public void addChat(Chat chat) {
        ownChats.add( chat);
    }
    public void removeChat(Chat chat) {
        ownChats.remove( chat);
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
