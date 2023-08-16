package com.charot.dhwebflux.domain.entity;

import com.charot.dhwebflux.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private String id;

    private String username;

    private String password;

    private Set<String> roles;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return toGrantedAuthority(roles);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> toGrantedAuthority(Set<String> roles) {
        if(roles == null) {
            return new ArrayList<>();
        }
        return roles.stream().map(str -> Role.valueOf(str)).collect(Collectors.toList());
    }
}
