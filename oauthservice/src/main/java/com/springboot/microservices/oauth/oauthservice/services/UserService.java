package com.springboot.microservices.oauth.oauthservice.services;

import com.springboot.microservices.users.servicecommons.models.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    public User findByUsername(String username);

    public User update(User user, Long id);
}
