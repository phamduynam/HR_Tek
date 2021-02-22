package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {

    Optional<Users> findByGmail(String gmail);

    @Query(value = "SELECT * FROM user u WHERE u.enable = 1", nativeQuery = true)
    List<Users> findAllUser();

    @Query("SELECT u from Users u WHERE u.name LIKE %?1%"
            + " OR u.gmail LIKE %?1%"
            + " OR u.phone LIKE %?1%"
            + " OR u.status LIKE %?1%"
            + " OR u.role.roleName LIKE %?1%")
    public List<Users> filterRecords(String keyword);

}
