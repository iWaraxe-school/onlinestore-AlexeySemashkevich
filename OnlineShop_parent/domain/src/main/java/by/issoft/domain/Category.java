package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    public String categoryName;

    public Category() {
        this.categoryName = null;
    }

    public String getCategoryName() { // геттер тут был поставлен чтобы код запускался, есть плоблема в store.Store.printAllCategories методе, не уверен, что это верное решение
        return categoryName;
    }

    List<String> productList = new ArrayList<String>();

    public void addProduct(Product product) {
        String productInfo = new String();
        productInfo = product.toString();
        productList.add(productInfo);
    }

    public void printAllProducts(){
        System.out.println("Category: " + categoryName);
        System.out.println("______________________________________________________________");
        for (int i=0; i < productList.size(); i++){
            System.out.println(productList.get(i));}
        System.out.println("______________________________________________________________");
        System.out.println("");
    }


}