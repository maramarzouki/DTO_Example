package DTO_Tuto;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataMapperImpl implements DataMapper{
    private Connection connection;

    public DataMapperImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDTO findById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String state = resultSet.getString("state");
                    String zipCode = resultSet.getString("zip_code");

                    return new UserDTO(userId, username, email, state, zipCode);
                }
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        List<UserDTO> userDTOList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String state = resultSet.getString("state");
                String zipCode = resultSet.getString("zip_code");

                userDTOList.add(new UserDTO(userId, username, email, state, zipCode));
            }
        }
        return userDTOList;
    }

    @Override
    public void save(UserDTO userDTO) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, email, street, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userDTO.getUsername());
            statement.setString(2, userDTO.getEmail());
            statement.setString(5, userDTO.getState());
            statement.setString(6, userDTO.getZipCode());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userDTO.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(UserDTO userDTO) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, email = ?, street = ?, city = ?, state = ?, zip_code = ? WHERE id = ?")) {
            statement.setString(1, userDTO.getUsername());
            statement.setString(2, userDTO.getEmail());
            statement.setString(5, userDTO.getState());
            statement.setString(6, userDTO.getZipCode());
            statement.setInt(7, userDTO.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
