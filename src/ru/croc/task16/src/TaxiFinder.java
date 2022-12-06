package ru.croc.Task16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ru.croc.Task16.TaxiDriver;

public class TaxiFinder {
    List<TaxiDriver> drivers;
    public TaxiFinder(List<TaxiDriver> drivers)
    {
        if(!drivers.isEmpty())
        {
            this.drivers = new ArrayList<>();
            this.drivers.addAll(drivers);
        }
    }


    public TaxiDriver findDriver(String requirements, double latitude, double longitude, String comfortClass)
    {
        Comparator<TaxiDriver> comparator = (td1,td2)->
        {
            if(td1!=null&&td2!=null) {
                boolean is_1_ok = td1.getComfortClass().equals(comfortClass)
                        && td1.getAdditionalFeatures().contains(requirements.toLowerCase());
                boolean is_2_ok =td2.getComfortClass().equals(comfortClass)
                        && td2.getAdditionalFeatures().contains(requirements.toLowerCase());
                //System.out.println(td1 + " and " + td1.getComfortClass().equals(comfortClass) + "\n"+td2 + " and "+td2.getComfortClass().equals(comfortClass));
                if (is_1_ok
                        && is_2_ok) {
                    return Double.compare((Math.abs(td1.getLatitude() - latitude) + Math.abs(td1.getLongitude() - longitude)), (Math.abs(td2.getLatitude() - latitude) + Math.abs(td2.getLongitude() - longitude)));
                }
                else if(is_1_ok&&!is_2_ok) {
                    return -1;
                }
                else if(is_2_ok&&!is_1_ok)
                {
                    return 1;
                }
            }
            return 0;
        };
        drivers.sort(comparator);
        if(drivers.get(0).getComfortClass().equals(comfortClass)&&drivers.get(0).getAdditionalFeatures().contains(requirements))
        {
            return drivers.get(0);
        }
        return null;
    }

}
