package by.issoft.domain.categories;

import by.issoft.domain.Category;

public class MilkCategory extends Category {

    @Override
    public String getCategoryName() {
        return super.getCategoryName();
    }

    private MilkCategory() {
        super("Milk");
    }
    public static class SingletonHolder {
        public static final MilkCategory HOLDER_INSTANCE = new MilkCategory();
    }

    public static MilkCategory getInstance() {
        return MilkCategory.SingletonHolder.HOLDER_INSTANCE;
    }
}
