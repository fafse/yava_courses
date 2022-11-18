package ru.croc.task10.src;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class Item implements java.lang.Runnable {
    private String name;
    private double curPrice;
    private Calendar timeStart;
    private Calendar timeEnd;
    private String winnerName;
    private int counter = 0;
    private char ch;

    private void showWinner() {
        System.out.println("Name:" + winnerName + "\nPrice:" + curPrice);
    }

    public double getCurPrice() {
        return curPrice;
    }

    private Boolean changePrice(String name, double price) {
        if (price <= this.curPrice) {
            return false;//"Цена меньше либо равна текущей максимальной ставке";
        }
        if (getInstance().getTimeInMillis() <= timeEnd.getTimeInMillis() && getInstance().getTimeInMillis() >= timeStart.getTimeInMillis()) {
            curPrice = price;
            this.winnerName = name;
        }
        return true;//"Цена успешно изменена";
    }

    private Item(String name, double curPrice, Calendar timeStart, Calendar timeEnd, char c) {
        this.name = name;
        this.curPrice = curPrice;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        ch = c;
    }

    public static Item createItem(String name, double curPrice, Calendar timeStart, Calendar timeEnd, char a) {
        if (name == null || name == "" ||
                curPrice < 0 || timeStart.getTimeInMillis() >= timeEnd.getTimeInMillis()) {
            return null;
        }
        return new Item(name, curPrice, timeStart, timeEnd, a);
    }

    public void run() {
        int menu = 0;
        String nameWinner;
        double price;
        synchronized (this) {
            while (getInstance().getTimeInMillis() <= timeEnd.getTimeInMillis() && getInstance().getTimeInMillis() >= timeStart.getTimeInMillis()) {
                System.out.println("Good:" + name + "\nCurrent price:" + curPrice);
                System.out.println("1. Change price");
                System.out.println("2. Print current winner");
                System.out.println("0. Exit");
                menu = Task10.cin.nextInt();
                switch (menu) {
                    case 1: {
                        System.out.println("Enter name");
                        nameWinner = Task10.cin.next();
                        System.out.println("Enter price");
                        price = Task10.cin.nextDouble();
                        changePrice(nameWinner, price);
                        break;
                    }
                    case 2: {
                        showWinner();
                        break;
                    }
                    case 0: {
                        return;
                    }
                    default: {
                        break;
                    }
                }
            }
            if (getInstance().getTimeInMillis() > timeEnd.getTimeInMillis() || getInstance().getTimeInMillis() < timeStart.getTimeInMillis()) {
                System.out.println("Time is gone....");
            }
        }
    }
}
