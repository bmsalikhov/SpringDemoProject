package com.example.SpringDemoProject.services;

import com.example.SpringDemoProject.config.CustomUserDetails;
import com.example.SpringDemoProject.models.Role;
import com.example.SpringDemoProject.models.User;
import com.example.SpringDemoProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

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

    public boolean addUser(User user) {
        User userFromDb = findByUsernameEquals(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        save(user, true);

        if (!ObjectUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                   "Hello, %s! \n" +
                   "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        save(user, false);

        return true;
    }
}
