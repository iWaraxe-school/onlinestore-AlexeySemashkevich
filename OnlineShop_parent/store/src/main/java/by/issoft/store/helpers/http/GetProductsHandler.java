package by.issoft.store.helpers.http;


import by.issoft.domain.Category;
import by.issoft.domain.categories.CategoriesENUM;
import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;

import java.io.IOException;

public class GetProductsHandler extends StoreHttpHandler {
    CategoriesENUM category;
    StoreHttpServer server;

    public GetProductsHandler(CategoriesENUM category, StoreHttpServer server){

        this.category = category;
        this.server = server;
    }

    @SneakyThrows
    public void handle(HttpExchange httpExchange) throws IOException {

        super.handle(httpExchange, server.getProductsForCategory(category, server.getCategories()));
    }
}
