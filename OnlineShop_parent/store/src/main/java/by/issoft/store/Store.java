package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.comparators.CombinedComparator;
import by.issoft.store.helpers.comparators.TopPricedComparator;

import java.util.*;

public class Store {
    private static volatile Store instance;

    public static Store getInstance() {
        Store localInstance = instance;
        if (localInstance == null) {
            synchronized (Store.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Store();
                }
            }
        }
        return localInstance;
    }

    private Store() {}

    private List<Category> categoryList = new ArrayList<Category>();
    private Map<Product,Category> productMap = new TreeMap<>(new CombinedComparator());
    private Set<Product> topPricedList = new TreeSet<>(new TopPricedComparator());

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
            System.out.println("___________________________________________________________________\n");
    }
    
    public void setTopPricedList(Product product){ topPricedList.add(product); }

    public void printTopPricedList(){
        System.out.println("*******************************************************************");
        System.out.println("Top 5 high-priced products: ");
        System.out.println("___________________________________________________________________");
        topPricedList.stream().limit(5).forEach(System.out::println);
        System.out.println("___________________________________________________________________\n");
    }

}

