package by.issoft.domain.categories;

import by.issoft.domain.Category;

public class BikeCategory extends Category {

    private BikeCategory() {
        super("Bike");
    }
    public static class SingletonHolder {
        public static final BikeCategory HOLDER_INSTANCE = new BikeCategory();
    }

    public static BikeCategory getInstance() {
        return BikeCategory.SingletonHolder.HOLDER_INSTANCE;
    }
}
