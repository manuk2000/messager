package chat;

import java.util.ArrayList;
import java.util.List;
import user.User;

/** ... */
public class Chat {

  private String name;
  private List<User> usersOfChat = new ArrayList<User>();
  private List<Message> history = new ArrayList<Message>();

  Chat(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<User> getusersOfChat() {
    return usersOfChat;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Message> getHistory() {
    return history;
  }

  public void addUser(User user) {
    usersOfChat.add(user);
  }

  public void removeUser(User user) {
    usersOfChat.remove(user);
  }

  public void addMessage(User sender, String context) {
    history.add(new Message(sender, context));
  }

  public void removeMessage(Message message) {
    history.remove(message);
  }
}
