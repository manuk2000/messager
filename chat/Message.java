/**
 * The Message class represents a message sent in a chat room by a user.
 */
package chat;

import user.User;

/**
 * The Message class represents a message sent in a chat room by a user.
 */
public class Message {

    private User sender;
    private String context;

    /**
     * Creates a new Message object with the given sender and context.
     * 
     * @param sender  the user who sent the message
     * @param context the content of the message
     */
    public Message(User sender, String context) {
        this.sender = sender;
        this.context = context;
    }

    /**
     * Returns the user who sent the message.
     * 
     * @return the user who sent the message
     */
    public User getSender() {
        return sender;
    }

    /**
     * Returns the content of the message.
     * 
     * @return the content of the message
     */
    public String getContext() {
        return context;
    }

}
