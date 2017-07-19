package Model.UserModel;

import MyDataStructures.Implementations.List.ListOrdered;

/**
 * Created by Matt on 5/30/2017.
 *
 * UserDB class is basically the same as shown by
 * Professor Kanchanawanchai in the videos below:
 *      video 1:
 *          https://youtu.be/hZeB8NArL2k
 *      video 2:
 *          https://youtu.be/x9g-vXuHNkA
 */
public class UserDB {

    private static ListOrdered<User> users = new ListOrdered<>();

    public static ListOrdered<User> getUsersArrayList() {
        return users;
    }

    public static void setUsersArrayList(ListOrdered<User> users) {
        UserDB.users = users;
    }

    public static void printOrderedList() {
        users.reset();
        System.out.println(users.toString());
    }
}
