package ru.croc.Task16;

import ru.croc.Task16.TaxiDriver;

import java.util.ArrayList;
import ru.croc.Task16.TaxiFinder;
import java.util.List;

public class Task16 {
    public static void main(String[] args) {
        List<TaxiDriver> drivers = new ArrayList<>();
        TaxiDriver tmp = new TaxiDriver("comfort",59.9,58.9,"for kids","1");
        drivers.add(tmp);
        tmp =new TaxiDriver("free",50,58.9,"music","2");
                drivers.add(tmp);
        tmp =new TaxiDriver("comfort",59.9,58.9,"music","3");
                drivers.add(tmp);
        TaxiFinder finder = new TaxiFinder(drivers);
        System.out.println(finder.findDriver("music",50,50,"comfort"));
    }
}