package ru.croc.Task16.src;
/*пример ввода параметров
59.9386 30.3141 comfort kids
* */


import java.util.*;

public class Task16 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        double latitude, longitude;
        String classComfort;
        Set<String> requirements = new HashSet<>();

        String[] client = cin.nextLine().split(" ");
        if(client.length<4)
        {
            System.out.println("Lack of elements");
            return;
        }


        latitude = Double.parseDouble(client[0]);
        longitude = Double.parseDouble(client[1]);
        classComfort = client[2];
        for(int i = 3;i< client.length;i++) {
            requirements.add(client[i]);
        }

        List<TaxiDriver> drivers = new ArrayList<TaxiDriver>();

        drivers.add(new TaxiDriver("comfort",59.9,58.9,Set.of("kids") ,"onedriver"));
        drivers.add(new TaxiDriver("free",50,58.9, Set.of("music"),"twodriver"));
        drivers.add(new TaxiDriver("comfort",59.9,58.9, Set.of("music"),"threedriver"));

        TaxiFinder finder = new TaxiFinder(drivers);

        System.out.println(finder.findDriver(requirements,latitude,longitude,classComfort));
    }
}