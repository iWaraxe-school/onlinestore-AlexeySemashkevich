package by.issoft.consoleApp;



import by.issoft.domain.Category;
import by.issoft.store.Store;
import by.issoft.store.helpers.OrderedProductsListCleaner;
import by.issoft.store.helpers.StoreHelper;
import by.issoft.store.helpers.StoreHelperDB;
import by.issoft.store.helpers.StoreHelperHttp;
import by.issoft.store.helpers.dbconnector.DBConnector;
import lombok.SneakyThrows;

import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class StoreApp {
    @SneakyThrows
    public static void main(String[] args) throws InterruptedException {

        Scanner userInput = new Scanner(System.in);
        Store onlineStore = Store.getInstance();
        StoreHelper storeHelper = StoreHelper.getInstance();
        DBConnector dbConnector = DBConnector.getInstance();
        StoreHelperDB storeHelperDB = StoreHelperDB.getInstance();
        StoreHelperHttp storeHelperHttp = new StoreHelperHttp();

        OrderedProductsListCleaner orderedProductsListCleaner = new OrderedProductsListCleaner();
        Timer timer = new Timer();
        timer.schedule(orderedProductsListCleaner,0,120_000);

        new Thread(dbConnector).start();
//        new Thread(storeHelper).start();
        TimeUnit.MILLISECONDS.sleep(500);
        storeHelperDB.fillStore();

        storeHelperHttp.HttpPopulator();
        storeHelperHttp.fillStore();
        storeHelperHttp.getCategories();
        storeHelperHttp.getProductsForCategory();
//        storeHelperHttp.getProductsFromCart();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\n" + "Welcome to our store." + "\n" + "We have following products: " );
//        onlineStore.printAllCategoriesAndProducts();
        storeHelperDB.printAllProductsFromDB();
        String str = "To sort all products please input 'sort' and press Enter\n" +
                "To show top 5 high-priced products input 'top5' and press Enter\n" +
                "To order random products input 'order' and press Enter\n"+
                "To exit application input 'quit' and press Enter\n";
        System.out.println(str);
        String input;
        input = userInput.next().toLowerCase();
        while(true) {
            switch (input) {
                case "sort":
//                    onlineStore.printSortedByXmlProductList();
                    storeHelperDB.printSortedProductList();
                    break;
                case "top5":
//                    onlineStore.printTopPricedList();
                    storeHelperDB.top5PricedProducts();
                    break;
                case "quit":
                    System.out.println("Come again to our shop");
                    System.exit(1);
                case "order":
                    System.out.println("Order is created\n");
//                    new Thread(onlineStore).start();
                    storeHelperDB.printOrderedProducts();
                    break;
                default:
                    System.out.println("Wrong command try again.\n");
                    break;
            }
            System.out.println(str);
            input = userInput.next().toLowerCase();
        }
    }
}