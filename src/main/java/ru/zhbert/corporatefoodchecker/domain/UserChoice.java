/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_choices")
public class UserChoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_id")
    private Dinner dinner;

    private LocalDate date;

    public UserChoice() {
    }

    public UserChoice(Long id, User user, Dinner dinner, LocalDate date) {
        this.id = id;
        this.user = user;
        this.dinner = dinner;
        this.date = date;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
