/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.corporatefoodchecker.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dinners")
public class Dinner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Название обеда не должно быть пустым!")
    @Length(max = 255, message = "Название слишком длинное!")
    private String name;

    @NotBlank(message = "Описание обеда не должно быть пустым!")
    @Length(max = 2048, message = "Описание слишком длинное!")
    private String description;

    public Dinner() {
    }

    public Dinner(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
