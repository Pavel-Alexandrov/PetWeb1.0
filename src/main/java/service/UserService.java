package service;

import DAO.UserDao;
import exception.DBException;
import exception.StatementException;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final Connection connection;

    public UserService() {
        this.connection = getMySQLConnection();
    }

    public List<User> getAllUsers() throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            List<User> userList = userDAO.getAllUsers();
            return userList;
        } catch (StatementException se) {
            throw new DBException(se);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если нет, то добавляет, иначе ничего не делает
    public void addUser(String name, String login, String password) throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            if (!userDAO.checkUser(login)) {
                userDAO.addUser(name, login, password);
            }
        } catch (StatementException se) {
            throw new DBException(se);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если есть, меняет данные, иначе ничего не делает
    public void updateUser(String name, String login, String password) throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            if (userDAO.checkUser(login)) {
                userDAO.updateUser(name, login, password);
            }
        } catch (StatementException se) {
            throw new DBException(se);
        }
    }

    public void deleteUser(Integer id) throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            userDAO.deleteUser(id);
        } catch (StatementException se) {
            throw new DBException(se);
        }
    }

    public void createTable() throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            userDAO.createTable();
        } catch (StatementException se) {
            throw new DBException(se);
        }
    }

    public void cleanUp() throws DBException {
        UserDao userDAO = getUserDAO();
        try {
            userDAO.dropTable();
        } catch (StatementException se) {
            throw new DBException(se);
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

    private UserDao getUserDAO() {
        return UserDao.getInstance(connection);
    }
}
