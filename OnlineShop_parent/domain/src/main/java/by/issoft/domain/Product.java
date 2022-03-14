package by.issoft.domain;


public class Product {

    private String name;
    private double price;
    private double rate;

    public Product(String name, double price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String toString() {
        String prodInfo;
        prodInfo = String.format("Name: '%-15s', Price: %s$, Rate: %s ",name,price,rate);
        return prodInfo;
    }


}

