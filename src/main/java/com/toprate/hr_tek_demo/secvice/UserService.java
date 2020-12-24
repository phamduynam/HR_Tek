package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.User;
import com.toprate.hr_tek_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public  User getUserById(String id){
        return userRepository.getOne(id);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public User getUserByGmail(String gmail){
        return userRepository.findByGmail(gmail);
    }

}
