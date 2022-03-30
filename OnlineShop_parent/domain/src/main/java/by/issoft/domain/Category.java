package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Category {

    private String categoryName;
    private List<Product> productList = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProductToCategory(Product product){
        productList.add(product);
    }
//
//    public Stream<Product> productStream() {
//       return productList.stream();
//    }

    public void printAllProducts(){
        System.out.println("###################################################################");
        System.out.println("Category: " + categoryName);
        System.out.println("___________________________________________________________________");
        productList.stream().map(Product::toString).forEach(System.out::println);
        System.out.println("___________________________________________________________________");
        System.out.println("");}

}