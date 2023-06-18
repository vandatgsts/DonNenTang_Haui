package com.example.quanlygiaotrinh.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTimeInPhone {
    // Lấy ngày tháng hiện tại
    public static String GetDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Định dạng ngày tháng
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }
}
