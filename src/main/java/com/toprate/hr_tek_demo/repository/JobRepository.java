package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobRequirements, String> {

    @Query(value = "select * from job_recruitment j where j.enable=1", nativeQuery = true)
    public List<JobRequirements> findAllJob();
}
