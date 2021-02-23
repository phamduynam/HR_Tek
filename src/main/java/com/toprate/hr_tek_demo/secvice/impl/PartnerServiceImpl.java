package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Partner;
import com.toprate.hr_tek_demo.repository.PartnerRepository;
import com.toprate.hr_tek_demo.secvice.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Partner> findAllPartner() {
        return partnerRepository.findAll();
    }
}
