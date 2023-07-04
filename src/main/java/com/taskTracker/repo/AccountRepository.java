package com.taskTracker.repo;


import com.taskTracker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);
    Optional<Account> getByEmail(String email);
}
