package com.example.weather.fragment.weekly;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.model.WeeklyForecast;
import com.squareup.picasso.Picasso;

import java.util.List;


public class WeeklyAdapter extends ArrayAdapter<WeeklyForecast> {

    Context context;
    int layout;
    List<WeeklyForecast> list;


    public WeeklyAdapter(Context context, int layout, List<WeeklyForecast> list) {
        super(context,layout,list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    class  ViewHolder{
        ImageView icon;
        TextView day;
        TextView temp;
        TextView des;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WeeklyAdapter.ViewHolder viewHolder = null;
        if (viewHolder == null){
            // ktra view neu bang null -> lay ve 1 cai view
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            viewHolder = new WeeklyAdapter.ViewHolder();
            // Anh xa

            viewHolder.day = view.findViewById(R.id.day);
            viewHolder.temp = view.findViewById(R.id.temp);
            viewHolder.des = view.findViewById(R.id.desc);
            viewHolder.icon = view.findViewById(R.id.weather_img);
            view.setTag(viewHolder);

        }else{
            viewHolder = (WeeklyAdapter.ViewHolder) view.getTag();
        }
        WeeklyForecast weather = list.get(i);
        viewHolder.temp.setText(weather.getLat() + "/"+weather.getLon());
        viewHolder.des.setText( weather.getDescription());
        Picasso.get().load("http:".concat(weather.getIcon())).into(viewHolder.icon);
        // con icon
        return view;
    }

}