package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public interface StoreHelperPopulator {

        private Map<Category, Integer> createCategoriesMap() {
                Map<Category, Integer> categoryProductMap = new HashMap<>();
                Reflections reflections = new Reflections("by.issoft.domain.categories");

                Set<Class<?>> subTypes = reflections.get(Scanners.SubTypes.of(Category.class).asClass());

                for (Class<?> type : subTypes) {
                        try {
                                Random random = new Random();
                                Category result = (Category) type.getDeclaredMethod("getInstance").invoke(new Object());
                                categoryProductMap.put(result, random.nextInt(5, 10));
                        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                                e.printStackTrace();
                        }
                }
                return categoryProductMap;
        }

        public default void fillStore() {
        }
}