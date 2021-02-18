package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.ExceptionLog;
import com.toprate.hr_tek_demo.repository.ExceptionLogRepository;
import com.toprate.hr_tek_demo.secvice.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    ExceptionLogRepository exceptionLogRepository;

    @Override
    public void insert(ExceptionLog exceptionLog) {
        exceptionLogRepository.save(exceptionLog);
    }

    @Override
    public List<ExceptionLog> getAll() {
        return exceptionLogRepository.findAll();
    }
}
