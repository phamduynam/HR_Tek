package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {

    @Query(value = "SELECT * FROM user u WHERE u.enable = 1", nativeQuery = true)
    public List<Users> findAllUser();

}
