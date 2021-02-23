package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.Status;
import com.toprate.hr_tek_demo.model.TakeCareTransaction;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.repository.StatusRepository;
import com.toprate.hr_tek_demo.repository.TakeCareTransactionRepository;
import com.toprate.hr_tek_demo.secvice.TakeCareTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeCareTransactionServiceImpl implements TakeCareTransactionService {

    @Autowired
    TakeCareTransactionRepository takeCareTransactionRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<TakeCareTransaction> getAll() {
        return takeCareTransactionRepository.findAll();
    }

    @Override
    public TakeCareTransaction save(String candidateId, String jobRecruitmentId) {
        // Tạo status mặc định
        Status status = statusRepository.findById(1).get();
        TakeCareTransaction takeCareTransaction = new TakeCareTransaction(status);
        takeCareTransaction.setContact(contactRepository.findById(candidateId).get());
        takeCareTransaction.setJobRequirements(jobRepository.findById(jobRecruitmentId).get());
        return takeCareTransactionRepository.save(takeCareTransaction);
    }

    @Override
    public TakeCareTransaction getByContactAndJob(String candidateId, String jobRecruitmentId) {
        return takeCareTransactionRepository.findByContact_CandidateIdAndJobRequirements_JobRecruitmentId(candidateId, jobRecruitmentId);
    }

    @Override
    public void delete(TakeCareTransaction takeCareTransaction) {
        takeCareTransactionRepository.delete(takeCareTransaction);
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
