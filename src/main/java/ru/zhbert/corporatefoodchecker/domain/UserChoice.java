/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Table(name = "user_choices")
public class UserChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_id")
    private Dinner dinner;

    private LocalDate date;

    @JoinColumn(name = "day_of_week")
    private String dayOfWeek;

    public UserChoice() {
    }

    public UserChoice(Long id, User user, Dinner dinner, LocalDate date) {
        this.id = id;
        this.user = user;
        this.dinner = dinner;
        this.date = date;
        this.dayOfWeek = getDayOfWeek(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dinner getDinner() {
        return dinner;
    }

    public void setDinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dayOfWeek = getDayOfWeek(date);
    }

    public boolean isExists() {
        if(this.dinner != null) {
            return true;
        }
        return false;
    }

    private String getDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek.toString()" + dayOfWeek.toString());
        Locale localeRu = new Locale("ru", "RU");

        String stringDate = dayOfWeek.getDisplayName(TextStyle.FULL,localeRu);

        return stringDate.substring(0, 1).toUpperCase() + stringDate.substring(1);
    }
}
