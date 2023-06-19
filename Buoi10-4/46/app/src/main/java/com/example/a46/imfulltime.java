package com.example.a46;

public class imfulltime extends Employee{
    @Override
    public double luong() {
        return 500;
    }

    @Override
    public String toString() {
        return "Fulltime----"+ super.toString() + "---Lương:----"+luong();
    }
}
