package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Status;
import com.toprate.hr_tek_demo.repository.StatusRepository;
import com.toprate.hr_tek_demo.secvice.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }
}
