/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhbert.corporatefoodchecker.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
