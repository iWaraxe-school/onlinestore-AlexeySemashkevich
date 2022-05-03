package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> productList = new ArrayList<>();

    protected Category(String categoryName) { this.categoryName = categoryName; }

    public void addProductToCategory(Product product){
        productList.add(product);
    }

    public void printAllProducts(){
        System.out.println("###################################################################");
        System.out.println("Category: " + categoryName);
        System.out.println("___________________________________________________________________");
        productList.stream().map(Product::toString).forEach(System.out::println);
        System.out.println("___________________________________________________________________\n\n");
    }
}