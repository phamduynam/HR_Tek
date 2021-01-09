package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<Users, String> {
    Optional<Users> findByGmail(String gmail);
}
