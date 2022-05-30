package by.issoft.domain.categories;

import by.issoft.domain.Category;

public class PhoneCategory extends Category {

    @Override
    public String getCategoryName() {
        return super.getCategoryName();
    }
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
