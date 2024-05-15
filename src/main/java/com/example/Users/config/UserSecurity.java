package com.example.Users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserSecurity {
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        String generatePassword = "{noop}1234";
        String generatePasswordForAdmin = "{noop}5678";
        return new InMemoryUserDetailsManager(
        User
                .withUsername("user")
                .password(generatePassword)
                .roles("USER").
                build(),
        User
                .withUsername("admin")
                .password(generatePasswordForAdmin)
                .roles("ADMIN").
                 build());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public").permitAll()
                        .requestMatchers("/role-user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/role-admin").hasAuthority("ROLE_ADMIN")
                        .anyRequest()
                        .authenticated())
                        .formLogin(Customizer.withDefaults());
                return http.build();
    }
}
