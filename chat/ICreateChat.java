package chat;

public interface ICreateChat {
    default Chat IcreateChat(String name){
        return new Chat(name);
    }
}

