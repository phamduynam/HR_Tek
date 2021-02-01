package com.toprate.hr_tek_demo.repository.specification;

import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.specification.base.BaseQuerySpecification;
import com.toprate.hr_tek_demo.repository.specification.base.Filter;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification extends BaseQuerySpecification<Users> {

    public Specification<Users> searchUser(SearchUserDto data) {
        if (data.getRole().equals(StringUtils.EMPTY) && data.getStatus().equals(StringUtils.EMPTY) ) {
            return null;
        }
        return super.initWhere().and(findByRole(data.getRole()).and(findByStatus(data.getStatus())));
    }

    private Specification<Users> findByStatus(String status) {
        if (status == null || StringUtils.EMPTY.equals(status)) {
            return null;
        }
        return super.equalsSpecification(Users_.STATUS, status);
    }

    private Specification<Users> findByRole(String role) {
        if (role == null || StringUtils.EMPTY.equals(role)) {
            return null;
        }
        Filter<String> stringFilter = new Filter<>();
        stringFilter.setEquals(role);
        return super.buildJoinSpecification(stringFilter, Users_.role, Role_.roleName);
    }

}
