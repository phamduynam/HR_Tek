package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactService {
    public Contact getContactById(String id);

    public void saveContact(Contact contact);

    public List<Contact> getAllContactTrue();

    public Contact convertDtoToContact(ContactDto contactDto);

    public ContactDto convertContactToDto(Contact contact);
}
