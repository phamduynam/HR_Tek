package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Location;
import com.toprate.hr_tek_demo.repository.LocationRepository;
import com.toprate.hr_tek_demo.secvice.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Override
    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }
}
