package Controller;

import com.example.myweek6project.MyDao.MyPostDao;
import com.example.myweek6project.MyDao.MyUserDao;
import com.example.myweek6project.MyModels.Post;
import com.example.myweek6project.MyModels.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        MyPostDao myPostDao = new MyPostDao();
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        try {
            User user1 = MyUserDao.userLogin(user);
            if (user1 != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user1);
                List<Post> postList = MyPostDao.displayPost();
                session.setAttribute("list", postList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
