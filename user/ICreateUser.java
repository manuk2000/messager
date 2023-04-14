package user;

public interface ICreateUser {
    default User createUser(String name , String phone){
        return new User(name , phone);
    }
}
