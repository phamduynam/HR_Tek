package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.repository.PositonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    PositonRepository positonRepository;

    public Position getPositonById(int id){
        return positonRepository.findById(id).get();
    }

}
