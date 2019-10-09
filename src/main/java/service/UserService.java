package service;

import DAO.UserDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Connection connection;

    public UserService() {
        this.connection = getMySQLConnection();
    }

    //формирует List<List<Object>> где List<Object> содержит все данные юзера, по порядку
    //сделано, чтобы jstl мог итерироваться по внутреннему и внешнему листам, создавая таблицу
    public List<User> getAllUsers() throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            List<User> userList = userDAO.getAllUsers();
            return userList;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если нет, то добавляет, иначе ничего не делает
    public void addUser(String name, String login, String password) throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            if (!userDAO.checkUser(login)) {
                userDAO.addUser(name, login, password);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если есть, меняет данные, иначе ничего не делает
    public void updateUser(String name, String login, String password) throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            if (userDAO.checkUser(login)) {
                userDAO.updateUser(name, login, password);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(Integer id) throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void cleanUp() throws DBException {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=root&serverTimezone=UTC");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private UserDAO getUserDAO() {
        return new UserDAO(connection);
    }
}
