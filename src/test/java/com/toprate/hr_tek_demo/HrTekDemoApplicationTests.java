package com.toprate.hr_tek_demo;

import com.toprate.hr_tek_demo.dto.SearchDto;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HrTekDemoApplicationTests {
    @Autowired
    private JobRepository jobRepository;

    @Test
    void contextLoads() {
        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        List<JobRequirements> users = jobRepository.findByLevelAndYearExperienceAndJobWorkSkills_Skill_SkillIdIn("DEV", null, ids);
//        users.forEach(contact -> System.out.println(contact.getJobTitle()));
    }

}
