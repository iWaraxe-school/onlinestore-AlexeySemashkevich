package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Field;
import java.util.Comparator;

public class TopPricedComparator extends CombinedComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        CompareToBuilder compareBuilder = new CompareToBuilder();
        SortConfig sortConfig = new SortConfig("price", "DESC");
        SortOrder sortOrder = (sortConfig.getSortOrder());
        try {
            if (sortOrder == SortOrder.ASC) {
                compareBuilder.append(this.getPropertyValueDouble(a, (sortConfig.getFieldName())), this.getPropertyValueDouble(b, (sortConfig.getFieldName())));
            } else {
                compareBuilder.append(this.getPropertyValueDouble(b, (sortConfig.getFieldName())), this.getPropertyValueDouble(a, (sortConfig.getFieldName())));
            }
        } catch (Exception e) {
            System.out.println("Error: Products were not compared. An exception was thrown with the message: " + e.getMessage());
            return 0;
        }
        return compareBuilder.toComparison();
    }


    protected Double getPropertyValueDouble(Product product, String property) throws Exception {
        try {
            Field field = product.getClass().getDeclaredField(property);
            field.setAccessible(true);
            return java.lang.Double.valueOf(String.valueOf(field.get(product)));
        }  catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new Exception(ex);}
    }
}
