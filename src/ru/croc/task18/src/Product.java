package ru.croc.task18.src;

public class Product {
    private String nameGoods;
    private String articleNumber;

    public String getNameGoods() {
        return nameGoods;
    }

    public int getPrice() {
        return price;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    private int price;
    Product(String nameGoods,String articleNumber, int price)
    {
        this.articleNumber=articleNumber;
        this.nameGoods=nameGoods;
        this.price=price;
    }
    @Override
    public String toString()
    {
        return articleNumber + " "+ nameGoods +" "+ price;
    }

}
