package bts.serverlet;

import bts.dao.UserDao;
import bts.exeption.UserNotRegisterException;
import bts.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password= req.getParameter("password");

        Optional<User> current = userDao.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email)
                        && user.getPassword().equals(password))
                .findFirst();
        if (current.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", current.get());
            resp.sendRedirect("/shop");
            return;
        }

        req.setAttribute("errorMassage", "Email or password don't exist ");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
