package com.example.quanlygiaotrinh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.info_Screen.ProgressManagement;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class AdapterReCyInfo extends RecyclerView.Adapter<AdapterReCyInfo.ViewHolder> {

    ArrayList<Book> list;
    Context mContext;

    public AdapterReCyInfo(ArrayList<Book> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public AdapterReCyInfo(ArrayList<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterReCyInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReCyInfo.ViewHolder holder, int position) {
        Book item = list.get(position);
        holder.txtLeader.setText(item.getAuthors().get(0));
        holder.txtName.setText(item.getName());
        holder.txtTime.setText(item.getTime());
        AdapterRecyChild adapter=new AdapterRecyChild(item.getAuthors());
        holder.listView.setAdapter(adapter);
        holder.constraintLayout.setOnClickListener(v ->{
            Intent intent=new Intent(mContext, ProgressManagement.class);
            intent.putExtra("bookId",item.getId());
            mContext.startActivity(intent);
        });


    }
    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLeader;
        ConstraintLayout constraintLayout;
        TextView txtName;
        TextView txtTime;
        RecyclerView listView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLeader = itemView.findViewById(R.id.process_txt_content);
            txtName = itemView.findViewById(R.id.process_txt_name);
            txtTime = itemView.findViewById(R.id.process_txt_time);
            listView = itemView.findViewById(R.id.txt_name_info_item2);
            listView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            constraintLayout = itemView.findViewById(R.id.layoutItem);


        }
    }
}
