package com.andynordevelop.discover;

import android.app.AlarmManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class IssLocationImpl {

    LocationManager locationManager;
    String ISSUrl = "http://api.open-notify.org/iss-now.json";
    IssLocationVO issLocationVO;
    Context mainContext;
    Handler handler;
    Runnable fetchIssLocationThread;
    final int delay = 1000*5;

    public IssLocationImpl(Context mainContext) {
        this.mainContext=mainContext;
        this.locationManager = (LocationManager) mainContext.getSystemService(Context.LOCATION_SERVICE);
        issLocationVO = new IssLocationVO();
        handler = new Handler();
    }

    public void startISSLocationThread() {
        handler.postDelayed(fetchIssLocationThread = new Runnable() {
            @Override
            public void run() {
                findCurrentISSLocation();
            }
        },delay);
    }

    public IssLocationVO getIssLocationVO() {
        return issLocationVO;
    }

    private void findCurrentISSLocation() {
        RequestQueue requestQueue = Volley.newRequestQueue(mainContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ISSUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            convertResponseToIssLocationVO(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private IssLocationVO convertResponseToIssLocationVO(String response) throws JSONException {
        IssLocationVO issLocationVO = new IssLocationVO();
        JSONObject jsonObject = new JSONObject(response);
        JSONObject locationJson = jsonObject.getJSONObject("iss_position");

        Location location = new Location(Context.LOCATION_SERVICE);
        location.setLatitude(locationJson.getDouble("latitude"));
        location.setLongitude(locationJson.getDouble("longitude"));
        location.setTime(jsonObject.getLong("timestamp"));

        issLocationVO.setCurrentLocation(location);
        issLocationVO.setCurrentTimeStamp(new Date(location.getTime()));

        //put data into historical
        issLocationVO.getHistoricLocations().put(issLocationVO.getCurrentLocation(), issLocationVO.getCurrentTimeStamp());

        Log.d("convert", jsonObject.toString());
        return issLocationVO;
    }
}
