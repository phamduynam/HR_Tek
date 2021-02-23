package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.repository.ContactPositionRepository;
import com.toprate.hr_tek_demo.secvice.ContactPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPositionServiceImpl implements ContactPositionService {

    @Autowired
    ContactPositionRepository contactPositionRepository;

    @Override
    public List<ContactPosition> getByContact(Contact contact) {
        return contactPositionRepository.findAllByContact(contact);
    }

    @Override
    public ContactPosition getById(Integer id) {
        return contactPositionRepository.findById(id).get();
    }

    @Override
    public void save(ContactPosition contactPosition) {
        contactPositionRepository.save(contactPosition);
    }

    @Override
    public void saveList(List<ContactPosition> contactPositionList) {
        contactPositionRepository.saveAll(contactPositionList);
    }

    @Override
    public void deleteById(Integer integer) {
        contactPositionRepository.deleteById(integer);
    }

    @Override
    public void delete(ContactPosition contactPosition) {
        contactPositionRepository.delete(contactPosition);
    }

}
