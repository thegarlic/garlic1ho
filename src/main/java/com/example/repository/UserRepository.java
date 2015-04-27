package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
