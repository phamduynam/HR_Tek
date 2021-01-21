package com.toprate.hr_tek_demo.security;

import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.RoleRepository;
import com.toprate.hr_tek_demo.repository.UserRepository;
import com.toprate.hr_tek_demo.secvice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service(value = "userServiceimp")
public class CustomUserDetailService extends UserServiceImpl implements UserDetailsService {
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


    @Override
    public Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
