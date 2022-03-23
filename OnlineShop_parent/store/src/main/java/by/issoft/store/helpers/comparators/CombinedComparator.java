package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;

import java.util.Comparator;

public class CombinedComparator implements Comparator<Product> {
    private String fieldName;
    private SortOrder sortOrder;

    public CombinedComparator(String fieldName, SortOrder sortOrder) {
    }

    XmlReader xmlReader = new XmlReader();

    public Comparator<Product> combined = xmlReader.getSortOrderList()
            .stream()
            .map(sortConfig -> new CombinedComparator(sortConfig.getFieldName(), sortConfig.getSortOrder()))
            .reduce(null, (combinedSoFar, nextComparator) ->
                    (combinedSoFar == null) ? nextComparator : (CombinedComparator) combinedSoFar.thenComparing(nextComparator));

    public CombinedComparator() {

    }


    @Override
    public int compare(Product o1, Product o2) {
        Comparator<Product> comparator = combined;
        if (sortOrder == SortOrder.DESC) comparator = comparator.reversed();
        return comparator.compare(o1, o2);    }

}
