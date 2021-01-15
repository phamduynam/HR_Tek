package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.repository.PositonRepository;
import com.toprate.hr_tek_demo.secvice.PositonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositonService {
    @Autowired
    private PositonRepository positonRepository;
    @Override
    public List<Position> getAllPosition() {
        return positonRepository.findAll();
    }
}
