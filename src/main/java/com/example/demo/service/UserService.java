 package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository = null;

    /**
     * Registers a new user.
     * @param user User object to save.
     * @return Saved User object.
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Find a user by username.
     * @param username The username to search for.
     * @return An Optional containing the User if found.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Load user by username for authentication.
     * @param username The username to search for.
     * @return UserDetails object for authentication.
     * @throws UsernameNotFoundException If user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // ✅ Should be encoded in real applications
                .roles("USER") // ✅ Changed `.authorities("USER")` to `.roles("USER")` for better role management
                .build();
    }
}
