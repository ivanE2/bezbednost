package com.main.app.config;

import com.main.app.domain.model.User;
import com.main.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findOneByEmail(username);
        org.springframework.security.core.userdetails.User.UserBuilder b = null;

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("NOT FOUND");
        }

        b = org.springframework.security.core.userdetails.User.withUsername(username);
        b.authorities(user.get().getRole().toString());
        b.password(user.get().getPassword());

        return b.build();
    }
}