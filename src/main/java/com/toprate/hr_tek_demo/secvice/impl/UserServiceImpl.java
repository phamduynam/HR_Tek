package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.UserRepository;
import com.toprate.hr_tek_demo.repository.specification.UserSpecification;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // tim kiem nguoi dung bang tu khoa nhap vao
    @Override
    public List<Users> filterRecords(String keyword) {
        if(keyword != null) {
            return userRepository.filterRecords(keyword);
        }
        return userRepository.findAllUser();
    }

    // tim kiem tat ca nguoi dung
    @Override
    public List<Users> getAllUser() {
        return userRepository.findAllUser();
    }

    // luu 1 nguoi dung moi
    @Override
    public void saveUser(Users user) {
        user.setEnable(1);
        userRepository.save(user);
    }

    // xoa 1 nguoi dung khoi he thong
    @Override
    public void deleteUser(Users user) {
        user.setEnable(0);
        userRepository.save(user);
    }

    // tim kiem nguoi dung theo id
    @Override
    public Optional<Users> findUserById(String id) {
        return userRepository.findById(id);
    }

    // tim kiem theo tat ca cac tieu chi
    @Override
    public List<Users> searchUserByKeyword(SearchUserDto data) {
        return userRepository.findAll( new UserSpecification().searchUser(data));
    }

    // phan trang
    @Override
    public Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public Users getUserByGmail(String gmail) {
        return userRepository.findByGmail(gmail).orElse(null);
    }

}
