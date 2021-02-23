package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> , JpaSpecificationExecutor<Contact> {
    // Trả về Các Contact đã active
    @Query("SELECT c FROM Contact c WHERE c.isEnable = true")
    Collection<Contact> findByIs_enableTrue();

    Optional<Contact> findByEmail1(String gmail1);

    Optional<Contact> findByEmail2(String gmail2);

    Optional<Contact> findByPhone1(String phone1);

    Optional<Contact> findByPhone2(String phone2);

    @Query(value = "select distinct * from contact a \n" +
            "join contact_position b on b.contact_id = a.candidate_id\n" +
            "join position c on c.position_id = b.position_id\n" +
            "join contact_work_skill e on a.candidate_id = e.contact_id\n" +
            "join skill f on e.skill_id = f.skill_id\n" +
            "where c.position_id in (select d.position_id from job_position d where d.job_recruitment_id = :id)\n" +
            "and f.skill_id in (select g.skill_id from job_work_skill g where g.job_recruitment_id = :id) ", nativeQuery = true)
    List<Contact> findAllContactForJob(@Param("id") String id);
}
