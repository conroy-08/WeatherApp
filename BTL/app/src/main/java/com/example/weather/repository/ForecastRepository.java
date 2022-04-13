package com.example.weather.repository;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.model.DailyForecast;
import com.example.weather.util.Constants;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastRepository {

    public List<DailyForecast> loadForecasts(String city , String numDays){
        List<DailyForecast> dailyForecasts = new ArrayList<>();

        String url = Constants.BASE_URL +city+"&days="+numDays+"&aqi=yes&alerts=yes";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {


            }
        });
        return  dailyForecasts;
    }
}
