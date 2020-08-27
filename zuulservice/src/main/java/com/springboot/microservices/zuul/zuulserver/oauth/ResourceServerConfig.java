package com.springboot.microservices.zuul.zuulserver.oauth;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/oauth/oauth/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/products/list", "/api/items/list", "/api/users/users").permitAll()
        .antMatchers(HttpMethod.GET, "api/products/list/{id}", "/api/items/list/{id}/quantity/{quantity}",
            "/api/users/users/{id}").hasAnyRole("ADMIN", "USER")
        .antMatchers("/api/products/**", "/api/items/**", "api/users/**").hasRole("ADMIN")
        .anyRequest().authenticated();
/*        .antMatchers(HttpMethod.POST, "/api/products/create", "/api/items/create", "api/users/users").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/api/products/edit/{id}", "/api/items/edit/{id}", "api/users/users/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/products/delete/{id}", "/api/items/delete/{id}", "api/users/users/{id}").hasRole("ADMIN");*/
    }

    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(jwtKey);
        return jwtAccessTokenConverter;
    }
}
