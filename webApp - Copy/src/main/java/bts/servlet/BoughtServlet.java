package bts.serverlet;

import bts.dao.BucketDao;
import bts.dao.BucketProductDao;
import bts.dao.ProductDao;
import bts.model.Bucket;
import bts.model.Product;
import bts.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/buy")
public class BoughtServlet extends HttpServlet {

    private BucketProductDao bucketProductDao = new BucketProductDao();
    private BucketDao bucketDao = new BucketDao();
    private ProductDao productDao  = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        Bucket bucket = bucketDao.findById(currentUser.getId())
                    .orElseThrow(NoSuchElementException::new);

        Product product = productDao.findById(id)
                .orElseThrow(NoSuchElementException::new);

        bucketProductDao.add(bucket.getId(), product.getId());

        resp.sendRedirect("/shop");

    }
}
