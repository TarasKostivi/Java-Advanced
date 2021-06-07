package bts.serverlet;

import bts.dao.UserDao;
import bts.model.Role;
import bts.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password= req.getParameter("password");

        User user = new User(email, password, name, Role.USER);

        userDao.save(user);
        User saved = userDao.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);

        resp.sendRedirect("/login");
    }
}
