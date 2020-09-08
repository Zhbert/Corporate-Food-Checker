/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.repos;

import antlr.collections.List;
import org.apache.tomcat.jni.Local;
import org.hibernate.persister.entity.Loadable;
import org.springframework.data.repository.CrudRepository;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public interface DinnerSetAdminRepo extends CrudRepository<DinnerSetAdmin, Long> {
    Optional<DinnerSetAdmin> findById(Long id);
    ArrayList findByDinnerDate(LocalDate localDate);
}
