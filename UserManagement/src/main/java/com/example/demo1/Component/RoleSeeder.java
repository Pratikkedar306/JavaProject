package com.example.demo1.Component;

import org.springframework.boot.CommandLineRunner;


import org.springframework.stereotype.Component;

import com.example.demo1.Repository.RoleRepository;
import com.example.demo1.entity.RoleEntity;
import com.example.demo1.entity.RoleName;

import org.springframework.beans.factory.annotation.Autowired;


@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void run(String... args) {
        try {
            if (roleRepo.findByName(RoleName.ADMIN).isEmpty()) {
                RoleEntity admin = new RoleEntity();
                admin.setName(RoleName.ADMIN);
                roleRepo.save(admin);
            }
            if (roleRepo.findByName(RoleName.MANAGER).isEmpty()) {
                RoleEntity manager = new RoleEntity();
                manager.setName(RoleName.MANAGER);
                roleRepo.save(manager);
            }
            if (roleRepo.findByName(RoleName.USER).isEmpty()) {
                RoleEntity user = new RoleEntity();
                user.setName(RoleName.USER);
                roleRepo.save(user);
            }
            System.out.println("✅ Roles initialized successfully!");

        } catch (Exception e) {
            System.err.println("🔥 Error while seeding roles: " + e.getMessage());
        }
    }
}
