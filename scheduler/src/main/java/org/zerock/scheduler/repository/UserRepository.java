package org.zerock.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.scheduler.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /* OAuth */
    Optional<User> findByEmail(String email);

}
