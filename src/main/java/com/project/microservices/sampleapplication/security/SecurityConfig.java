//package com.project.microservices.sampleapplication.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfig {
//
//    public BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests( authorize -> authorize
//                .requestMatchers( "/books", "/v3/*", "/swagger-ui/**")
//
//                .permitAll()
////                .hasRole("USER")
//                .anyRequest()
//                .authenticated()
//        );
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public UserDetailsService user(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(bCryptPasswordEncoder.encode("admin-pass"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder.encode("user-pass"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
//}
