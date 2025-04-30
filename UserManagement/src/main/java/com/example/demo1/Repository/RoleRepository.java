package com.example.demo1.Repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.entity.RoleName;
import com.example.demo1.entity.*;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName name);
}