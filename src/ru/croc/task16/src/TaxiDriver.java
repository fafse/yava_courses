package ru.croc.Task16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaxiDriver {
    private String identificator;
    private String comfortClass;
    private double latitude, longitude;

    public String getIdentificator() {
        return identificator;
    }

    public String getComfortClass() {
        return comfortClass;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getAdditionalFeatures() {
        return additionalFeatures;
    }

    private List<String> additionalFeatures= new ArrayList<>();//дополнительные возможности

    public double getLatitude() {
        return latitude;
    }

    public TaxiDriver(String comfortClass, double latitude, double longitude, List<String> additionalFeatures,String identificator)
    {
        this.comfortClass = comfortClass;
        this.latitude=latitude;
        this.longitude = longitude;
        this.additionalFeatures.addAll(additionalFeatures);
        this.identificator = identificator;
    }

    public boolean checkmatch(String comfortClass, List<String> requirements)
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
