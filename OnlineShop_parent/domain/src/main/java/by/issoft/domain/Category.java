package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> productList = new ArrayList<Product>();

    protected Category(String categoryName) {
        this.categoryName = null;
    }

    public void addProductToCategory(Product product){
        productList.add(product);
    }

    public void printAllProducts(){
        System.out.println("Category: " + categoryName);
        System.out.println("______________________________________________________________");
        for (Product product: productList){
            product.toString();}
        System.out.println("______________________________________________________________");
        System.out.println("");
    }


}