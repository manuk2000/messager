package chat;

import java.util.ArrayList;
import java.util.List;
import user.User;

/**
 * Represents a chat object that can be used for messaging between users.
 */
public class Chat {

  private String name;
  private List<User> usersOfChat = new ArrayList<User>();
  private List<Message> history = new ArrayList<Message>();

  /**
   * Creates a new Chat object with the specified name.
   *
   * @param name the name of the chat
   */
  public Chat(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the chat.
   *
   * @return the name of the chat
   */
  public String getName() {
    return name;
  }

  /**
   * Returns a list of users in the chat.
   *
   * @return a list of users in the chat
   */
  public List<User> getusersOfChat() {
    return usersOfChat;
  }

  /**
   * Sets the name of the chat.
   *
   * @param name the new name of the chat
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns a list of messages in the chat.
   *
   * @return a list of messages in the chat
   */
  public List<Message> getHistory() {
    return history;
  }

  /**
   * Adds a user to the chat.
   *
   * @param user the user to add to the chat
   */
  public void addUser(User user) {
    usersOfChat.add(user);
  }

  /**
   * Removes a user from the chat.
   *
   * @param user the user to remove from the chat
   */
  public void removeUser(User user) {
    usersOfChat.remove(user);
  }

  /**
   * Adds a message to the chat.
   *
   * @param sender  the user who sent the message
   * @param context the text of the message
   */
  public void addMessage(User sender, String context) {
    history.add(new Message(sender, context));
  }

  /**
   * Removes a message from the chat.
   *
   * @param message the message to remove from the chat
   */
  public void removeMessage(Message message) {
    history.remove(message);
  }
}
