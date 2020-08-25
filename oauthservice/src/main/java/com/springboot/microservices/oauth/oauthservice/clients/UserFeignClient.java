package com.springboot.microservices.oauth.oauthservice.clients;

import com.springboot.microservices.users.servicecommons.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service")
public interface UserFeignClient {
    
    @GetMapping("/users/search/search-username")
    public User findByUsername(@RequestParam String username);
}
