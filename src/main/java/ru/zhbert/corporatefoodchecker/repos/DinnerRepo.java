/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.corporatefoodchecker.repos;

import org.springframework.data.repository.CrudRepository;
import ru.zhbert.corporatefoodchecker.domain.Dinner;

public interface DinnerRepo extends CrudRepository<Dinner, Long> {
    Dinner findById(Integer id);
}
