package ru.croc.task10.src;

import java.util.Random;

import static java.util.Calendar.getInstance;

public class User implements java.lang.Runnable{
    Item my_lot;
    public void run() {
        int menu = 0;
        String nameWinner;
        double price;
        while (getInstance().getTimeInMillis() <= timeEnd.getTimeInMillis() && getInstance().getTimeInMillis() >= timeStart.getTimeInMillis()) {
            Random rn = new Random();
            nameWinner=getInstance().toString();
            price =  rn.nextDouble()+200;
            if(changePrice(nameWinner,price)) {
                //System.out.println( timeStart.getTimeInMillis()+" "+getInstance().getTimeInMillis() + " "+timeEnd.getTimeInMillis());
                //System.out.println(" I changed price " + price + " in "+ getInstance().getTimeInMillis());
            }
                /*System.out.println("Good:" + name + "\nCurrent price:" + curPrice);
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
                */
            if (getInstance().getTimeInMillis() > timeEnd.getTimeInMillis() || getInstance().getTimeInMillis() < timeStart.getTimeInMillis()) {
                System.out.println("Time is gone....");
                break;
            }
        }

    }
}
