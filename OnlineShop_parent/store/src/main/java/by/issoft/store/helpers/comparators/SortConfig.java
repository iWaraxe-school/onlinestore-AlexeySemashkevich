package by.issoft.store.helpers.comparators;

public class SortConfig {

    private String fieldName;
    private SortOrder sortOrder;

    public SortConfig(String fieldName, String sortOrder) {
        this.fieldName = fieldName;
        this.sortOrder = SortOrder.valueOf(sortOrder);
    }

    public String getFieldName() {
        return fieldName;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }
}
