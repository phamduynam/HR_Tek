package com.toprate.hr_tek.repository;

import com.toprate.hr_tek.model.ContactWorkSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactWorkSkillRepository extends JpaRepository<ContactWorkSkill,String> {
}
