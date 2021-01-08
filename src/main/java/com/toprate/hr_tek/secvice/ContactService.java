package com.toprate.hr_tek.secvice;

import com.toprate.hr_tek.model.Contact;
import com.toprate.hr_tek.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact getContactById(String id){
        return contactRepository.findById(id).get();
    }

    public void saveContact(Contact contact){
        contactRepository.saveAndFlush(contact);
    }

    public Contact getContactByGmail_1(String gmail){
        return contactRepository.findByEmail1(gmail).get();
    }

    public Contact getContactByGmail_2(String gmail){
        return contactRepository.findByEmail2(gmail).get();
    }

    public Contact getContactPhone_1(String phone){
        return contactRepository.findByPhone1(phone).get();
    }

    public Contact getContactPhone_2(String phone){
        return contactRepository.findByPhone2(phone).get();
    }

    public void deleteAllContact(){
        contactRepository.deleteAll();
    }

    public Collection<Contact> getAllContact(){
        return  contactRepository.findAll();
    }

    public Collection<Contact> getAllContactTrue(){
        return contactRepository.findByIs_enableTrue();
    }

}
