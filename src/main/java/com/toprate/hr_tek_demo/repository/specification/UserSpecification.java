package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.UserRepository;
import com.toprate.hr_tek_demo.repository.specification.base.BaseQuerySpecification;
import com.toprate.hr_tek_demo.repository.specification.base.Filter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification extends BaseQuerySpecification<Users> {

    @Autowired
    private UserRepository userRepository;

    // tim kiem nguoi dung theo trang thai va quyen
    public Specification<Users> searchUser(SearchUserDto data) {
        if (data.getRole().equals(StringUtils.EMPTY) && data.getStatus().equals(StringUtils.EMPTY)  && data.getKeyword().equals(StringUtils.EMPTY) ) {
            return null;
        }
        return super.initWhere().and(findByStatus(data.getStatus())).and(findByRole(data.getRole()));
    }

    // tim kiem nguoi dung theo trang thai
    private Specification<Users> findByStatus(String status) {
        if (status == null || StringUtils.EMPTY.equals(status)) {
            return null;
        }
        return super.equalsSpecification(Users_.STATUS, status);
    }

    // tim kiem nguoi dung theo quyen
    private Specification<Users> findByRole(String role) {
        if (role == null || StringUtils.EMPTY.equals(role)) {
            return null;
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(role);
        return super.buildJoinSpecification(stringFilter, Users_.role, Role_.roleName);
    }

//    private Specification<Users> filterRecords(String keyword) {
//        if (keyword == null || StringUtils.EMPTY.equals(keyword)) {
//            return null;
//        }
//        if(keyword != null) {
//            return (Specification<Users>) userRepository.filterRecords(keyword);
//        }
//        return (Specification<Users>) userRepository.findAllUser();
//    }
}
