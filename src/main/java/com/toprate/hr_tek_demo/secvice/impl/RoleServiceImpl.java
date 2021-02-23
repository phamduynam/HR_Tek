package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Role;
import com.toprate.hr_tek_demo.repository.RoleRepository;
import com.toprate.hr_tek_demo.secvice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
