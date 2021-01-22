package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobPosition;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.repository.JobPositionRepository;
import com.toprate.hr_tek_demo.secvice.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Override
    public void save(String jobId, int positionId) {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setJobRequirements(new JobRequirements(jobId));
        jobPosition.setPosition(new Position(positionId));

        jobPositionRepository.save(jobPosition);

    }

    @Override
    public List<JobPosition> findAllByJobId(String id) {
        return jobPositionRepository.findAllByJobId(id);
    }

    @Override
    public void delete(JobPosition jobPosition) {
        jobPositionRepository.delete(jobPosition);
    }

    @Override
    public void saveAll(List<JobPosition> jobPositionList) {
        jobPositionRepository.saveAll(jobPositionList);
    }


}
