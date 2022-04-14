package com.example.weather.fragment.weekly;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.R;
import com.example.weather.fragment.detail.DetailAdapter;
import com.example.weather.model.DailyForecastDetail;
import com.example.weather.model.WeeklyForecast;
import com.example.weather.util.Constants;
import com.example.weather.util.Utility;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeeklyFragment extends Fragment {
    Unbinder unbinder;
    private  View mView;

    List<WeeklyForecast> list;
    WeeklyAdapter adapter;
    ListView listView;
    private String cityName = "Hanoi";
    private String numberDays = "7";


    @BindView(R.id.city) TextView mcity;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.condition) TextView condition;
    @BindView(R.id.weather_resource) ImageView weather_resource;
    @BindView(R.id.temp_condition) TextView temp_condition;

    private Context mContext;

    public WeeklyFragment() {
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
        mView = inflater.inflate(R.layout.fragment_weekly,container,false);
        unbinder = ButterKnife.bind(this, mView);

        listView = mView.findViewById(R.id.recyclerview);

        getJsonRelative(cityName,numberDays);
        adapter = new WeeklyAdapter(getContext(),R.layout.weekly_items,list);
        listView.setAdapter(adapter);

        return  mView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private  void getJsonRelative(String cityName, String numberDays){
        list = new ArrayList<>();
        String url = Constants.BASE_URL+"?key="+Constants.API_KEY+"&q="+cityName+"&days="+numberDays+"&aqi=yes&alerts=yes";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        temp_condition.setText(response.getJSONObject("current").getString("temp_c")+"°C" );
                        mcity.setText(response.getJSONObject("location").getString("name") );
                        condition.setText(response.getJSONObject("current").getJSONObject("condition").getString("text"));
                        String img=response.getJSONObject("current").getJSONObject("condition").getString("icon");
                        Picasso.get().load("http:".concat(img)).into(weather_resource);
                        date.setText(Utility.formatDate());

                        JSONObject forecastObj= response.getJSONObject("forecast");
                        JSONArray forecast0 = forecastObj.getJSONArray("forecastday");


                       for (int index =0 ;index< forecast0.length();index++){
                           JSONObject obj = forecast0.getJSONObject(index).getJSONObject("day");
                           String description = obj.getJSONObject("condition").getString("text");
                           String lat = obj.getString("maxtemp_c");
                           String lon = obj.getString("mintemp_c");
                           String icon =  obj.getJSONObject("condition").getString("icon");

                           list.add(new WeeklyForecast(icon,description,lat,lon));
                       }
                       adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());

        requestQueue.add(request);
    }


    public  void receiveDataFromSettings(String city , String days){
        cityName = city;
        numberDays = days;
        System.out.println("this is weekly "+ cityName +" / "+numberDays);
    }

}
