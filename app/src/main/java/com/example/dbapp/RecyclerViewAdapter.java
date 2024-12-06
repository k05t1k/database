package com.example.dbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cars> carList;

    public RecyclerViewAdapter(Context context, ArrayList<Cars> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cars car = carList.get(position);
        holder.carName.setText(car.getCar_Name());
        holder.carMark.setText(car.getCar_Mark());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView carName, carMark;

        public ViewHolder(View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.c_name);
            carMark = itemView.findViewById(R.id.c_mark);
        }
    }
}


