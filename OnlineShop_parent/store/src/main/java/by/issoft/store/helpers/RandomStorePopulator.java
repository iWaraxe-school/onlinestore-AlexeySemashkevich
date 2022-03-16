package by.issoft.store.helpers;

import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private Faker faker = new Faker();

    public RandomStorePopulator() {}

    public String getProductName(String valueOf) {
        switch (valueOf) {
            case "BikeCategory":
                return faker.aviation().aircraft();
            case "PhoneCategory" :
                return faker.funnyName().name();
            case "MilkCategory" :
                return faker.artist().name();
            default: return null;
        }
    }
    public Double getPrice() {return faker.number().randomDouble(2,1,10_000);}
    public Double getRate() {return faker.number().randomDouble(2,1,5);}

}
