package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobRequirements;

public interface JobService {
    public JobRequirements findJobById(String id);

    public void saveJob(JobRequirements jobRequirements);
}
