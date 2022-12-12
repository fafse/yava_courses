package ru.croc.task18.src;

public class Order {
    private int numberOrder;
    private String name;
    private String articleNumber;
    Order(int numberOrder,String name, String articleNumber)
    {
        this.articleNumber=articleNumber;
        this.name=name;
        this.numberOrder=numberOrder;
    }

}
