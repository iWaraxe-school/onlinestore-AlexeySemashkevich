package by.issoft.consoleApp;


import by.issoft.store.Store;
import by.issoft.store.helpers.StoreHelper;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);

        storeHelper.fillStore();
        System.out.println("Welcome to our store." + "\n" + "We have following products: " );
        onlineStore.printAllCategoriesAndProducts();
        String str = "To sort all products please input 'sort' and press Enter\nTo show top 5 high-priced products input 'top5' and press Enter\nTo exit application input 'quit' and press Enter\n";
        System.out.println(str);
        String input;
        input = userInput.next().toLowerCase();
        while(true) switch (input) {
            case "sort":
                onlineStore.printSortedByXmlProductList();
                System.out.println(str);
                input = userInput.next().toLowerCase();
                break;
            case "top5":
                onlineStore.printTopPricedList();
                System.out.println(str);
                input = userInput.next().toLowerCase();
                break;
            case "quit":
                System.out.println("Come again to our shop");
                System.exit(1);
            default:
                System.out.println("Wrong command try again.");
                System.out.println(str);
                input = userInput.next().toLowerCase();
                break;
        }
    }
}
