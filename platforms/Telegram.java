package platforms;

import java.util.ArrayList;
import java.util.List;

import chat.Chat;
import chat.ICreateChat;
import chat.Message;
import user.ICreateUser;
import user.User;

public class Telegram implements Platform {
    private List<User> allUser = new ArrayList<>();
    private List<Chat> allChat = new ArrayList<>();

    public User searchUser(String phone) {
        for (User user : allUser) {
            if (user.getPhone().equals(phone))
                return user;
        }
        return null;
    }

    public Chat searchChat(String nameChat) {
        for (Chat chat : allChat) {
            if (chat.getName().equals(nameChat))
                return chat;
        }
        return null;
    }

    public User singUp(String name, String phone) {
        if (searchUser(phone) != null) {
            return null;
        }
        User tmp = new User(name, phone);
        allUser.add(tmp);
        return tmp;
    }

    public User singIn(String phone) {
        User tmp = searchUser(phone);
        if (searchUser(phone) == null) {
            return null;
        }
        return tmp;
    }

    public void deleteMySelf(User self) {
        allUser.remove(self);
    }

    public Chat createChat(User self, String nameChat) {
        if (searchChat(nameChat) != null) {
            return null;
        }
        Chat tmp = new Chat(nameChat);
        allChat.add(tmp);
        return tmp;
    }

    public void removeChat(User self, String nameChat) {
        Chat chat = searchChat(nameChat);
        if (chat == null) {
            System.out.println("Not chat " + nameChat);
            return;
        }
        allChat.remove(chat);
    }
    
    public boolean addUserToChat(String phone, String chatName) {
        User user = searchUser(phone);
        Chat chat = searchChat(chatName);
        if (user == null || chat == null) {
            return false;
        }

        if (chat.getusersOfChat().indexOf(user) != -1) {
            return false;
        }

        chat.addUser(user);
        return true;
    }

    public boolean deleteUserOfChat(String phone, String chatName) {
        User user = searchUser(phone);
        Chat chat = searchChat(chatName);
        if (user == null || chat == null) {
            return false;
        }

        if (chat.getusersOfChat().indexOf(user) == -1) {
            return false;
        }
        chat.removeUser(user);
        return true;

    }

    public boolean sendMessage(User self, Chat hostChat, String context) {
        if (hostChat.getusersOfChat().indexOf(self) == -1) {
            return false;
        }
        hostChat.addMessage(self, context);
        return true;
    }

    public boolean removeMessage(User self, Chat hostChat, int index) {
        if (hostChat.getusersOfChat().indexOf(self) == -1) {
            return false;
        }
        hostChat.removeMessage(hostChat.getHistory().get(index));
        return true;
    }

    public void showAllNameChat(User self) {
        self.getOwnChats().forEach((i) -> System.out.println(i.getName()));
    }

    public void showHistoryOfChat(User self, String chatName) {
        for (Chat chat : self.getOwnChats()) {
            if (chat.getName().equals(chatName)) {
                int index = 0;
                for (Message message : chat.getHistory()) {
                    System.out.println(index++ + message.getContext());
                }
            }
        }

    }
}
