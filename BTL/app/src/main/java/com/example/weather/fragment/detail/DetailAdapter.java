package com.example.weather.fragment.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;

import com.example.weather.model.DailyForecastDetail;
import com.squareup.picasso.Picasso;


import java.util.List;

public class DetailAdapter extends ArrayAdapter<DailyForecastDetail> {


    Context context;
    int layout;
    List<DailyForecastDetail> list;


    public DetailAdapter(Context context, int layout, List<DailyForecastDetail> list) {
        super(context,layout,list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    class  ViewHolder{
        ImageView icon;
        TextView txtTime;
        TextView txtTemp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder = null;
        if (viewHolder == null){
            // ktra view neu bang null -> lay ve 1 cai view
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            // Anh xa
            viewHolder.txtTime = view.findViewById(R.id.time_detail);
            viewHolder.txtTemp = view.findViewById(R.id.temp);
            viewHolder.icon = view.findViewById(R.id.weather_img);
            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        DailyForecastDetail weather = list.get(i);
        viewHolder.txtTime.setText(weather.getTime());
        viewHolder.txtTemp.setText(weather.getTemperature() );
       // Picasso.get().load("http:".concat(weather.getIcon())).into(viewHolder.icon);

        return view;
    }
}
