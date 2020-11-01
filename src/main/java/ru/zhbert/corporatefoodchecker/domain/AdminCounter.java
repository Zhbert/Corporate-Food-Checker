/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.corporatefoodchecker.domain;

import java.time.LocalDate;

public class AdminCounter {
    private LocalDate date;
    private String dinnerOne;
    private String dinnerTwo;
    private Integer dinOneCount;
    private Integer dinTwoCount;

    public AdminCounter() {
    }

    public AdminCounter(LocalDate date, String dinnerOne, String dinnerTwo, Integer dinOneCount, Integer dinTwoCount) {
        this.date = date;
        this.dinnerOne = dinnerOne;
        this.dinnerTwo = dinnerTwo;
        this.dinOneCount = dinOneCount;
        this.dinTwoCount = dinTwoCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDinnerOne() {
        return dinnerOne;
    }

    public void setDinnerOne(String dinnerOne) {
        this.dinnerOne = dinnerOne;
    }

    public String getDinnerTwo() {
        return dinnerTwo;
    }

    public void setDinnerTwo(String dinnerTwo) {
        this.dinnerTwo = dinnerTwo;
    }

    public Integer getDinOneCount() {
        return dinOneCount;
    }

    public void setDinOneCount(Integer dinOneCount) {
        this.dinOneCount = dinOneCount;
    }

    public Integer getDinTwoCount() {
        return dinTwoCount;
    }

    public void setDinTwoCount(Integer dinTwoCount) {
        this.dinTwoCount = dinTwoCount;
    }
}
