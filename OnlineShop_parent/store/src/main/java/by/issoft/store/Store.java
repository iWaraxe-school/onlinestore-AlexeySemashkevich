package by.issoft.store;

import by.issoft.domain.Category;

import java.util.ArrayList;

import java.util.List;



public class Store {

    List <Category> categoryList = new ArrayList<Category>();


    public void printAllCategories(){
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = new Category();
            category = categoryList.get(i);
            String catName = category.getCategoryName();  //тут идея ругалась и моим решением было добавление геттера для того чтобы решить проблему, гуттер тут временно, чтобы все запускалосьб хотя я не уверен, что это верное решение
            if (catName.equals("Bike")) {
                category.printAllProducts();
            } else if (catName.equals("Phone")) {
                category.printAllProducts();
            } else if (catName.equals("Milk")) {
                category.printAllProducts();}
        }
    }




}
