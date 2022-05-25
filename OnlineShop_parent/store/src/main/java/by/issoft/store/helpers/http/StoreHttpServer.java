package by.issoft.store.helpers.http;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoriesENUM;
import by.issoft.store.helpers.StoreHelperDB;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
public class StoreHttpServer {
    public static final int PORT = 8080;
    public static final String BASE_URL = "http://localhost:" + PORT;
    public static final String GET_CATEGORIES_URL = BASE_URL + "/categories";
    public static final String GET_PRODUCTS_FOR_CATEGORY_URL = BASE_URL + "/productsForCategory";
    public static final String CART_URL = BASE_URL + "/cart";
    private final List<Product> cartProducts = new ArrayList<>();
    private final List<Product> allProducts = new ArrayList<>();
    public StoreHelperDB helperDB = StoreHelperDB.getInstance();
    public List<Category> categories = new ArrayList<>();

    public StoreHttpServer() {
        run();
    }

    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            StoreHelperDB storeHelperDB = StoreHelperDB.getInstance();
            List<Category> allCategories = storeHelperDB.getCategoriesFromDB();
            for (Category category : allCategories) {
                server.createContext(("/productsForCategory/" + category.getCategoryName()),
                        new GetProductsHandler(CategoriesENUM.valueOf(category.getCategoryName()), this));
            }
            server.createContext("/categories", new GetCategoryHandler(this));
            server.createContext("/cart", new GetCartHandler(this));

            System.out.println("server started at " + PORT);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories() {

        if (categories != null) {
            categories = helperDB.getCategoriesFromDB();
        }

        return categories;
    }

    public List<Product> getProductsForCategory(CategoriesENUM categoryName, List<Category> categories) throws SQLException {

        List<Product> products = helperDB.getProductsForCategory();

        Category category = categories.stream().filter(x -> x.getCategoryName().equals(x.getCategoryName())).findFirst().orElse(null);
        assert category != null;
        category.addProducts(products);

        return products;
    }

    public Product addProductToCart(String productName, List<Product> cartProducts, List<Product> allProducts) {

        Product product = getSelectedProduct(productName, allProducts);
        cartProducts.add(product);

        return product;
    }

    private Product getSelectedProduct(String productName, List<Product> products) {
        Optional<Product> orderedProduct = getAllProducts(products).stream()
                .filter(x -> x.getName().equals(productName))
                .findFirst();

        return orderedProduct.orElse(null);
    }

    private List<Product> getAllProducts(List<Product> products) {
        for (Category category : this.categories) {
            products.addAll(category.getProductList());
        }

        return products;
    }
}
