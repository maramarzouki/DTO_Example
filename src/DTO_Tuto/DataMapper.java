package DTO_Tuto;

import java.sql.SQLException;
import java.util.List;

public interface DataMapper {
    UserDTO findById(int id) throws SQLException;

    List<UserDTO> findAll() throws SQLException;

    void save(UserDTO userDTO) throws SQLException;

    void update(UserDTO userDTO) throws SQLException;

    void delete(int id) throws SQLException;
    
    
//    User findById(int id) throws SQLException;
//
//    List<User> findAll() throws SQLException;
//
//    void save(User user) throws SQLException;
//
//    void update(User user) throws SQLException;
//
//    void delete(int id) throws SQLException;
}
