package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users>, PagingAndSortingRepository<Users, String> {

    Optional<Users> findByGmail(String gmail);

    @Query(value = "SELECT u FROM Users u WHERE u.enable = 1")
    Page<Users> findAllUser(Pageable pageable);

}
