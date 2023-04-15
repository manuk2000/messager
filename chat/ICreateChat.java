/**
 * The ICreateChat interface provides a method for creating new chat rooms.
 */
package chat;

/**
 * The ICreateChat interface provides a method for creating new chat rooms.
 */
public interface ICreateChat {

    /**
     * Creates a new Chat object with the given name.
     * 
     * @param name the name of the chat room
     * @return a new Chat object with the given name
     */
    default Chat IcreateChat(String name) {
        return new Chat(name);
    }
}
