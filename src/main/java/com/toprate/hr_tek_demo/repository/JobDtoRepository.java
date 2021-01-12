package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.dto.JobDto;
import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobDtoRepository extends JpaRepository<JobDto, String> {

    @Query(nativeQuery = true, name = "findAllJob")
    public List<JobDto> getAllJob();

    @Query(nativeQuery = true, name = "findJobDetail")
    public JobDto getJobById(@Param("id") String id);
}
