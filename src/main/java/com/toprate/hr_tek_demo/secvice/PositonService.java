package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Position;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PositonService {
    List<Position> getAllPosition();

    Position getPositionByName(String name);
}
