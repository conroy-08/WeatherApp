package com.example.weather.fragment.today;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.weather.MainActivity;
import com.example.weather.R;

import com.example.weather.fragment.FragmentListener;
import com.example.weather.fragment.settings.SettingsFragment;
import com.example.weather.model.ForecastSetting;
import com.example.weather.util.Constants;
import com.example.weather.util.Utility;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TodayFragment extends Fragment {

    Unbinder unbinder;
    private  View mView;
    private String cityName = "Hanoi";
    private String numberDays = "7";

    @BindView(R.id.city) TextView mcity;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.condition) TextView condition;
    @BindView(R.id.weather_resource) ImageView weather_resource;
    @BindView(R.id.temp_condition) TextView temp_condition;
    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.humidity_value) TextView humidity_value;
    @BindView(R.id.wind_value) TextView wind_value;
    @BindView(R.id.uv_value) TextView uv_value;

    private Context mContext;

    public TodayFragment() {
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        unbinder = ButterKnife.bind(this, mView);
        receiveDataFromSettings(cityName , numberDays);

        getJsonWeather(cityName,numberDays);
        return  mView;

    }




    private void getJsonWeather(String cityName, String numberDays) {
        String url = Constants.BASE_URL+"?key="+Constants.API_KEY+"&q="+cityName+"&days="+numberDays+Constants.AQI_ALERT;
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            temp_condition.setText(response.getJSONObject("current").getString("temp_c") +"°C");
                            mcity.setText(response.getJSONObject("location").getString("name") );
                            uv_value.setText(response.getJSONObject("current").getString("uv")+"%");
                            condition.setText(response.getJSONObject("current").getJSONObject("condition").getString("text"));
                            temperature.setText(response.getJSONObject("current").getString("feelslike_c")+"°C");
                            humidity_value.setText(response.getJSONObject("current").getString("humidity"));
                            wind_value.setText(response.getJSONObject("current").getString("wind_mph"));
                            //Để load hình ảnh dùng thư viện Picasso
                            String img=response.getJSONObject("current").getJSONObject("condition").getString("icon");
                            Picasso.get().load("http:".concat(img)).into(weather_resource);
                            date.setText(Utility.formatDate());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        //adding the string request to request queue
        requestQueue.add(request);
    }


    public  void receiveDataFromSettings(String city , String days){
        cityName = city;
        numberDays = days;
        System.out.println("this is today "+ cityName +" / "+numberDays);
    }

}

