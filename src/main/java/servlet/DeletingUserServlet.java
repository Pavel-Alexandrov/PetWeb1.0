package servlet;

import exception.DBException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete")
public class DeletingUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserService userService = new UserService();

            Integer id = Integer.valueOf(request.getParameter("id"));
            userService.deleteUser(id);
            response.sendRedirect("/start");

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException e) {
            throw new IOException("ошибка при удалении пользователя");
        }
    }
}
