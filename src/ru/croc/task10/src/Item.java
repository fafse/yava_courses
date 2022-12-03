package ru.croc.task10.src;

import java.util.Calendar;
import java.util.Random;

import static java.util.Calendar.getInstance;

public class Item {
    private volatile String name;
    private volatile double curPrice;
    private Calendar timeStart;
    private Calendar timeEnd;
    private String winnerName;
    private int counter = 0;

    private void showWinner() {
        System.out.println("Name:" + winnerName + "\nPrice:" + curPrice);
    }

    public double getCurPrice() {
        return curPrice;
    }

    private Boolean changePrice(String name, double price) {
        synchronized (this) {
            if (price <= this.curPrice) {
                return false;//"Цена меньше либо равна текущей максимальной ставке";
            }
            if (getInstance().getTimeInMillis() <= timeEnd.getTimeInMillis() && getInstance().getTimeInMillis() >= timeStart.getTimeInMillis()) {
                this.curPrice = price;
                this.winnerName = name;
            }
            return true;//"Цена успешно изменена";
        }

    }

    private Item(String name, double curPrice, Calendar timeStart, Calendar timeEnd) {
        this.name = name;
        this.curPrice = curPrice;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public static Item createItem(String name, double curPrice, Calendar timeStart, Calendar timeEnd) {
        if (name == null || name == "" ||
                curPrice < 0 || timeStart.getTimeInMillis() >= timeEnd.getTimeInMillis()) {
            return null;
        }
        return new Item(name, curPrice, timeStart, timeEnd);
    }


}
