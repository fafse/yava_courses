package ru.croc.task10.src;
/*
* 3 параметра
* текущая стоимость
* имя пользователя
* время окончания торгов по лоту
* метод ставки, который обновляет стоимость лота и сохраняет пользователя, предложившего её
* второй метод получает имя пользователя-победителя
* делать ставки на лот могут сразу несколько пользователей
* реализовать возможность участия в ставках большого количества пользователй без ошибок(потокобезопасно)
* */


import java.util.Calendar;
import java.util.Scanner;

import static java.util.Calendar.getInstance;

public class Task10 {
    public static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Calendar startData = getInstance();
        Calendar endData = getInstance();
        endData.add(Calendar.SECOND,50);
        Item r1 = Item.createItem("Huawei watch GT2", 200, startData, endData);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r1);
        Thread t4 = new Thread(r1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
        System.out.println(r1.getCurPrice());
    }
}