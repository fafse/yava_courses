package ru.croc.Task16;

import java.util.Comparator;

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

    public String getAdditionalFeatures() {
        return additionalFeatures;
    }

    private String additionalFeatures;//дополнительные возможности

    public double getLatitude() {
        return latitude;
    }

    public TaxiDriver(String comfortClass, double latitude, double longitude, String additionalFeatures,String identificator)
    {
        this.comfortClass = comfortClass;
        this.latitude=latitude;
        this.longitude = longitude;
        this.additionalFeatures = additionalFeatures;
        this.identificator = identificator;
    }
    @Override
    public String toString()
    {
        return identificator;
    }

}
