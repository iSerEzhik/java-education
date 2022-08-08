import com.github.javafaker.Faker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();
        Faker faker = Faker.instance();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i + 1, faker.commerce().price(), faker.commerce().productName()));
        }
        resp.getWriter().println("<html>\n\t<body>\n\t<h2>Products:</h2>");
        for (Product product : productList) {
            resp.getWriter().print(product + "<br>");
        }
        resp.getWriter().println("</body>\n\r</html>");
    }

    static class Product {
        private final int id;
        private final String cost;
        private final String title;

        public Product(int id, String cost, String title) {
            this.id = id;
            this.cost = cost;
            this.title = title;
        }

        @Override
        public String toString() {
            return "#%1$d. %2$s = %3$s rub".formatted(id, title, cost);
        }
    }
}
