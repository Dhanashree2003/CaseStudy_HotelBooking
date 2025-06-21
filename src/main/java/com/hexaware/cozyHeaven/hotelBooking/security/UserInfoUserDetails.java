package com.hexaware.cozyHeaven.hotelBooking.security;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;



public class UserInfoUserDetails implements UserDetails {


    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User user) {
        email=user.getEmail();
        password=user.getPassword();
        authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name().toUpperCase()));

//        authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
//        authorities= Arrays.stream(user.getRole().name())
//                .map(SimpleGrantedAuthority::new) // .map(str -> new SimpleGrantedAuthority(str))
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}