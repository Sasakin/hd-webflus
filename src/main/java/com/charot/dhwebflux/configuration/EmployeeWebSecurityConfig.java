package com.charot.dhwebflux.configuration;

import com.charot.dhwebflux.domain.entity.User;
import com.charot.dhwebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class EmployeeWebSecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        Flux<User> userFlux = userRepository.findAll();
        Map<String, UserDetails> userMap = userFlux
                .collectMap(User::getUsername, user -> (UserDetails) user)
                .block();
        return new MapReactiveUserDetailsService(userMap);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
