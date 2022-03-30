package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Field;
import java.util.*;




public class CombinedComparator implements Comparator<Product> {

//    private Map<String, String> sortBy;

//    public CombinedComparator(Map sortBy){
//        this.sortBy = sortBy;
//    }

    XmlReader xmlReader = new XmlReader();

    public CombinedComparator() {
    }

    @Override
    public int compare(Product a, Product b) {
        CompareToBuilder compareBuilder = new CompareToBuilder();
        for (SortConfig item : xmlReader.getSortOrderList()) {
            SortOrder sortOrder = (item.getSortOrder());
            try {
                if (sortOrder == SortOrder.ASC) {
                    compareBuilder.append(this.getPropertyValue(a, item.getFieldName()), this.getPropertyValue(b, item.getFieldName()));
                } else {
                    compareBuilder.append(this.getPropertyValue(b, item.getFieldName()), this.getPropertyValue(a, item.getFieldName()));
                }
            } catch (Exception e) {
                System.out.println("Error: Products were not compared. An exception was thrown with the message: " + e.getMessage());
                return 0;
            }
        }

        return compareBuilder.toComparison();
    }

    private String getPropertyValue(Product product, String property) throws Exception {
        try {
            Field field = product.getClass().getDeclaredField(property);
            field.setAccessible(true);
            return String.valueOf(field.get(product));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new Exception(ex);
        }
    }
}





