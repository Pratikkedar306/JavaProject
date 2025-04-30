package com.example.demo1.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.entity.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
