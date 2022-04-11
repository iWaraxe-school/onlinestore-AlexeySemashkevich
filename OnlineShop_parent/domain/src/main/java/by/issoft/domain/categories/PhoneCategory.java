package by.issoft.domain.categories;

import by.issoft.domain.Category;

public class PhoneCategory extends Category {

    private PhoneCategory() {
        super( "Phone");
    }
    public static class SingletonHolder {
        public static final PhoneCategory HOLDER_INSTANCE = new PhoneCategory();
    }

    public static PhoneCategory getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
