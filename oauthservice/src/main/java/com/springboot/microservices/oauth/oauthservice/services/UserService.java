package com.springboot.microservices.oauth.oauthservice.services;

import com.springboot.microservices.users.servicecommons.models.entity.User;

public interface UserService {

    public User findByUsername(String username);
}
