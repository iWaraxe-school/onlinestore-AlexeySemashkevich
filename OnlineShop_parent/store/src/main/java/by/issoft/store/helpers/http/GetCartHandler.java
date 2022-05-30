package by.issoft.store.helpers.http;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class GetCartHandler extends StoreHttpHandler {
    StoreHttpServer server;

    public GetCartHandler(StoreHttpServer server){
        this.server = server;
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        super.handle(httpExchange, server.getCartProducts());
    }
}
