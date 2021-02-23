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
            return super.initWhere();
        }
        return findByStatus(data.getStatus()).and(findByRole(data.getRole()).and(findByName(data.getKeyword()).or(findByPhone(data.getKeyword())).or(findByMail(data.getKeyword())).or(findLikeByStatus(data.getKeyword())).or(findLikeByRole(data.getKeyword()))));
    }

    // tim kiem nguoi dung theo trang thai
    private Specification<Users> findByStatus(String status) {
        if (status == null || StringUtils.EMPTY.equals(status)) {
            return super.initWhere();
        }
        return super.equalsSpecification(Users_.STATUS, status);
    }

    // tim kiem nguoi dung theo quyen
    private Specification<Users> findByRole(String role) {
        if (role == null || StringUtils.EMPTY.equals(role)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(role);
        return super.buildJoinSpecification(stringFilter, Users_.role, Role_.roleName);
    }

    // tim kiem nguoi dung theo ten
    private Specification<Users> findByName(String name) {
        if (name == null || StringUtils.EMPTY.equals(name)) {
            return super.initWhere();
        }
        return super.likeSpecification(Users_.NAME, name);
    }

    // tim kiem nguoi dung theo email
    private Specification<Users> findByMail(String mail) {
        if (mail == null || StringUtils.EMPTY.equals(mail)) {
            return super.initWhere();
        }
        return super.likeSpecification(Users_.GMAIL, mail);
    }

    // Tim kiem nguoi dung theo sdt
    private Specification<Users> findByPhone(String phone) {
        if (phone == null || StringUtils.EMPTY.equals(phone)) {
            return super.initWhere();
        }
        return super.likeSpecification(Users_.PHONE, phone);
    }

    // Tim kiem nguoi dung theo trang thai su dung like
    private Specification<Users> findLikeByStatus(String status) {
        if (status == null || StringUtils.EMPTY.equals(status)) {
            return super.initWhere();
        }
        return super.likeSpecification(Users_.STATUS, status);
    }

    // tim kiem nguoi dung theo quyen su dung like
    private Specification<Users> findLikeByRole(String role) {
        if (role == null || StringUtils.EMPTY.equals(role)) {
            return super.initWhere();
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setLike(role);
        return super.buildJoinSpecification(stringFilter, Users_.role, Role_.roleName);
    }

}
