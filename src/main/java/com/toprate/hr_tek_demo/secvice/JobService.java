package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.Users;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public Optional<JobRequirements> findJobById(String id);

    public void saveJob(JobRequirements jobRequirements);

    public List<JobRequirements> findAllJob();

    public void updateJob(JobRequirements jobRequirement);

    public void deleteJob(JobRequirements job);

    Page<JobRequirements> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    public List<JobRequirements> searchJobByKeyword(SearchJobDto searchJobDto);
}
