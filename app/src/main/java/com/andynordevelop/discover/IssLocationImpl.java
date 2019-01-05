package com.andynordevelop.discover;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import static com.andynordevelop.discover.MainActivity.MULTIPLE_PERMISSIONS;

public class IssLocationImpl {

    public static String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    LocationManager locationManager;

    Context mainContext;
    public IssLocationImpl(Context mainContext) {
        this.locationManager = (LocationManager) mainContext.getSystemService(Context.LOCATION_SERVICE);
    }

    protected IssLocationVO getCurrentUserLocation() {
        if (ActivityCompat.checkSelfPermission(mainContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mainContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions((Activity) mainContext, permissions, MULTIPLE_PERMISSIONS);
        }

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }


}
