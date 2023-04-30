package com.spring.security.test.SpringSecurityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder  passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails normalUser= User.withUsername("rohit").password(passwordEncoder().encode("password")).roles("NORMAL").build();

        UserDetails adminUser = User.withUsername("Rohit2").password(passwordEncoder().encode("Rohit30@")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/home/admin")
//                .hasRole("ADMIN")
//                .requestMatchers("/home/normal")
//                .hasRole("NORMAL").
                .requestMatchers("/home/public").
                permitAll().
                anyRequest().
                authenticated()
                .and().
                formLogin();
        return httpSecurity.build();

    }
}
