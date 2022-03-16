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
        prodInfo = String.format("Name: %-30s Price: %7s$ Rate: %-4s ",name,price,rate);
        return prodInfo;
    }


}

