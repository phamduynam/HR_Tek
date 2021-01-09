package com.toprate.hr_tek_demo.repository;
import com.toprate.hr_tek_demo.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositonRepository extends JpaRepository<Position,Integer> {
    Optional<Position> findByPositionName(String name);
}
