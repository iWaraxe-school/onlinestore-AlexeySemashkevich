package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {
   private Store store;

    private StoreHelper(Store store) {this.store = store;}

    private static volatile StoreHelper instance;
    public static StoreHelper getInstance() {
        StoreHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (StoreHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StoreHelper(Store.getInstance());
                }
            }
        }
        return localInstance;
    }

    private Map<Category,Integer> createProductListToAdd(){
        Map<Category, Integer> categoryProductMap = new HashMap<>();
        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<?>> subTypes = reflections.get(Scanners.SubTypes.of(Category.class).asClass());

        for (Class<?> type : subTypes) {
            try {
                Random random = new Random();
                Category result = (Category) type.getDeclaredMethod("getInstance").invoke(new Object());
                categoryProductMap.put(result, random.nextInt(5,10));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return categoryProductMap;
    }

    public void fillStore() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProductMapToAdd = createProductListToAdd();
        for (Map.Entry<Category, Integer> entry : categoryProductMapToAdd.entrySet()) {
            store.addCategoryToList(entry.getKey());

            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populator.getProductName(String.valueOf(entry.getKey().getClass().getSimpleName())),
                        populator.getPrice(),
                        populator.getRate());
                entry.getKey().addProductToCategory(product);
                store.mapPutter(product,entry.getKey());
                store.setTopPricedList(product);
            }
        }
    }

}
