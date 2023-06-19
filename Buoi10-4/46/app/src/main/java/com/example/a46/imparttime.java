package com.example.a46;

public class imparttime extends Employee {

    @Override
    public double luong() {
        return 150;
    }

    @Override
    public String toString() {
        return "Parttime ---"+ super.toString()+"---Lương----:"+luong();
    }
}
