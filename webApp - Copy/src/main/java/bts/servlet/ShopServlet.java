package bts.serverlet;

import bts.dao.ProductDao;
import bts.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDao.findAll();

        req.setAttribute("products", products);

        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
}
