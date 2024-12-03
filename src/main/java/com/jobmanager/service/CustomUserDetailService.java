package com.jobmanager.service;

import com.jobmanager.entitiy.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * This service class handles the logic for authorization by implementing
 * the UserDetailsService interface.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = service.findByUsername(username);
        if (acc == null) {
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        return User.builder()
                .username(acc.getUsername())
                .password(acc.getPassword())
                .roles(acc.getRole())
                .build();
    }
}
