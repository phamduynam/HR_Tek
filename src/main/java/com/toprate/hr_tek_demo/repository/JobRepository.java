package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobRequirements, String>, JpaSpecificationExecutor<JobRequirements> {

    @Query(value = "select * from job_recruitment j where j.enable=1", nativeQuery = true)
    public List<JobRequirements> findAllJob();

//    @Query("SELECT j FROM JobRequirements j WHERE j.level = :level"
//            + " OR u.phone LIKE %?1%"
//            + " AND u.role.roleName LIKE %?1%"
//            + " AND u.status LIKE %?1%")
//    public List<JobRequirements> searchFullText(@Param("location") String location,
//                                                @Param("yearExperience") String yearExperience,
//                                                @Param("level") String level,
//                                                @Param("partner") String partner);

    //List<JobRequirements> findByLevelAndYearExperienceAndJobWorkSkills_Skill_SkillIdIn(String level, Float yearExperience, List<Integer> id);
    List<JobRequirements> findByTakeCareTransactionList_Contact_CandidateId(String id);

}
