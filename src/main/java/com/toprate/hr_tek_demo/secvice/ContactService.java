package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.SearchDto;
import com.toprate.hr_tek_demo.model.Contact;

import java.util.List;


public interface ContactService {
    Contact getContactById(String id);

    void saveContact(Contact contact);

    List<Contact> getAllContactTrue();

    void updateContact(Contact contact);

    List<Contact> search(SearchDto searchDto);

    List<Contact> findAllContactForJob(String id);

    Contact getContactByGmail_1(String gmail);

    Contact getContactByGmail_2(String gmail);

    Contact getContactByPhone_1(String phone);

    Contact getContactByPhone_2(String phone);
    public List<Contact> searchSpecification(SearchDto data);
}
