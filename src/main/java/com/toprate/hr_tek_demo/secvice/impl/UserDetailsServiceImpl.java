package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Role;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 12:10 04/02/2021
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByGmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("email not found");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getGmail(), "", grantedAuthorities);
    }

}