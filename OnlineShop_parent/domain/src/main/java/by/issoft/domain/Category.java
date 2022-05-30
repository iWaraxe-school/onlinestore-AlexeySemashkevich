package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor


public class Category {

    @Getter
    @Setter
    private String categoryName;
    private List<Product> productList = new ArrayList<>();

    protected Category(String categoryName) { this.categoryName = categoryName; }

    public void addProductToCategory(Product product){
        productList.add(product);
    }

    public void addProducts(List<Product> products) {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.addAll(products);
    }

    public void printAllProducts(){
        System.out.println("###################################################################");
        System.out.println("Category: " + categoryName);
        System.out.println("___________________________________________________________________");
        productList.stream().map(Product::toString).forEach(System.out::println);
        System.out.println("___________________________________________________________________\n\n");
    }
}