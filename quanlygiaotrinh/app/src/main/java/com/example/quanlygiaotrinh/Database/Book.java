package com.example.quanlygiaotrinh.Database;

import java.util.ArrayList;

public class Book {
    int id=0;
    String name;
    String code;
    int credits;
    ArrayList<String> authors;
    String time;
    int status=1; // 1. Chờ xác nhận 2.Đã duyệt, 3.Hoàn thành
    int ans=0; // 0.không đạt/Hủy .1. Đạt
    String ideal="";

    public String getIdeal() {
        return ideal;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Book(String name, String code, int credits, ArrayList<String> authors, String time, int status, int ans, String ideal) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.authors = authors;
        this.time = time;
        this.status = status;
        this.ans = ans;
        this.ideal = ideal;
    }

    public Book(int id, String name, String code, int credits, ArrayList<String> authors, String time, int status, int ans, String ideal) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.authors = authors;
        this.time = time;
        this.status = status;
        this.ans = ans;
        this.ideal = ideal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

