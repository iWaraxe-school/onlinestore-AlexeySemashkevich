package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StoreHelper {
    Store store;

    public StoreHelper(Store store) {this.store = store;}

    private static Map<Category,Integer> createProductListToAdd(){
        Map<Category, Integer> categoryProductMap = new HashMap<>();
        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<?extends Category> type : subTypes) {
            try {
                Random random = new Random();
                categoryProductMap.put(type.getConstructor().newInstance(), random.nextInt(20));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return categoryProductMap;
    }

    public void fillStore(Map<Category, Integer> categoryProductMap) {
        RandomStorePopulator populator = new RandomStorePopulator();
        for (Map.Entry<Category, Integer> entry : categoryProductMap.entrySet()) {
          //  Set<Field> ids = reflections.get(FieldsAnnotated.with(Id.class).as(Field.class))
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populator.getProductName(String.valueOf(entry.getKey().getClass().getSimpleName())),
                        populator.getPrice(),
                        populator.getRate()
                );
            }
        }
    }

}
