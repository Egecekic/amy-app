package com.example.application.Repositories;

import com.example.application.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {
    List<SystemUser> findByEmailAndPassword(String email, String password);

    //List<SystemUser> findById(Long id);
}
