package com.example.application.Repositories;

import com.example.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositories extends CrudRepository<User,Long>, JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    List<User> findBycompanyName(String name);
    List<User> findByMail(String name);
}
