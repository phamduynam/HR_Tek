package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
