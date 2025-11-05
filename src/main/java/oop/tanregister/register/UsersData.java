package oop.tanregister.register;

import java.util.ArrayList;
import java.util.List;

public class UsersData {
    private static final List<Users> userList = new ArrayList<>();

    public static void addUser(Users user) {
        userList.add(user);
    }

    public static List<Users> getUsers() {
        return userList;
    }
}
