package by.issoft.store;

import by.issoft.domain.Category;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> categoryList = new ArrayList<Category>();

    public void printAllCategoriesAndProducts(){
        for (Category category : categoryList) {
            category.printAllProducts();
        }
    }

}
