package java12.dao;

import java12.model.User;

import java.util.Optional;

public interface UserDao {
    void creatUsertable();
    void save (User newUser);
    Optional<User> findbyId(Long id);
    String updateStudentById(User user,long id);
    boolean deletebyId(Long id);
    String changeRoleByUsernameAndPassword(String username, String role, String password );
    Optional<User>getUserRole(String role);
}
