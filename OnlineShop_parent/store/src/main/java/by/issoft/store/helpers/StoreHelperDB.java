package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoriesENUM;
import by.issoft.store.Store;
import by.issoft.store.helpers.comparators.CombinedComparator;
import by.issoft.store.helpers.dbconnector.DBConnector;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

import static by.issoft.store.helpers.dbconnector.DBConnector.getConnection;
import static java.lang.String.valueOf;

public class StoreHelperDB implements StoreHelperPopulator {
    private Store store;

    private StoreHelperDB(Store store) {
        this.store = store;
    }

    private static volatile StoreHelperDB instance;

    public static StoreHelperDB getInstance() {
        StoreHelperDB localInstance = instance;
        if (localInstance == null) {
            synchronized (StoreHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StoreHelperDB(Store.getInstance());
                }
            }
        }
        return localInstance;
    }

    private List<Category> categoryList = new ArrayList<>();


    private Map<Category, Integer> createCategoriesMap() {
        Map<Category, Integer> categoryProductMap = new HashMap<>();
        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<?>> subTypes = reflections.get(Scanners.SubTypes.of(Category.class).asClass());

        for (Class<?> type : subTypes) {
            try {
                Random random = new Random();
                Category result = (Category) type.getDeclaredMethod("getInstance").invoke(new Object());
                categoryProductMap.put(result, random.nextInt(5, 10));
                categoryList.add(result);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return categoryProductMap;
    }

    ;
    private String preparedStatementString = "INSERT INTO PUBLIC.\"Categories\" (\"categoryName\")\n" +
            "VALUES (?);";

    @Override
    public void fillStore() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProductMapToAdd = createCategoriesMap();

        for (Map.Entry<Category, Integer> entry : categoryProductMapToAdd.entrySet()) {

            try {
                String value = valueOf(entry.getKey().getCategoryName());
                DBConnector dbConnector = DBConnector.getInstance();
                PreparedStatement preparedStatement = getConnection().prepareStatement(preparedStatementString);
                preparedStatement.setString(1, value);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String preparedQuery = "INSERT INTO PUBLIC.\"Products\" (\"NAME\", \"PRICE\", \"RATE\", \"Category\")\n" +
                    "VALUES (?, ?, ?, ?);";
            for (int i = 0; i < entry.getValue(); i++) {
                String value4 = valueOf(entry.getKey().getCategoryName());
                String value1;
                double value2;
                double value3;

                Product product = new Product(
                        value1 = valueOf(populator.getProductName(valueOf(entry.getKey().getClass().getSimpleName()))),
                        value2 = populator.getPrice(),
                        value3 = populator.getRate());

                DBConnector dbConnector = DBConnector.getInstance();
                PreparedStatement preparedStatement2 = null;
                try {
                    preparedStatement2 = getConnection().prepareStatement(preparedQuery);
                    preparedStatement2.setString(1, value1);
                    preparedStatement2.setDouble(2, value2);
                    preparedStatement2.setDouble(3, value3);
                    preparedStatement2.setString(4, value4);
                    preparedStatement2.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        ;
        addProductsToSortedList();
    }

    public void printAllProductsFromDB() {
        String query1 = "SELECT * FROM PUBLIC.\"Products\"";
        try {
            Statement statement1 = getConnection().createStatement();
            ResultSet rs1 = statement1.executeQuery(query1);
            System.out.println("###################################################################");
            System.out.println("___________________________________________________________________");
            while (rs1.next()) {
                String name = rs1.getString(2);
                Double price = rs1.getDouble(3);
                Double rate = rs1.getDouble(4);
                System.out.printf("Name: %-30s Price: %7s$    Rate: %-4s \n", name, price, rate);
            }
            System.out.println("___________________________________________________________________\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConcurrentSkipListSet<Product> sortedList = new ConcurrentSkipListSet<Product>(new CombinedComparator());

    private void addProductsToSortedList() {
        DBConnector dbConnector = DBConnector.getInstance();
        String query2 = "SELECT * FROM PUBLIC.\"Products\"";
        try {
            Statement statement1 = getConnection().createStatement();
            ResultSet rs2 = statement1.executeQuery(query2);

            while (rs2.next()) {
                String name = rs2.getString(2);
                Double price = rs2.getDouble(3);
                Double rate = rs2.getDouble(4);

                sortedList.add(new Product(name, price, rate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printSortedProductList() {
        System.out.println("*******************************************************************");
        System.out.println("Sorted products via xml: ");
        System.out.println("___________________________________________________________________");
        sortedList.forEach(System.out::println);
        System.out.println("___________________________________________________________________\n");
    }

    public void top5PricedProducts() {
        DBConnector dbConnector = DBConnector.getInstance();
        String query3 = "SELECT TOP 5 * FROM PUBLIC.\"Products\" ORDER BY PRICE DESC";
        try {
            Statement statement1 = getConnection().createStatement();
            ResultSet rs3 = statement1.executeQuery(query3);

            System.out.println("###################################################################\n" +
                    "Top 5 high-priced products: \n" +
                    "___________________________________________________________________");
            while (rs3.next()) {
                String name = rs3.getString(2);
                Double price = rs3.getDouble(3);
                Double rate = rs3.getDouble(4);
                System.out.printf("Name: %-30s Price: %7s$    Rate: %-4s \n", name, price, rate);
            }
            System.out.println("___________________________________________________________________\n");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    private CopyOnWriteArrayList<Product> orderedProducts = new CopyOnWriteArrayList<>();

    private CopyOnWriteArrayList<Product> getOrderedProducts() {
        Random random = new Random();
        String preparedQuery = "SELECT * FROM PUBLIC.\"Products\" WHERE ID = ?;";
        int limit = sortedList.size();
        int randomInt = random.nextInt(1, limit);

        for (int j = 0; j < randomInt; j++) {
            DBConnector dbConnector = DBConnector.getInstance();
            PreparedStatement preparedStatement4 = null;
            try {
                Statement statement1 = getConnection().createStatement();
                preparedStatement4 = getConnection().prepareStatement(preparedQuery);
                preparedStatement4.setInt(1, random.nextInt(1, limit));

                ResultSet resultSet = preparedStatement4.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getNString(2);
                    double price = resultSet.getDouble(3);
                    double rate = resultSet.getDouble(4);
                    orderedProducts.add(new Product(name, price, rate));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return orderedProducts;
    }

    public void printOrderedProducts() {
        getOrderedProducts();
        System.out.println("*******************************************************************");
        System.out.println("You have ordered following products: ");
        System.out.println("___________________________________________________________________");
        orderedProducts.forEach(System.out::println);
        System.out.println("___________________________________________________________________\n");

    }

    public void cleanUpOrderList() {
        orderedProducts.clear();
    }

    public List<Category> getCategoriesFromDB() {
//        String query1 = "SELECT * FROM PUBLIC.\"Categories\"";
//
//        try {
//            Statement statement1 = getConnection().createStatement();
//            ResultSet rs1 = statement1.executeQuery(query1);
//            JSONObject jsonObject = new JSONObject();
//            JSONArray array = new JSONArray();
//
//
//            while (rs1.next()) {
//                JSONObject record = new JSONObject();
//
//                record.put("ID", rs1.getInt("ID"));
//                record.put("Category_Name", rs1.getString("categoryName"));
//                array.add(record);
//
//            }
//            jsonObject.put("Categories", array);
//            try {
//                FileWriter file = new FileWriter("OnlineShop_parent/store/src/main/resources/Json/output.json");
//                file.write(jsonObject.toJSONString());
//                file.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("JSON file created......");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return categoryList;
    }

    public List<Product> getProductsForCategory() throws SQLException {

        List<Category> categoryList1 = getCategoriesFromDB();
        List<Product> products = new ArrayList<>();
        DBConnector dbConnector = DBConnector.getInstance();
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM PUBLIC.\"Products\" WHERE \"Category\" = ?;";
        for (Category category1 : categoryList1) {

            try {
                Statement statement1 = getConnection().createStatement();
                preparedStatement = getConnection().prepareStatement(sql);

                preparedStatement.setString(1, valueOf(category1.getCategoryName()));
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    products.add(new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getDouble("rate")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return products;


    }
}




