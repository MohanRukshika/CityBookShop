package Services;

import model.*;

import java.util.List;

public class UserService {

    private static final String FILE_NAME = "users.txt";

    public void registerUser(User user) {
        String data = user.getUsername() + "," + user.getPassword() + "," + user.getRole();
        FileHandler.writeLine(FILE_NAME, data);
    }

    public User login(String username, String password) {
        List<String> users = FileHandler.readAllLines(FILE_NAME);

        for (String line : users) {
            String[] parts = line.split(",");

            if (parts[0].equals(username) && parts[1].equals(password)) {

                if (parts[2].equals("Manager")) {
                    return new Manager(username, password);
                } else {
                    return new Cashier(username, password);
                }
            }
        }

        return null;
    }

}
