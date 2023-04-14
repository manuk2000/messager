package controler;

import java.util.Scanner;

import chat.Chat;
import platforms.Platform;
import platforms.Telegram;
import user.User;

public class Main extends Telegram {
    private static Scanner strInput = new Scanner(System.in);
    private static Scanner numberInput = new Scanner(System.in);

    public static void main(String[] args) {
        Platform platform = new Telegram();
        Telegram telegram = (Telegram) platform;

        int command;
        Program: while (true) {
            User self = null;
            System.out.println("Please select one index of the options");
            System.out.println("1 -> Sing in");
            System.out.println("2 -> Sing up");
            System.out.println("3 -> exit of System");

            command = Integer.parseInt(strInput.next());
            if (command == 1) {
                System.out.println("Please input Phone");
                String phone = strInput.nextLine();
                self = telegram.singIn(phone);
                if (self == null) {
                    System.out.println(" Phone not found ");
                    continue;
                }
            } else if (command == 2) {// sing up
                System.out.println("Please input Name");
                String name = strInput.nextLine();
                System.out.println("Please input Phone");
                String phone = strInput.nextLine();
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

            GenerelMenu: while (true) {
                System.out.println("Please select one index of the options");
                System.out.println("1 -> Show all chats");
                System.out.println("2 -> Create chat");
                System.out.println("3 -> Using user to chat");
                System.out.println("4 -> Remove chat");
                System.out.println("5 -> Delete self user");
                command = Integer.parseInt(strInput.next());

                switch (command) {
                    case 1:
                        while (true) {
                            telegram.showAllNameChat(self);
                            System.out.println("Please select one name of the Chat or 'break' from generel menu");
                            String nameChat = strInput.nextLine();
                            if (nameChat.equals("break")) {
                                continue GenerelMenu;
                            }
                            telegram.showHistoryOfChat(self, nameChat);

                            addOrDeleteMessage(telegram, self, nameChat);
                        }

                    case 2:
                        System.out.println("Please select one name new chat");
                        String nameChat = strInput.nextLine();
                        for (Chat chat : self.getOwnChats()) {
                            if (chat.getName().equals(nameChat)) {
                                System.out.println(nameChat + "by name aleady exists");
                                continue;
                            }
                        }
                        telegram.createChat(self, nameChat);
                        continue GenerelMenu;
                    case 3:
                        System.out.println("Please input name user");
                        String nameUser = strInput.nextLine();
                        User tmpUser = telegram.searchUser(nameUser);
                        if (tmpUser == null) {
                            System.out.println("Not name  " + nameUser);
                            continue;
                        }

                        System.out.println("Please input name chat");
                        String chatname = strInput.nextLine();
                        Chat tmpChat = telegram.searchChat(chatname);
                        if (tmpChat == null) {
                            System.out.println("Not chat  " + chatname);
                            continue;
                        }
                        telegram.addUserToChat(nameUser, chatname);
                        continue GenerelMenu;
                    case 4:
                        System.out.println("Please input name chat");
                        String tmpChatName = strInput.nextLine();
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

    private static void addOrDeleteMessage(Telegram telegram, User self, String chatName) {
        Chat tmp = null;
        for (Chat chat : self.getOwnChats()) {
            if (chat.getName().equals(chatName)) {
                tmp = chat;
            }
        }
        int command;
        while (true) {
            System.out.println();
            System.out.println("Please select one index of the options");
            System.out.println("1 -> write new message");
            System.out.println("2 -> delete message : imput index of message");
            System.out.println("3 -> breake of chat");
            command = Integer.parseInt(strInput.next());
            if (command == 1) {
                System.out.println("Please imput text");
                String context = strInput.nextLine();
                telegram.sendMessage(self, tmp, context);
                continue;
            } else if (command == 2) {
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
