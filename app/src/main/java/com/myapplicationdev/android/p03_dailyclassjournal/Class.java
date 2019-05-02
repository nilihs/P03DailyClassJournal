package com.myapplicationdev.android.p03_dailyclassjournal;

import java.io.Serializable;

public class Class implements Serializable {

    private String week;
    private String dailyGrade;

    public Class(String week, String dailyGrade) {
        this.week = week;
        this.dailyGrade = dailyGrade;
    }

    public String getWeek() {
        return week;
    }

    public String getDailyGrade() {
        return dailyGrade;
    }
}
