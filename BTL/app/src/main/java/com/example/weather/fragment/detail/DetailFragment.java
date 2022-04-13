package com.example.weather.fragment.detail;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.R;
import com.example.weather.model.DailyForecastDetail;
import com.example.weather.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends Fragment {

    private  View mView;


    List<DailyForecastDetail> list;
    DetailAdapter adapter;
    ListView listView;


    private Context mContext;

    public DetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail,container,false);

        listView = mView.findViewById(R.id.list_detail_item);
        getJsonWeather("hanoi","1");
        adapter = new DetailAdapter(getContext(),R.layout.detail_items,list);
        listView.setAdapter(adapter);

        return  mView;

    }
    private void getJsonWeather(String cityName, String numberDays) {
        list = new ArrayList<>();
        String url = Constants.BASE_URL+"?key="+Constants.API_KEY+"&q="+cityName+"&days="+numberDays+"&aqi=yes&alerts=yes";
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject forecastObj= response.getJSONObject("forecast");
                            JSONObject forecast0=forecastObj.getJSONArray("forecastday").getJSONObject(0);
                            JSONArray hourArray =forecast0.getJSONArray("hour");
                            for(int i=0;i< hourArray.length();i++)
                            {
                                JSONObject hourObj = hourArray.getJSONObject(i);
                                String time = hourObj.getString("time");
                                String temper =hourObj.getString("temp_c");
                                String img=hourObj.getJSONObject("condition").getString("icon");
                                list.add(new DailyForecastDetail(time,temper,img));
                            }
                            adapter.notifyDataSetChanged();
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



}
