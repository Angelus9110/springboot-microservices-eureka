package com.springboot.microservices.oauth.oauthservice.security.event;

import com.springboot.microservices.oauth.oauthservice.services.UserService;
import com.springboot.microservices.users.servicecommons.models.entity.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger logger = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String message = "Success login " + user.getUsername();
        System.out.println(message);
        logger.info(message);

        User userLoging = userService.findByUsername(authentication.getName());
        if(userLoging.getLoginTryNumber() != null && userLoging.getLoginTryNumber() >0){
            userLoging.setLoginTryNumber(0);
            userService.update(userLoging, userLoging.getId());
        }

    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String message = "Login error " + e.getMessage();
        logger.error(message);
        System.out.println(message);

        try{
            User user = userService.findByUsername(authentication.getName());
            if (user.getLoginTryNumber()==null){
                user.setLoginTryNumber(0);
            }

            logger.info("Current tries is: " + user.getLoginTryNumber());
            user.setLoginTryNumber(user.getLoginTryNumber()+1);

            if(user.getLoginTryNumber()>=3){
                logger.error("User " + user.getUsername() + " disabled by max intents");
                user.setEnabled(false);
            }

            userService.update(user, user.getId());

        }catch (FeignException feignException){
            logger.error("The user " + authentication.getName() + " doesn't exist in the system");
        }
    }
}
