package com.toprate.hr_tek.secvice;

import com.toprate.hr_tek.model.Users;
import com.toprate.hr_tek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    public Users getUserByGmail(String gmail) {
        return userRepository.findByGmail(gmail).get();
    }
}
