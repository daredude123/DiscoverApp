package com.andynordevelop.discover;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import static com.andynordevelop.discover.MainActivity.permissions;

public class UserLocationImpl {

    Context mainContext;
    LocationManager locationManager;

    public UserLocationImpl(Context mainContext) {
        this.mainContext = mainContext;
        this.locationManager = (LocationManager) mainContext.getSystemService(Context.LOCATION_SERVICE);

    }

    protected UserLocationVO getCurrentUserLocation() {
        if (ActivityCompat.checkSelfPermission(mainContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mainContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mainContext, permissions, 1);
        }
        return convertToUserLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
    }

    private UserLocationVO convertToUserLocation(Location lastKnownLocation) {
        return new UserLocationVO(lastKnownLocation);
    }
}
