package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPositionService {
    public void save(String jobId, int positionId);

    public List<JobPosition> findAllByJobId(String id);

    public void delete(JobPosition jobPosition);

    public void saveAll(List<JobPosition> jobPositionList);

}
