package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.TakeCareTransaction;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.JobRepository;
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

    @Override
    public List<TakeCareTransaction> getAll() {
        return takeCareTransactionRepository.findAll();
    }

    @Override
    public TakeCareTransaction save(String candidateId, String jobRecruitmentId) {
        TakeCareTransaction takeCareTransaction = new TakeCareTransaction();
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
