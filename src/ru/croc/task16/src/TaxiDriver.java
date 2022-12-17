package ru.croc.Task16.src;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaxiDriver {
    final private String identificator;
    final private String comfortClass;
    final private double latitude, longitude;
    final private Set<String> additionalFeatures;//дополнительные возможности

    public String getIdentificator() {
        return identificator;
    }

    public String getComfortClass() {
        return comfortClass;
    }

    public double getLongitude() {
        return longitude;
    }

    public Set<String> getAdditionalFeatures() {
        return new HashSet<>(additionalFeatures);
    }

    public double getLatitude() {
        return latitude;
    }

    public TaxiDriver(String comfortClass, double latitude, double longitude, Set<String> additionalFeatures,String identificator)
    {
        this.comfortClass = comfortClass;
        this.latitude=latitude;
        this.longitude = longitude;
        this.additionalFeatures= additionalFeatures;
        this.identificator = identificator;
    }

    public boolean checkMatch(String comfortClass, Set<String> requirements)
    {
        return this.getComfortClass().equals(comfortClass)
                && this.getAdditionalFeatures().containsAll(requirements);
    }
    @Override
    public String toString()
    {
        return identificator;
    }

}
