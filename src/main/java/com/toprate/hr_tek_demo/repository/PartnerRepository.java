package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
}
