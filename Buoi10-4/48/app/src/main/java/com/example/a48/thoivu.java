package com.example.a48;

import androidx.annotation.NonNull;

public class thoivu extends employee{
    @Override
    public int Luong() {
        return 150;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString()+"Thời vụ";
    }
}
