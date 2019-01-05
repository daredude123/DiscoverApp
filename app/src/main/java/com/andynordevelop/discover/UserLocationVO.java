package com.andynordevelop.discover;

import android.location.Location;

import java.util.List;

public class UserLocationVO {
    Location lastLocation;
    List<Location> historicUserLocations;

    public UserLocationVO(Location lastLocation, List<Location> historicUserLocation) {
        this.lastLocation = lastLocation;
        this.historicUserLocations = historicUserLocation;
    }

    public UserLocationVO() {
    }


}
