package com.example.a46;

public abstract class Employee {
    private String id;
    private String name;
    public abstract double luong();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tên: "+this.name+ "  Mã:"+ this.id;
    }
}
