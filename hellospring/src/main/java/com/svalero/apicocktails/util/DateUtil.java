package com.svalero.apicocktails.util;

public class DateUtil {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static LocalDate format(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}