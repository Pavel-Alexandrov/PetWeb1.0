package DAO;

import executor.Executor;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Executor executor;
    private Connection connection;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM Users";
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        List<User> userList = new ArrayList<>();
        while(resultSet.next()) {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
            userList.add(user);
        }
        resultSet.close();
        statement.close();
        return userList;
        //лямбды недоступны
        /*
        return executor.execQuery(query, resultSet -> {
            List<User> userList = new ArrayList<>();
            while(!resultSet.isLast()) {
                resultSet.next();
                User user = new User(resultSet.getString("name"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password"));
                userList.add(user);
            }*/
    }

    public void addUser(String name, String login, String password) throws SQLException {
        String update = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateUser(String name, String login, String password) throws SQLException {
        String update = "UPDATE users SET name = ?, login = ?, password = ? WHERE login LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, login);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(Integer id) throws SQLException {
        String update = "DELETE FROM Users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    //проверяет наличие юзера в базе по логину
    public boolean checkUser(String login) throws SQLException {
        String query = "SELECT * FROM Users WHERE login  = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        //заменить
        return resultSet.next();
    }

    public void createTable() throws SQLException {
        String update = "CREATE TABLE if NOT EXISTS Users (id int auto_increment, name varchar(256), login varchar(256) unique, password varchar(256), primary key(id))";
        executor.execUpdate(update);
    }

    public void dropTable() throws SQLException {
        String update = "DROP TABLE if EXISTS Users";
        executor.execUpdate(update);
    }
}
