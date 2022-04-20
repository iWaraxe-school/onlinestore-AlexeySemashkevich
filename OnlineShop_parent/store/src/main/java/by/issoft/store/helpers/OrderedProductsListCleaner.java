package by.issoft.store.helpers;

import by.issoft.store.Store;

import java.util.TimerTask;

public class OrderedProductsListCleaner extends TimerTask {
    @Override
    public void run() {
        Store store = Store.getInstance();
        store.cleanUpOrderList();
        System.out.println("Order list is cleared\n");

    }
}