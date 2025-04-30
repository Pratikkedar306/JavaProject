package com.example.demo1.Controller;

import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo1.Repository.RoleRepository;
import com.example.demo1.Repository.UserRepository;
import com.example.demo1.entity.RoleEntity;
import com.example.demo1.entity.UserEntity;



@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final String ID2 = "id";
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepo;

    // Get all user data
    @GetMapping("/alldata")
    public ResponseEntity<?> getAllData() {
        return ResponseEntity.ok().body(userRepo.findAll());
    }

    // Add new user data
    @PostMapping("/add")
    public ResponseEntity<String> addData(@RequestBody UserEntity request) {
        UserEntity user = new UserEntity();

        // Set basic user details
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUserTask(request.getUserTask());

        // Validate and set roles
        Set<RoleEntity> roleEntities = new HashSet<>();
        for (RoleEntity role : request.getRoles()) {
            RoleEntity validRole = roleRepo.findByName(role.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName()));
            roleEntities.add(validRole);
        }
        user.setRoles(roleEntities);

        // Save the user in the database
        userRepo.save(user);
        
        return new ResponseEntity<>("Data saved successfully.", HttpStatus.CREATED);
    }


    // Update existing user data
    // Update existing user data
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateData(@PathVariable(ID2) int id, @RequestBody UserEntity request) {
        Optional<UserEntity> opt = userRepo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        
        UserEntity user = opt.get();
        user.setName(request.getName());
        user.setRoles(request.getRoles());
        user.setUserTask(request.getUserTask());
        userRepo.save(user);
        return new ResponseEntity<>("Data updated for ID: " + id, HttpStatus.OK);
    }

    // Delete user data
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable(ID2) int id) {
        Optional<UserEntity> opt = userRepo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        userRepo.deleteById(id);
        return new ResponseEntity<>("Data deleted for ID: " + id, HttpStatus.OK);
    }
}
