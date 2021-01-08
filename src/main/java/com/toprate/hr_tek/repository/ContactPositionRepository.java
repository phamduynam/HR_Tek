package com.toprate.hr_tek.repository;

import com.toprate.hr_tek.model.ContactPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPositionRepository extends JpaRepository<ContactPosition,String> {
}
