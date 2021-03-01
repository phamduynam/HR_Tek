package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> getAllUser();

    void saveUser(Users user);

    void deleteUser(Users user);

    Optional<Users> findUserById(String id);

    Page<Users> searchUserByKeyword(SearchUserDto data, int pageNo, int pageSize, String sortField, String sortDirection) ;

    Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Users getUserByGmail(String gmail);

    List<Users> getAllByRole(String name);
}
