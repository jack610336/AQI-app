package com.practice.jack_wang.weatherquery.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.practice.jack_wang.weatherquery.R;
import com.practice.jack_wang.weatherquery.sqlite.weatherEntry;


import java.util.List;

/**
 * Created by Jack_Wang on 2017/12/14.
 */

public class MyRecyclerAdpter extends RecyclerView.Adapter<MyRecyclerAdpter.ViewHolder> {


    private List<weatherEntry> mweatherEntryList;

    public RecyclerViewItemClick mrecyclerViewItemClick;

    public interface RecyclerViewItemClick{
        void onItemClick(View view, int position);
    }
    public void setRecyclerViewItemClick(RecyclerViewItemClick click){
        this.mrecyclerViewItemClick = click;
    }

    public MyRecyclerAdpter(List<weatherEntry> weatherEntryList){
        mweatherEntryList = weatherEntryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int currentAQI=Integer.parseInt(mweatherEntryList.get(position).getCityValue());
        holder.itemView.setTag(position);

        holder.txtcityName.setText(mweatherEntryList.get(position).getCityName());
//        holder.emptytxt.setText(mweatherEntryList.get(position).getDate());
        holder.txtTemp.setText("AQI : "+mweatherEntryList.get(position).getCityValue());


        if( currentAQI >=0 &&  currentAQI <= 50){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#00DB00"));
        }else if(currentAQI >50 && currentAQI <=100){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#FFCC00"));
        }else if(currentAQI > 100 && currentAQI <=150){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#FF8000"));
        }
        else if(currentAQI > 150 && currentAQI <=200){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else if(currentAQI >200 && currentAQI <=300){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#B766AD"));
        }
        else if(currentAQI > 300 ){
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#AE0000"));
        }
        else {
            holder.backgroundPics.setBackgroundColor(Color.parseColor("#FDFFFF")); //例外狀況
        }


    }

    @Override
    public int getItemCount() {

        return mweatherEntryList.size();
    }

    public void setList(List<weatherEntry> list){

        this.mweatherEntryList = list;
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtcityName,txtTemp,emptytxt;
        LinearLayout backgroundPics;

        public ViewHolder(View itemView) {
            super(itemView);
            txtcityName = itemView.findViewById(R.id.txtcityName);
            txtTemp = itemView.findViewById(R.id.txtcityValue);
            backgroundPics = itemView.findViewById(R.id.layoutbackground);
            emptytxt = itemView.findViewById(R.id.empty);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(mrecyclerViewItemClick != null){
                mrecyclerViewItemClick.onItemClick(view ,(Integer) view.getTag());
            }

        }
    }
}
