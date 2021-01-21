package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Override
    public Optional<JobRequirements> findJobById(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public void saveJob(JobRequirements jobRequirements) {
        jobRequirements.setEnable(1);
        jobRepository.save(jobRequirements);
    }

    @Override
    public List<JobRequirements> findAllJob() {
        return jobRepository.findAllJob();
    }

    @Override
    public Page<JobRequirements> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return jobRepository.findAll(pageable);
    }
}
