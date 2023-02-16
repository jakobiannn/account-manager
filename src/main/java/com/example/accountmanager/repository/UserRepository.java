package com.example.accountmanager.repository;


import com.example.accountmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
    void deleteUserByLogin(String login);
    boolean existsUserByLogin(String login);
}