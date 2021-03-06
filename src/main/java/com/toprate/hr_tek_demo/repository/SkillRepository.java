package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {

    Skill findBySkillName(String name);
}
