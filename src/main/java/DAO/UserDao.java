package DAO;

import exception.StatementException;
import model.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    public static UserDao getInstance(Connection connection) {
        return new UserJDBCDAO(connection);
    }

    public List<User> getAllUsers() throws StatementException;

    public void addUser(String name, String login, String password) throws StatementException;

    public void updateUser(String name, String login, String password) throws StatementException;

    public void deleteUser(Integer id) throws StatementException;

    //проверяет наличие юзера в базе по логину
    public boolean checkUser(String login) throws StatementException;

    public void createTable() throws StatementException;

    public void dropTable() throws StatementException;
}
