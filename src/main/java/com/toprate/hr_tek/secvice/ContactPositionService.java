package com.toprate.hr_tek.secvice;

import com.toprate.hr_tek.model.ContactPosition;
import com.toprate.hr_tek.repository.ContactPositionRepository;
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
