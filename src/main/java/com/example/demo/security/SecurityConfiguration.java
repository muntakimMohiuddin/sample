package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.Header;

import org.springframework.security.config.Customizer;
import jakarta.websocket.Session;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf->csrf.disable())
            .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/admin/**", "/api/teacher/**","/api/student/**").permitAll()
                .anyRequest().authenticated()
            )
            .headers(headers->headers.frameOptions().sameOrigin())
            .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }
    
    // // @Bean
    // // public DataSource dataSource() {
    // //     return new EmbeddedDatabaseBuilder()
    // //         .setType(EmbeddedDatabaseType.H2)
    // //         .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
    // //         .build();
    // // }

    // @Bean
    // public UserDetailsManager users(DataSource dataSource) {
    //     // UserDetails user = User.withDefaultPasswordEncoder()
    //     //     .username("user")
    //     //     .password("password")
    //     //     .roles("USER")
    //     //     .build();
    //     JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    //     System.out.println("datasource"+dataSource);
    //     // users.createUser(user);
    //     // users.
    //     return users;
    // }
    // // @Bean
    // // public InMemoryUserDetailsManager userDetailsService() {
    // //     UserDetails user = User.withDefaultPasswordEncoder()
    // //         .username("user")
    // //         .password("password")
    // //         .roles("USER")
    // //         .build();
    // //     return new InMemoryUserDetailsManager(user);
    // // }
}
