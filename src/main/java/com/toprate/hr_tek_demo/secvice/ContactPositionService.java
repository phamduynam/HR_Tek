package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;

import java.util.List;


public interface ContactPositionService {
    public List<ContactPosition> getByContact(Contact contact);

    public ContactPosition getById(Integer id);

    public void save(ContactPosition contactPosition);

    public void saveList(List<ContactPosition> contactPositionList);

    public void deleteById(Integer integer);

    public void delete(ContactPosition contactPosition);
}
