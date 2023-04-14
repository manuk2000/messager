package chat;

public interface ICreateChat {
    default Chat createChat(String name){
        return new Chat(name);
    }
}
class Q implements ICreateChat{
    public static void main(String[] args) {
        ICreateChat o = new Q();
        Q oo = (Q) o;
        oo.foo();
    }
    void foo(){
        System.out.println("Q.foo()");
    }
}
