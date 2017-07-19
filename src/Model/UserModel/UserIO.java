package Model.UserModel;

import java.io.*;

/**
 * Created by Matt on 5/30/2017.
 *
 * UserIO class is basically the same as shown by
 * Professor Kanchanawanchai in the videos below:
 *      video 1:
 *          https://youtu.be/hZeB8NArL2k
 *      video 2:
 *          https://youtu.be/x9g-vXuHNkA
 *
 */
public class UserIO {

    public static void writeUsers(Object data) throws IOException {

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("users.dat"));
        output.writeObject(data);
        output.flush();
        output.close();

    }

    public static Object readUsers() throws IOException, ClassNotFoundException {

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("users.dat"));
        return input.readObject();

    }

}
