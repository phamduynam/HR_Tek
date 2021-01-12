package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobRequirements, String> {
}
