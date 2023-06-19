package com.example.quanlygiaotrinh.Register_Screen.Register_Form_Screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class AdapterChooseTeacher extends RecyclerView.Adapter<AdapterChooseTeacher.ViewHoder> {
    ArrayList<String> nameTeacher;
    ArrayList<String> viewTeachers;

    public AdapterChooseTeacher(ArrayList<String> nameTeacher, ArrayList<String> viewTeachers) {
        this.nameTeacher = nameTeacher;
        this.viewTeachers = viewTeachers;
    }

    public AdapterChooseTeacher(ArrayList<String> nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    @NonNull
    @Override
    public AdapterChooseTeacher.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterChooseTeacher.ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_teacher,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChooseTeacher.ViewHoder holder, int position) {
        String item = nameTeacher.get(position);
        holder.textView.setText(item);

    }

    @Override
    public int getItemCount() {
        return nameTeacher.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item_nameTeacher);
            checkBox=itemView.findViewById(R.id.item_checkTeacher);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked){
                  viewTeachers.add(textView.getText().toString());
                }
                else{
                    viewTeachers.remove(textView.getText().toString());
                }
            });
        }
    }
}
