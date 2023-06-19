package com.example.a48;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter {
    Activity context;//activy truyen vao
    int LayoutID;
    ArrayList<employee> arrlistemployee;

    public Adapter(@NonNull Activity context, int resource, ArrayList<employee> e) {
        super(context, resource, e);
        this.context = context;
        this.LayoutID = resource;
        this.arrlistemployee = e;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutID,null);
            final TextView id = convertView.findViewById(R.id.txtID);
            final TextView  ten = convertView.findViewById(R.id.txthoten);
            final TextView  nvgi = convertView.findViewById(R.id.txttype);
            final  TextView luong = convertView.findViewById(R.id.txtluong);
            employee employee1 = arrlistemployee.get(position);
            id.setText(employee1.getId());
            ten.setText(employee1.getName());
            nvgi.setText(employee1.toString().toString());
            String s = String.valueOf(employee1.Luong());
            luong.setText(s);
        return convertView;

    }
}
