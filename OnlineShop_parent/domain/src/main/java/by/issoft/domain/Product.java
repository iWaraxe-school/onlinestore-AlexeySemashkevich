package by.issoft.domain;


public class Product {

    public String name;
    public double rate;
    public double price;

    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String toString() {
        String prodInfo = new String();
        prodInfo = String.format("Name: '%s', Rate: %s Price: %s $",name,rate,price );
        return prodInfo;
    }


}

