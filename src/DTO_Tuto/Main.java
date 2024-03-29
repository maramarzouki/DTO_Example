package DTO_Tuto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DataMapper userMapper = new DataMapperImpl(connection);

            // Creating a new user
            Address address = new Address("street", "city", "state", "code");
            User newUser = new User(0, "maram", "maram@gmail.com", address);
            UserDTO userDTO = UserDTOMapper.mapUserToDTO(newUser);
            userMapper.save(userDTO);

            // Finding a user by id
            if (userDTO != null) {
                System.out.println("Found user: " + userDTO.getUsername());

                // Updating user properties
                userDTO.setUsername("newUsername");
                userDTO.setEmail("newemail@gmail.com");
                userMapper.update(userDTO);
                System.out.println("User updated: " + userDTO.getUsername());

                // Deleting user
                userMapper.delete(userDTO.getId());
                System.out.println("User deleted.");
            } else {
                System.out.println("User not found.");
            }

            // Finding all users
            List<UserDTO> userList = userMapper.findAll();
            System.out.println("All users:");
            for (UserDTO u : userList) {
                System.out.println("Username: " + u.getUsername());
                System.out.println("Email: " + u.getEmail());
                System.out.println("State: " + u.getState());
                System.out.println("Zip Code: " + u.getZipCode());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
