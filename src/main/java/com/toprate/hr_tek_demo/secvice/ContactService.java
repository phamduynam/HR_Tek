package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.excel.ContactSheet;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.ContactWorkSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact getContactById(String id){
        return contactRepository.findById(id).get();
    }

    public void saveContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteAllContact(){
        contactRepository.deleteAll();
    }

    public Collection<Contact> getAllContact(){
        return  contactRepository.findAll();
    }

}
