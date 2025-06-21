package com.hexaware.cozyHeaven.hotelBooking.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.repository.UserInfoRepository;


@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }

        return new UserInfoUserDetails(user);
    }
}
