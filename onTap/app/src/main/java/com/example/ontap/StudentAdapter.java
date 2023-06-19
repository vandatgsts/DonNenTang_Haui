package com.example.ontap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> studentList;
    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }
    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = convertView.findViewById(R.id.txt_ViewName);
            holder.maSVTextView = convertView.findViewById(R.id.txt_MSV);
            holder.lopTextView = convertView.findViewById(R.id.txt_Class);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student student = studentList.get(position);
        holder.nameTextView.setText(student.name);
        holder.maSVTextView.setText(student.maSV);
        holder.lopTextView.setText(student.lop);

        return convertView;
    }
    private static class ViewHolder {
        TextView nameTextView;
        TextView maSVTextView;
        TextView lopTextView;
    }
}
