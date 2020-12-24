package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository  extends JpaRepository<User, String> {
    User findByGmail(String gmail);
}
