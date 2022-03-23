package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.comparators.CombinedComparator;

import java.util.*;
import java.util.stream.Stream;

public class Store {

    private List<Category> categoryList = new ArrayList<Category>();
    private Map<Product,Category> ProductMap = new LinkedHashMap<>();

    public void printAllCategoriesAndProducts(){
        categoryList.forEach(Category::printAllProducts);

    }

    public void addCategoryToList(Category category) {
        categoryList.add(category);
    }

    public void mapPutter(Product product, Category category){
        ProductMap.put(product,category);
    }

//    public Map<Product, Category> getSortedProductMap() {
//        CombinedComparator combinedComparator = new CombinedComparator();
//        ProductMap.entrySet().stream()
//                .sorted(combinedComparator.combined)
//                .map(sortedProductMap)
//        return sortedProductMap;
//    }
//
//        public void printMap(){
//
//        System.out.println(ProductMap);
//    }







}
