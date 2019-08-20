package com.mitrais.cdc.blogmicroservices.services;

import com.mitrais.cdc.blogmicroservices.entity.User;
import com.mitrais.cdc.blogmicroservices.repository.UserRepository;
import com.mitrais.cdc.blogmicroservices.security.jwt.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        return new UserDetails(user);
    }
}
