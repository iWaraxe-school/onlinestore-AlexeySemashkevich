package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> productList = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProductToCategory(Product product){
        productList.add(product);
    }

    public void printAllProducts(){
        System.out.println("################################################################");
        System.out.println("Category: " + categoryName);
        System.out.println("________________________________________________________________");
        for (Product product: productList){
            System.out.println(product.toString());}
        System.out.println("________________________________________________________________");
        System.out.println("");;}

}