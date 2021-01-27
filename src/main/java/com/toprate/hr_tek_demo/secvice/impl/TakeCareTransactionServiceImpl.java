package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.TakeCareTransaction;
import com.toprate.hr_tek_demo.repository.TakeCareTransactionRepository;
import com.toprate.hr_tek_demo.secvice.TakeCareTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TakeCareTransactionServiceImpl implements TakeCareTransactionService {

    @Autowired
    TakeCareTransactionRepository takeCareTransactionRepository;

    @Override
    public List<TakeCareTransaction> getAll() {
        return takeCareTransactionRepository.findAll();
    }

    @Override
    public List<TakeCareTransaction> getAllByContact(Contact contact) {
        return takeCareTransactionRepository.findAllByContact(contact);
    }

    @Override
    public List<TakeCareTransaction> getAllByJob(JobRequirements job) {
        return takeCareTransactionRepository.findAllByJobRequirements(job);
    }
}
