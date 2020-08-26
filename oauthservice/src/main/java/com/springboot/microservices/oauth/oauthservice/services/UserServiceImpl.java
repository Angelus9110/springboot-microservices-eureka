package com.springboot.microservices.oauth.oauthservice.services;

import com.springboot.microservices.oauth.oauthservice.clients.UserFeignClient;
import com.springboot.microservices.users.servicecommons.models.entity.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userFeignClient.findByUsername(username);

            List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

            logger.info("Authenticated user: " + username);

            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getEnabled(), true, true, true,
                authorities);
        } catch (FeignException e) {
            logger.error("Login error, the user " + username + " doesn't exist in the system");
            throw new UsernameNotFoundException("Login error, the user " + username + " doesn't exist in the system");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userFeignClient.findByUsername(username);
    }

    @Override
    public User update(User user, Long id) {
        return userFeignClient.update(user, id);
    }

}
