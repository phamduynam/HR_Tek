package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<Users> getAllUser();
    public void saveUser(Users user);
    public void deleteUser(Users user);
    public Optional<Users> findUserById(String id);
}
