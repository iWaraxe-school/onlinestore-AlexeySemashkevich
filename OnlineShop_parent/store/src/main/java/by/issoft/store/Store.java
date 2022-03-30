package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.comparators.CombinedComparator;

import java.util.*;

public class Store {

    private List<Category> categoryList = new ArrayList<Category>();
    private Map<Product,Category> productMap = new TreeMap<>(new CombinedComparator(){});

    public void printAllCategoriesAndProducts(){
        categoryList.forEach(Category::printAllProducts);
    }

    public void addCategoryToList(Category category) {
        categoryList.add(category);
    }

    public void mapPutter(Product product, Category category){
        productMap.put(product,category);
    }

    public void printSortedByXmlProductList(){
            System.out.println("*******************************************************************");
            System.out.println("Sorted products via xml: ");
            System.out.println("___________________________________________________________________");
            productMap.keySet().forEach(System.out::println);
            System.out.println("___________________________________________________________________");
    }
    


}

