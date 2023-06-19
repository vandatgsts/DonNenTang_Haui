package com.example.a48;

import androidx.annotation.NonNull;

public abstract class employee {
    private String id;
    private String name;
    public abstract int Luong();

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nhân Viên:";
    }
}
