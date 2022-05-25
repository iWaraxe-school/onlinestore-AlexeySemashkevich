package by.issoft.store.helpers;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.http.StoreHttpServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class StoreHelperHttp implements StoreHelperPopulator {

    public static final String DEFAULT_USERNAME = "Username";
    public static final String DEFAULT_PASSWORD = "Password";
    private final List<Product> cartProducts = new ArrayList<>();
    private final List<Product> allProducts = new ArrayList<>();
    public StoreHelperDB populator = StoreHelperDB.getInstance();
    public List<Category> categories = new ArrayList<>();


    StoreHttpServer server;
    HttpClient client;
    ObjectMapper objectMapper = new ObjectMapper();

    public void HttpPopulator() {
        server = new StoreHttpServer();
        createClient();
    }


    public List<Category> getCategories() {
        try {
            HttpGet request = new HttpGet(StoreHttpServer.GET_CATEGORIES_URL);
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            return objectMapper.readValue(result, new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            System.out.println("Error when getting all categories via HTTP : " + e.getLocalizedMessage() + "\n");
            e.printStackTrace();
        }

        return categories;
    }


    public List<Product> getProductsForCategory(Category category) {
        try {
            HttpGet request = new HttpGet(String.format(StoreHttpServer.GET_PRODUCTS_FOR_CATEGORY_URL + "/%s", category.getCategoryName()));
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            return objectMapper.readValue(result, new TypeReference<List<Product>>() {
            });

        } catch (IOException e) {
            System.out.println(String.format("Error when getting products for category '%S' via HTTP : ", category.getCategoryName()) + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void addToCart(String productName) throws Exception {

        HttpPost httppost = new HttpPost(StoreHttpServer.CART_URL);

        try {
            httppost.setEntity(new StringEntity(objectMapper.writeValueAsString(server.addProductToCart(productName, server.getCartProducts(), server.getAllProducts()))));

        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            System.out.println("Error when adding product to cart via HTTP : " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception();
        }
    }

    public List<Product> getProductsFromCart() throws Exception {

        try {
            HttpGet request = new HttpGet(StoreHttpServer.CART_URL);
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            return objectMapper.readValue(result, new TypeReference<List<Product>>() {
            });

        } catch (IOException e) {
            System.out.println("Error when getting all products from the cart via HTTP : " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception();
        }
    }

    private void createClient() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        provider.setCredentials(AuthScope.ANY, credentials);

        client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
    }
}
