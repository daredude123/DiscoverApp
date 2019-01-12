package com.andynordevelop.discover;

import android.location.Location;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class IssLocationVO {


    static Location currentLocation;
    static Date currentTimeStamp;
    static Map<Location,Date> historicLocations;

    public IssLocationVO(Location currentLocation, Date currentTimeStamp, Map<Location, Date> historicLocations) {
        this.currentLocation = currentLocation;
        this.currentTimeStamp = currentTimeStamp;
        this.historicLocations = historicLocations;
    }

    public IssLocationVO() {
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Date getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(Date currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    public Map<Location, Date> getHistoricLocations() {
        return historicLocations;
    }

    public void setHistoricLocations(Map<Location, Date> historicLocations) {
        this.historicLocations = historicLocations;
    }
}
