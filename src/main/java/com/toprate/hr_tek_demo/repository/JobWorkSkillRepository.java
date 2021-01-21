package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.JobWorkSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobWorkSkillRepository extends JpaRepository<JobWorkSkill, Integer> {

    @Query(value = "select * from job_work_skill j where j.job_recruitment_id = :id", nativeQuery = true)
    public List<JobWorkSkill> findAllByJobId(@Param("id") String id);
}
