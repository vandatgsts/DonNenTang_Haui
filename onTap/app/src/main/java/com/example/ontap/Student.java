package com.example.ontap;

public class Student {
    String name;
    String maSV;
    String lop;

    public Student() {
    }

    public Student(String name, String maSV, String lop) {
        this.name = name;
        this.maSV = maSV;
        this.lop = lop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
