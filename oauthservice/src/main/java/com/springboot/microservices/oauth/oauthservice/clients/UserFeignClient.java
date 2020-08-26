package com.springboot.microservices.oauth.oauthservice.clients;

import com.springboot.microservices.users.servicecommons.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="user-service")
public interface UserFeignClient {
    
    @GetMapping("/users/search/search-username")
    public User findByUsername(@RequestParam String username);
    
    @PutMapping("/users/{id}")
    public User update(@RequestBody User user, @PathVariable Long id);
}
