package com.example.demo1.Configuration;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo1.Repository.UserRepository;
import com.example.demo1.entity.UserEntity;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> opt = userRepository.findByEmail(username);
        UserEntity userEntity = opt.get();

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        String[] roles = userEntity.getRoles().stream()
                                   .map(role -> role.getName().toString()) 
                                   .toArray(String[]::new);

        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())  
                .roles(roles)
                .build();
    }
}
