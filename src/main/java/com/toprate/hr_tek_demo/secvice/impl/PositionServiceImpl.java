package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.repository.PositonRepository;
import com.toprate.hr_tek_demo.secvice.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositonRepository positonRepository;

    @Override
    public List<Position> getAllPosition() {
        return positonRepository.findAll();
    }

    @Override
    public Position getPositionByName(String name) {
        return positonRepository.findByPositionName(name);
    }
}
