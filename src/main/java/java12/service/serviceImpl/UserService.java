package java12.service.serviceImpl;

import java12.model.User;

import java.util.Optional;

public interface UserService {
    void creatUsertable();

    void save (User newUser);
    Optional<User> findbyId(Long id);
    String updateSrudentbyId(User user,long id);
    boolean deletebyId(Long id);

    String cahangeRoleByUsernameAndPassword(String username, String role, String password);

    void getUserRole(String role);
}
