package by.issoft.consoleApp;



import by.issoft.store.Store;
import by.issoft.store.helpers.OrderedProductsListCleaner;
import by.issoft.store.helpers.StoreHelper;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StoreApp {
    public static void main(String[] args) throws InterruptedException {

        Scanner userInput = new Scanner(System.in);
        Store onlineStore = Store.getInstance();
        StoreHelper storeHelper = StoreHelper.getInstance();

        new Thread(storeHelper).start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Welcome to our store." + "\n" + "We have following products: " );
        onlineStore.printAllCategoriesAndProducts();
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
                    onlineStore.printSortedByXmlProductList();
                    break;
                case "top5":
                    onlineStore.printTopPricedList();
                    break;
                case "quit":
                    System.out.println("Come again to our shop");
                    System.exit(1);
                case "order":
                    System.out.println("Order is created\n");
                    new Thread(onlineStore).start();
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