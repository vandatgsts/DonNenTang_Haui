package com.example.quanlygiaotrinh.info_Screen;

import java.util.ArrayList;
import java.util.List;

public class Procress {
    int id;
    String name;
    String content;
    String timeStart;

    List<String> timeEdit=new ArrayList<>();
    int status=1;// 1. chưa làm, 2. đã nhận , 3 đã xong

    int bookId;

    public Procress(int id, String name, String content, String timeStart, List<String> timeEdit, int status, int bookId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.timeStart = timeStart;
        this.timeEdit = timeEdit;
        this.status = status;
        this.bookId = bookId;
    }

    public Procress(String name, String content, String timeStart, List<String> timeEdit, int status, int bookId) {
        this.name = name;
        this.content = content;
        this.timeStart = timeStart;
        this.timeEdit = timeEdit;
        this.status = status;
        this.bookId = bookId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public List<String> getTimeEdit() {
        return timeEdit;
    }

    public void setTimeEdit(List<String> timeEdit) {
        this.timeEdit = timeEdit;
    }
}
