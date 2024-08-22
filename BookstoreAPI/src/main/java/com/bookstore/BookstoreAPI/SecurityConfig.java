package com.bookstore.BookstoreAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig<HttpSecurity> extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    protected void configure(HttpSecurity http) throws Exception {
        http.getClass().hashCode();
    }



    private class JwtTokenProvider {
    }

    private record JwtConfigurer(Object jwtTokenProvider) {
    }
}