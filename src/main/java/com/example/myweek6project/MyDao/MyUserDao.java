package com.example.myweek6project.MyDao;

import com.example.myweek6project.MyModels.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MyUserDao {

    protected static Connection getConnection() {
        String MyUrl = "jdbc:mysql://localhost:3306/MyProjectDataBase";
        String MyUsername = "root";
        String MyPassword = "flora1993";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(MyUrl, MyUsername, MyPassword);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    //
    public static int insertUser(User user) throws SQLException {
        int status = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = MyUserDao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (first_name, last_name, password, email, username) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            System.out.println(preparedStatement);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int updateUser(User user) throws SQLException {
        int status = 0;
        try {
            Connection connection = MyUserDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users set first_name=?, last_name=?, password=?, email=?, username=? where id = ?");
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            status = preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int deleteUser(User user) throws SQLException {
        int status = 0;
        try {
            Connection connection = MyUserDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
            preparedStatement.setInt(1, user.getUser_id());
            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static User userLogin(User user) {
        User user1 = null;

        try {
            Connection connection = MyUserDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

//          System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user1 = new User();
                user1.setUsername(resultSet.getString("username"));
                user1.setUser_id(resultSet.getInt("user_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user1;
    }

}
