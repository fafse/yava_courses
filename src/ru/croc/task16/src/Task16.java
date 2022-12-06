package ru.croc.Task16;
/*пример ввода параметров
59.9386
30.3141
comfort
kids
* */

import ru.croc.Task16.TaxiDriver;

import java.util.ArrayList;
import ru.croc.Task16.TaxiFinder;

import java.util.Collections;
import java.util.List;

public class Task16 {
    public static void main(String[] args) {
        double latitude, longitude;
        String classComfort;
        List<String> requirements=new ArrayList<>();
        if(args.length<4)
        {
            System.out.println("Wrong args num"+args.length);
            return;
        }
        latitude = Double.parseDouble(args[0]);
        longitude = Double.parseDouble(args[1]);
        requirements.clear();
        classComfort = args[2];
        for(int i = 3;i< args.length;i++)
        {
            requirements.add(args[i]);
        }
        List<TaxiDriver> drivers = new ArrayList<>();
        TaxiDriver tmp = new TaxiDriver("comfort",59.9,58.9, Collections.singletonList("kids"),"onedriver");
        drivers.add(tmp);
        tmp =new TaxiDriver("free",50,58.9, Collections.singletonList("music"),"twodriver");
                drivers.add(tmp);
        tmp =new TaxiDriver("comfort",59.9,58.9, Collections.singletonList("music"),"threedriver");
                drivers.add(tmp);
        TaxiFinder finder = new TaxiFinder(drivers);
        System.out.println(finder.findDriver(requirements,latitude,longitude,classComfort));
    }
}