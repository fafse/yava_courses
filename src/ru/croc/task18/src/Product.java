package ru.croc.task18.src;

public class Product {
    private String nameGoods;
    private String articleNumber;
    private int price;
    Product(String nameGoods,String articleNumber, int price)
    {
        this.articleNumber=articleNumber;
        this.nameGoods=nameGoods;
        this.price=price;
    }
}