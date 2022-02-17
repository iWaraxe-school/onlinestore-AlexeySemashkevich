package by.issoft.store;

import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private Faker faker = new Faker();

    public RandomStorePopulator() {
    }

    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "Bike":
                return faker.aviation().aircraft();
            case "Phone" :
                return faker.company().name();
            case "Milk" :
                return faker.beer().name();
            default: return null;

        }
    }
    public Double getPrice() {return faker.number().randomDouble(2,1,10_000);}
    public Double getRate() {return faker.number().randomDouble(1,1,5);}



}
