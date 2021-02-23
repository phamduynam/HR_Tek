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
        if (data.getRole().equals(StringUtils.EMPTY) && data.getStatus().equals(StringUtils.EMPTY) && data.getKeyword().equals(StringUtils.EMPTY)) {
            return null;
        }
        return super.initWhere().and(findByStatus(data.getStatus())).and(findByRole(data.getRole())).or(findByName(data.getKeyword())).or(findByPhone(data.getKeyword())).and(findByMail(data.getKeyword()));
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

    // tim kiem nguoi dung theo ten
    private Specification<Users> findByName(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return null;
        }
        return super.likeSpecification(Users_.NAME, name);
    }

    // tim kiem nguoi dung theo email
    private Specification<Users> findByMail(String mail) {
        if (mail == null || StringUtils.EMPTY.equals(mail)) {
            return null;
        }
        return super.likeSpecification(Users_.GMAIL, mail);
    }

    // Tim kiem nguoi dung theo sdt
    private Specification<Users> findByPhone(String phone) {
        if (phone == null || StringUtils.EMPTY.equals(phone)) {
            return null;
        }
        return super.likeSpecification(Users_.PHONE, phone);
    }

    // Tim kiem nguoi dung theo trang thai
    private Specification<Users> findLikeByStatus(String status) {
        if (status == null || StringUtils.EMPTY.equals(status)) {
            return null;
        }
        return super.likeSpecification(Users_.STATUS, status);
    }

}
