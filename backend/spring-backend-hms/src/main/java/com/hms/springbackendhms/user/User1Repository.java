package com.hms.springbackendhms.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User1Repository extends JpaRepository<User1, Integer> {
    Optional<User1> findByEmail(String email);
}
