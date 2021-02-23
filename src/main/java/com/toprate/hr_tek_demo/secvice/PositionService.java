package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Position;

import java.util.List;


public interface PositionService {

    List<Position> getAllPosition();

    Position getPositionByName(String name);
}
