package by.issoft.store;



import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.comparators.CombinedComparator;
import by.issoft.store.helpers.comparators.TopPricedComparator;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Store implements Runnable {
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
    private ConcurrentSkipListMap<Product, Category> productMap = new ConcurrentSkipListMap<>(new CombinedComparator());
    private Set<Product> topPricedList = new TreeSet<>(new TopPricedComparator());
    private CopyOnWriteArrayList<Product> orderedProducts = new CopyOnWriteArrayList<>();


    public void printAllCategoriesAndProducts() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
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

    @Override
    public void run() {
        getOrderedProducts();
        printOrderedProducts();
    }


    private CopyOnWriteArrayList<Product> getOrderedProducts() {
        Random random = new Random();
        List<Product> targetList = new ArrayList<>(productMap.keySet());
        int limit = productMap.keySet().size();
        int randomInt = random.nextInt(1,limit);
        for(int j = 0; j < randomInt; j++ ){
            orderedProducts.add(targetList.get(random.nextInt(0,limit)));
        }
        return orderedProducts;
    }

    public void printOrderedProducts(){
        System.out.println("*******************************************************************");
        System.out.println("You have ordered following products: ");
        System.out.println("___________________________________________________________________");
        orderedProducts.forEach(System.out::println);
        System.out.println("___________________________________________________________________\n");

    }

    public void cleanUpOrderList (){
        orderedProducts.clear();
    }

}