package java12.service.serviceImpl;

import java12.dao.UserDao;
import java12.dao.impl.UserDaoImpl;
import java12.model.User;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();
    @Override
    public void creatUsertable() {
        userDao.creatUsertable();
    }

    @Override
    public void save(User newUser) {
        userDao.save(newUser);
    }

    @Override
    public Optional<User> findbyId(Long id) {
        return userDao.findbyId(id);
    }

    @Override
    public String updateSrudentbyId(User user, long id) {
       return userDao.updateStudentById(user,id);
    }

    @Override
    public boolean deletebyId(Long id) {
        return userDao.deletebyId(id);
    }

    @Override
    public String cahangeRoleByUsernameAndPassword(String username, String role, String password) {
return userDao.changeRoleByUsernameAndPassword(username,role,password);
    }

    @Override
    public void getUserRole(String role) {
        userDao.getUserRole(role);

    }
}
