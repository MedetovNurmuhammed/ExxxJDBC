package java12.dao.impl;

import java12.configuration.JDBCconfig;
import java12.dao.UserDao;
import java12.model.User;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao,AutoCloseable {
    private final Connection connection = JDBCconfig.getconnection();


    @Override
    public void creatUsertable() {
        try {

            Statement statement =  connection.createStatement();
            statement.executeUpdate("""
            create  table if not exists users(id serial primary key,
            username varchar,
            password varchar,
            roles  varchar);
            """);
            System.out.println("Успешно создан!");
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());}
    }

    @Override
    public void save(User newUser) {
        String query = """
        insert into users(username, password, role)
        values(?, ?, ?)
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());


        }

        System.out.println("Успешно создан!");
    }

    @Override
    public Optional<User> findbyId(Long id) {
        User user = new User();
        String query  = "select * from users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);//анткени лонг тибиндеги id келет
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw  new RuntimeException("User wih id nohtfound");
            }
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));

            resultSet.close();
            connection.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(user);
    }

    @Override
    public String updateStudentById(User user, long id) {
        String query = "UPDATE users " +
                "SET username = ?, password = ?, role = ? " +
                "WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Failed";
        }

        return "Success";
    }
    @Override
    public boolean deletebyId(Long id) {
        String query = "delete from users where id  =? ";
        try( PreparedStatement preparedStatement  = connection.prepareStatement(query)) {

            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String changeRoleByUsernameAndPassword(String username, String role, String password) {
        String query = "UPDATE user " +
                "SET role = ? " +
                "WHERE username = ? AND password = ?";
        int execute = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

            execute = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return execute != 0 ? "Update" : "Failed";
    }

    @Override
    public Optional<User> getUserRole(String role) {
        User user = null;
        String query = "SELECT * FROM users WHERE role = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                // If no user with the specified role is found, return an empty Optional
                return Optional.empty();
            }

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return Optional.ofNullable(user);
    }
        @Override
    public void close() throws Exception {
        connection.close();
    }
}
