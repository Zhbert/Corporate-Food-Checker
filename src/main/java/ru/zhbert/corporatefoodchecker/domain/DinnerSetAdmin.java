/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DinnerSetAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_id")
    private Dinner dinner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_two_id")
    private Dinner dinnerTwo;

    public Dinner getDinnerTwo() {
        return dinnerTwo;
    }

    public void setDinnerTwo(Dinner dinnerTwo) {
        this.dinnerTwo = dinnerTwo;
    }

    private LocalDate dinnerDate;

    public DinnerSetAdmin() {
    }

    public DinnerSetAdmin(Long id, Dinner dinner, Dinner dinnerTwo, LocalDate dinnerDate) {
        this.id = id;
        this.dinner = dinner;
        this.dinnerTwo = dinnerTwo;
        this.dinnerDate = dinnerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dinner getDinner() {
        return dinner;
    }

    public void setDinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public LocalDate getDinnerDate() {
        return dinnerDate;
    }

    public void setDinnerDate(LocalDate dinnerDate) {
        this.dinnerDate = dinnerDate;
    }
}
