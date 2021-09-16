package Controller;

import com.example.myweek6project.MyDao.MyPostDao;
import com.example.myweek6project.MyDao.MyUserDao;
import com.example.myweek6project.MyModels.Post;
import com.example.myweek6project.MyModels.User;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String FName = request.getParameter("postBody");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Post post = new Post();
        post.setPost_messages(FName);
        post.setUser_id(user.getUser_id());

        int status = MyPostDao.addPost(post);
        if (status > 0) {
            out.print("<p>post uploaded successfully</p>");
            response.sendRedirect("homePage.jsp");
        } else {
            out.print("<p>Unable to post</p>");
        }
        out.close();
    }
}
