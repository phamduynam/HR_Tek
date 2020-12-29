package com.toprate.hr_tek_demo.security;


import com.toprate.hr_tek_demo.model.Role;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.RoleRepository;
import com.toprate.hr_tek_demo.repository.UserRepository;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.hibernate.service.UnknownServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userServiceimp")
public class CustomUserDetailService extends UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String Gmail) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByGmail(Gmail);

        optionalUsers.orElseThrow(
                () -> new UsernameNotFoundException("Gmail not found")
        );

        return optionalUsers.map(CustomUserDeatails::new).get();
    }


}
