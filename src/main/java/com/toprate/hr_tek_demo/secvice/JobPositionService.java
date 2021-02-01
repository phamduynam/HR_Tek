package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPositionService {
    void save(String jobId, int positionId);

    List<JobPosition> findAllByJobId(String id);

    void delete(JobPosition jobPosition);

    void saveAll(List<JobPosition> jobPositionList);

}
