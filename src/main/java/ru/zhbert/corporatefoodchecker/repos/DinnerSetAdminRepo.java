/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.repos;

import org.springframework.data.repository.CrudRepository;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public interface DinnerSetAdminRepo extends CrudRepository<DinnerSetAdmin, Long> {
    Optional<DinnerSetAdmin> findById(Long id);
    ArrayList findByDinnerDate(LocalDate localDate);
    ArrayList findByDinnerOrDinnerTwo(Dinner dinner, Dinner dinnerTwo);
}
