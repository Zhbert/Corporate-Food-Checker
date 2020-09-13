/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.repos;

import org.springframework.data.repository.CrudRepository;
import ru.zhbert.corporatefoodchecker.domain.UserChoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public interface UserChoiceRepo extends CrudRepository<UserChoice, Long> {
    Optional<UserChoice> findById(Long id);
}
