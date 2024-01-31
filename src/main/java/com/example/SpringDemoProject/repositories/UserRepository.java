package com.example.SpringDemoProject.repositories;

import com.example.SpringDemoProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameEquals(String username);

    User findByActivationCode(String code);
}
