package com.andynordevelop.discover;

import android.location.Location;

import java.util.List;

public class UserLocationVO {
    Location lastLocation;

    public UserLocationVO(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public UserLocationVO() {
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    @Override
    public String toString() {
        return "UserLocationVO{" +
                "lastLocation=" + lastLocation +
                '}';
    }
}
