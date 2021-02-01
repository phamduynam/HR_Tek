package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;

import java.util.List;


public interface ContactPositionService {
    List<ContactPosition> getByContact(Contact contact);

    ContactPosition getById(Integer id);

    void save(ContactPosition contactPosition);

    void saveList(List<ContactPosition> contactPositionList);

    void deleteById(Integer integer);

    void delete(ContactPosition contactPosition);
}
