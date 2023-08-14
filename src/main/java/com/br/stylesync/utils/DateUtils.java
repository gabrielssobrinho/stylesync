package com.br.stylesync.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date parse(String date) {
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format");
        }
    }
}
