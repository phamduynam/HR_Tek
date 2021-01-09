package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.repository.ContactPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactPositionService  {
    @Autowired
    private ContactPositionRepository contactPositionRepository;

    public void saveContactPosition(ContactPosition contactPosition){
        contactPositionRepository.save(contactPosition);
    }

}
