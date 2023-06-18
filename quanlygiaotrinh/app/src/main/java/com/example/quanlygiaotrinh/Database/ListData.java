package com.example.quanlygiaotrinh.Database;

import com.example.quanlygiaotrinh.Infomation.InforReCy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ListData {
    public static ArrayList<InforReCy> listInfor=new ArrayList<>();
    public static ArrayList<String> listTeacher=new ArrayList<>();
    public static ArrayList<Book> listBook=new ArrayList<>();

    public static void CreateData(){
        TextAdd();
        AddTeacher();
    }
    public static void TextAdd(){
        listInfor.add(new InforReCy("Nguyen Van Dat","Nguyen Van Dat","2022-2023"));
        listInfor.add(new InforReCy("Nguyen Van Dat","Nguyen Van Dat","2022-2023"));
        listInfor.add(new InforReCy("Nguyen Van Dat","Nguyen Van Dat","2022-2023"));
        listInfor.add(new InforReCy("Nguyen Van Dat","Nguyen Van Dat","2022-2023"));

    }
    public static void AddTeacher(){
        listTeacher.add("Nguyen Van Dat");
        listTeacher.add("Nguyen Khac Thang");
        listTeacher.add("Ngo the tai");
        listTeacher.add("Nguyen Van Phu");
        listTeacher.add("Duc Quang");
    }

}
