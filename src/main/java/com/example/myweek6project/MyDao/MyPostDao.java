package com.example.myweek6project.MyDao;

import com.example.myweek6project.MyModels.Post;
import com.example.myweek6project.MyModels.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyPostDao {
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

    public static int addPost(Post post) throws SQLException {
        int status = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = MyPostDao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO post (user_Id,post_Messages) VALUES (?,?)");
            preparedStatement.setInt(1, post.getUser_id());
            preparedStatement.setString(2, post.getPost_messages());
            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<Post> displayPost() throws SQLException {
        List<Post> listOfPost = new ArrayList<>();

        try {
            Connection connection = MyPostDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from post");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                int postId = result.getInt("post_id");
                int user_id = result.getInt("user_id");
                String message = result.getString("post_Messages");
                post.setPost_messages(message);
                post.setUser_id(user_id);
                post.setPost_id(postId);
                listOfPost.add(post);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfPost;
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
                user1.setUser_id(resultSet.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user1;
    }

}
