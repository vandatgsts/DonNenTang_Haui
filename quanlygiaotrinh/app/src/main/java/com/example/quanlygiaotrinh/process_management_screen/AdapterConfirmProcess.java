package com.example.quanlygiaotrinh.process_management_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class AdapterConfirmProcess extends RecyclerView.Adapter<AdapterConfirmProcess.ViewHolder> {
    ArrayList<Book> books;

    public AdapterConfirmProcess(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public AdapterConfirmProcess.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterConfirmProcess.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tiem_confirmation,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterConfirmProcess.ViewHolder holder, int position) {
        Book item=books.get(position);
        holder.name.setText(item.getName());
        holder.ans.setText((item.getAns()==0?"Không Đạt":"Đạt"));
        if(item.getIdeal()==null){
            holder.ideal.setVisibility(View.GONE);
        }
        else {
            holder.ideal.setText(item.getIdeal());
        }
    }

    @Override
    public int getItemCount() {
        return books.isEmpty()?0:books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView ans;
        TextView ideal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.confirmation_item_txt_name);
            ans=itemView.findViewById(R.id.confirmation_item_txt_ans);
            ideal=itemView.findViewById(R.id.confirmation_item_txt_ideal);
        }
    }
}
