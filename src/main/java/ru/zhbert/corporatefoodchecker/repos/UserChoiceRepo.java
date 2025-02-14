/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.corporatefoodchecker.repos;

import org.springframework.data.repository.CrudRepository;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.domain.UserChoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public interface UserChoiceRepo extends CrudRepository<UserChoice, Long> {
    Optional<UserChoice> findById(Long id);
    ArrayList<UserChoice> findByDate(LocalDate date);
    ArrayList<UserChoice> findByDateAndUser(LocalDate date, User user);
}
