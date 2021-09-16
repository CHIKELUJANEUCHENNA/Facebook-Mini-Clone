package Controller;

import com.example.myweek6project.MyDao.MyUserDao;
import com.example.myweek6project.MyModels.User;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
    private MyUserDao userDao;

    public void init() {
        userDao = new MyUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String FName = request.getParameter("first_name");
        String LName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String username = request.getParameter("username");

        User user = new User();
        user.setFirst_name(FName);
        user.setLast_name(LName);
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);

        int status = MyUserDao.insertUser(user);
        if (status > 0) {
            out.print("<p>Successfully Registered</p>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        } else {
            out.print("<p>Unable to register</p>");
        }
        out.close();
    }
}
