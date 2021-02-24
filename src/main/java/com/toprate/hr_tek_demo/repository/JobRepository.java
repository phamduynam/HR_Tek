package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobRequirements, String>, JpaSpecificationExecutor<JobRequirements> {

    @Query(value = "select j from JobRequirements j where j.enable=1")
    Page<JobRequirements> findAllJob(Pageable pageable);

    List<JobRequirements> findByTakeCareTransactionList_Contact_CandidateId(String id);

}
