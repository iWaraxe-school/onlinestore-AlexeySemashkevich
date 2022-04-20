package by.issoft.store.helpers;

import by.issoft.store.Store;

public class OrderedProductsListCleaner implements Runnable{
    @Override
    public void run() {
        Store store = Store.getInstance();
        store.cleanUpOrderList();
        System.out.println("Order list is cleared\n");

    }
}