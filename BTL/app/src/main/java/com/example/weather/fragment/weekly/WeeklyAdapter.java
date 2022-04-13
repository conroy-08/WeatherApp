package com.example.weather.fragment.weekly;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.fragment.detail.DetailAdapter;
import com.example.weather.model.DailyForecastDetail;
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
        // con icon
        return view;
    }
    /*List<WeeklyForecast> itemList;


    public WeeklyAdapter(List<WeeklyForecast> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public WeeklyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyAdapter.ViewHolder holder, int position) {
        //Picasso.get().load("http:".concat(itemList.get(position).getIcon())).into(holder.icon);
        holder.temp.setText(itemList.get(position).getLat() + "/"+itemList.get(position).getLon());
        holder.des.setText( itemList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView day;
        TextView temp;
        TextView des;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.weather_img);
            day = itemView.findViewById(R.id.day);
            temp = itemView.findViewById(R.id.temp);
            des = itemView.findViewById(R.id.desc);
        }
    }*/
}