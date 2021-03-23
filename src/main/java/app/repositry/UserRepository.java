package app.repositry;

import app.model.User;

public class UserRepository {

    public User getUser() {
        User user = new User();
        user.setEmail("vozniukos@gmail.com");
        user.setPassword("1234");

        return user;
    }
}
