package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {

    @Query(value = "select * from job_position j where j.job_recruitment_id = :id ", nativeQuery = true)
    public List<JobPosition> findAllByJobId(@Param("id") String id);
}
