package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.TakeCareTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeCareTransactionRepository extends JpaRepository<TakeCareTransaction,Integer> {

    List<TakeCareTransaction> findAllByContact(Contact contact);

    List<TakeCareTransaction> findAllByJobRequirements(JobRequirements job);

    TakeCareTransaction findByContact_CandidateIdAndJobRequirements_JobRecruitmentId(String candidateId, String jobRecruitmentId);

}
