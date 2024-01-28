package com.example.SpringDemoProject.services;

import com.example.SpringDemoProject.config.CustomUserDetails;
import com.example.SpringDemoProject.models.User;
import com.example.SpringDemoProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User findByUsernameEquals(String username) {
        Optional<User> user = userRepository.findByUsernameEquals(username);
        return user.orElse(null);
    }

    public void save(User user, boolean isNew) {
        if (isNew) {
            passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameEquals(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
