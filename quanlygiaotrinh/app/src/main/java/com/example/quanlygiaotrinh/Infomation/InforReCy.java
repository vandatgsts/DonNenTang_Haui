package com.example.quanlygiaotrinh.Infomation;

public class InforReCy {
    String nameLeader;
    String nameParticipants;
    String time;

    public InforReCy(String nameLeader, String nameParticipants, String time) {
        this.nameLeader = nameLeader;
        this.nameParticipants = nameParticipants;
        this.time = time;
    }

    public String getNameLeader() {
        return nameLeader;
    }

    public void setNameLeader(String nameLeader) {
        this.nameLeader = nameLeader;
    }

    public String getNameParticipants() {
        return nameParticipants;
    }

    public void setNameParticipants(String nameParticipants) {
        this.nameParticipants = nameParticipants;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}