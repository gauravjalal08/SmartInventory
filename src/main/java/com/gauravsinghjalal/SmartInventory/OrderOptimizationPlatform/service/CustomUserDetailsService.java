package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.User;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositry userRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositry.findByUsername(username)
                .orElseThrow(()-> new   UsernameNotFoundException("User not found"));
          return new org.springframework.security.core.userdetails.User(
                  user.getUsername(),
                  user.getPassword(),
                  user.getRoles().stream()
                          .map(role -> new SimpleGrantedAuthority(role.getName()))
                          .collect(Collectors.toList())

          );
    }
}
