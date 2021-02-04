package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.TakeCareTransaction;

import java.util.List;

public interface TakeCareTransactionService {
    List<TakeCareTransaction> getAll();

    TakeCareTransaction save(String candidateId, String jobRecruitmentId);

    TakeCareTransaction getByContactAndJob(String candidateId, String jobRecruitmentId);

    void delete(TakeCareTransaction takeCareTransaction);

    List<TakeCareTransaction> getAllByContact(Contact contact);

    List<TakeCareTransaction> getAllByJob(JobRequirements job);
}
