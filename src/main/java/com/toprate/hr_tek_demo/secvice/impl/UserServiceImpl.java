package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.UserRepository;
import com.toprate.hr_tek_demo.repository.specification.JobSpecification;
import com.toprate.hr_tek_demo.repository.specification.UserSpecification;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public void saveUser(Users user) {
        user.setEnable(1);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Users user) {
        user.setEnable(0);
        userRepository.save(user);
    }

    @Override
    public Optional<Users> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Users> searchUserByKeyword(SearchUserDto data) {
        return userRepository.findAll( new UserSpecification().searchUser(data));
    }

    @Override
    public Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userRepository.findAll(pageable);
    }
}
