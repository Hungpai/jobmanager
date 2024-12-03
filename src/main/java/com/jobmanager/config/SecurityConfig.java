package com.jobmanager.config;

import com.jobmanager.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class handles the authorization logic. It defines
 * the security filter chain on HTTP requests and applies
 * password encoding and authorization to the authentication
 * manager.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService uds;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * This functions defien the security filter chain. All login and registration page requests
     * are permitted. All other requests need to be authorized via a form.
     * Also enabled a logout request.
     *
     * @param http HttpSecurity object
     * @return the http object with the applied filter chain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // permit all login and registration as well as resources
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/css/*", "/images/*", "/login", "/account/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                // all other requests need authorization
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll())
                // enable logout requests
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    /**
     * Applies authorization via UserDetailService and password encoder
     * to the authentication manager.
     *
     * @param http HttpSecurity object
     * @return HttpSecurity object with applied password encoder and UserDetailService
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(uds)
                .passwordEncoder(encoder);
        return authenticationManagerBuilder.build();
    }
}
