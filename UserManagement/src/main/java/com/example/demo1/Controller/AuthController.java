package com.example.demo1.Controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.Component.JwtUtil;
import com.example.demo1.DTO.LoginRequest;
import com.example.demo1.DTO.RegisterReflection;
import com.example.demo1.Repository.RoleRepository;
import com.example.demo1.Repository.UserRepository;
import com.example.demo1.entity.RoleEntity;
import com.example.demo1.entity.RoleName;
import com.example.demo1.entity.UserEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserRepository userRepo;
	@Autowired
    private RoleRepository roleRepo;
	@Autowired
    private PasswordEncoder encoder;
	@Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterReflection request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        RoleEntity role = roleRepo.findByName(RoleName.ADMIN)
        		.orElseThrow(() -> new RuntimeException("Role USER not found in database"));
        user.getRoles().add(role);
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<UserEntity> opt = userRepo.findByEmail(request.getEmail());
        
        if (opt.isPresent()) {
            UserEntity userdata = opt.get();
            // Only check password match
            if (encoder.matches(request.getPassword(), userdata.getPassword())) {
                String token = jwtUtil.generateToken(request.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}


