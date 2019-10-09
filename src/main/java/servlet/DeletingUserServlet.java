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

            //порядок параметров userParam: id, login, name, password
            //типобезопасно ли то, что я внизу творю?
            List<Object> userParam = (List<Object>) request.getAttribute("user");
            userService.deleteUser( (String) userParam.get(1), (String) userParam.get(3));
            response.sendRedirect("/start");

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException e) {
            throw new IOException("ошибка при удалении пользователя");
        }
    }
}
