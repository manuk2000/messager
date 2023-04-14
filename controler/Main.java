package controler;

import chat.Chat;
import java.util.Scanner;
import platforms.Platform;
import platforms.Telegram;
import user.User;

public class Main extends Telegram {

  private static Scanner strInput = new Scanner(System.in);
  private static Scanner numInput = new Scanner(System.in);

  public static void main(String[] args) {
    Platform platform = new Telegram();
    Telegram telegram = (Telegram) platform;

    int command;
    Program:while (true) {
      User self = null;
      System.out.println("Please select one index of the options");
      System.out.println("1 -> Sing in");
      System.out.println("2 -> Sing up");
      System.out.println("3 -> exit of System");

      command = Integer.parseInt(numInput.next());
      strInput.reset();
      if (command == 1) {
        System.out.println("Please input Phone");
        String phone = strInput.nextLine();
        strInput.reset();
        self = telegram.singIn(phone);
        if (self == null) {
          System.out.println(" Phone not found ");
          continue;
        }
      } else if (command == 2) { // sing up
        System.out.println("Please input Name");
        String name = strInput.nextLine();
        strInput.reset();
        System.out.println("Please input Phone");
        String phone = strInput.nextLine();
        strInput.reset();
        self = telegram.singUp(name, phone);
        if (self == null) {
          System.out.println("Invalid Name or Phone");
          continue;
        }
      } else if (command == 3) {
        break;
      } else if (!(command == 1 || command == 2 || command == 3)) {
        System.out.println("Invalid command");
        continue;
      }

      GenerelMenu:while (true) {
        System.out.println("Please select one index of the options");
        System.out.println("1 -> Show all chats");
        System.out.println("2 -> Create chat");
        System.out.println("3 -> Add user to chat");
        System.out.println("4 -> Remove chat");
        System.out.println("5 -> Delete self user");
        command = Integer.parseInt(numInput.next());
        strInput.reset();

        switch (command) {
          case 1:
            while (true) {
                System.out.println("All chats");
              telegram.showAllNameChat(self);
              System.out.println(
                "Please select one name of the Chat or 'break' from generel menu"
              );
              String nameChat = strInput.nextLine();
              strInput.reset();
              if (nameChat.equals("break")) {
                continue GenerelMenu;
              }
              telegram.showHistoryOfChat(self, nameChat);

              addOrDeleteMessage(telegram, self, nameChat);
            }
          case 2:
            System.out.println("Please input name new chat");
            String nameChat = strInput.nextLine();
            strInput.reset();
            for (Chat chat : self.getOwnChats()) {
              if (chat.getName().equals(nameChat)) {
                System.out.println(nameChat + "by name aleady exists");
                continue;
              }
            }
            telegram.createChat(self, nameChat);
            continue GenerelMenu;
          case 3:
            System.out.println("Please input phone user");
            String phoneUser = strInput.nextLine();
            strInput.reset();
            User tmpUser = telegram.searchUser(phoneUser);
            if (tmpUser == null) {
              System.out.println("Not name  " + phoneUser);
              continue;
            }

            System.out.println("Please input name chat");
            String chatname = strInput.nextLine();
            strInput.reset();
            Chat tmpChat = telegram.searchChat(chatname);
            if (tmpChat == null) {
              System.out.println("Not chat  " + chatname);
              continue;
            }
            telegram.addUserToChat(phoneUser, chatname);
            continue GenerelMenu;
          case 4:
            System.out.println("Please input name chat");
            String tmpChatName = strInput.nextLine();
            strInput.reset();
            Chat tmpChat4 = telegram.searchChat(tmpChatName);
            if (tmpChat4 == null) {
              System.out.println("Not chat  " + tmpChatName);
              continue;
            }
            telegram.removeChat(self, tmpChatName);
            continue GenerelMenu;
          case 5:
            telegram.deleteMySelf(self);
            break Program;
          default:
            break;
        }
      }
    }
  }

  private static void addOrDeleteMessage(
    Telegram telegram,
    User self,
    String chatName
  ) {
    Chat tmp = null;
    for (Chat chat : self.getOwnChats()) {
      if (chat.getName().equals(chatName)) {
        tmp = chat;
      }
    }
    int command;
    while (true) {
      System.out.println("Get History");
      telegram.showHistoryOfChat(self, chatName);
      System.out.println("Please select one index of the options");
      System.out.println("1 -> write new message");
      System.out.println("2 -> delete message ");
      System.out.println("3 -> breake of chat");
      command = Integer.parseInt(numInput.next());
      strInput.reset();
      if (command == 1) {
        System.out.println("Please imput text");
        String context = strInput.nextLine();
        strInput.reset();
        telegram.sendMessage(self, tmp, context);
        continue;
      } else if (command == 2) {
        System.out.println(": imput index of message");
        command = Integer.parseInt(numInput.next());
        strInput.reset();
        telegram.removeMessage(self, tmp, command);
        continue;
      } else if (command == 3) {
        return;
      } else {
        System.out.println("Invalid command");
        continue;
      }
    }
  }
}
