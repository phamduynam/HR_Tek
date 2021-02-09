package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.dto.JobRequirementDTO;
import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.dto.SearchJobForContactDto;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<JobRequirements> findJobById(String id);

    void saveJob(JobRequirements jobRequirements);

    List<JobRequirements> findAllJob();

    void updateJob(JobRequirements jobRequirement);

    void deleteJob(JobRequirements job);

    Page<JobRequirements> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    List<JobRequirements> searchJobForContact(Contact contact, SearchJobForContactDto searchJobForContactDto);

    List<JobRequirements> searchJobByKeyword(SearchJobDto searchJobDto);

    List<JobRequirementDTO> searchJobMatchByContact(ContactDto contactDto, SearchJobForContactDto searchJobForContactDto);

    List<JobRequirements> searchJobByContact(ContactDto contactDto);
}
