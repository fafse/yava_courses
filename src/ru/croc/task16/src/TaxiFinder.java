package ru.croc.Task16.src;

import java.sql.Driver;
import java.util.*;

public class TaxiFinder {
    List<TaxiDriver> drivers;
    public TaxiFinder(List<TaxiDriver> drivers)
    {
            this.drivers=drivers;
    }
    private List<TaxiDriver> getDrivers()
    {
        return new ArrayList<>(drivers);
    }

    public TaxiDriver findDriver(Set<String> requirements, double latitude, double longitude, String comfortClass)
    {
        List<TaxiDriver> tmpDrivers = getDrivers();
        if(tmpDrivers.isEmpty())
        {
            return null;
        }
        Comparator<TaxiDriver> comparator = (td1,td2)->
        {
            if(td1!=null&&td2!=null) {
                boolean is_1_ok = td1.checkMatch(comfortClass,requirements);
                boolean is_2_ok =td2.checkMatch(comfortClass,requirements);
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
        tmpDrivers.sort(comparator);
        if(tmpDrivers.get(0).checkMatch(comfortClass,requirements))
        {
            return tmpDrivers.get(0);
        }
        return null;
    }

}
