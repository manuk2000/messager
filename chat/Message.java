package chat;

import user.User;

public class Message {
    private User sender;
    private String context;

     Message(User sender , String context){
        this.sender = sender;
        this.context = context;
    }

    public User getSender() {
        return sender;
    }
    public String getContext() {
        return context;
    }

}
