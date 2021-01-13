package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact getContactById(String id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveAndFlush(contact);
    }

    @Override
    public List<Contact> getAllContactTrue() {
        return (List<Contact>) contactRepository.findByIs_enableTrue();
    }

    @Override
    public Contact convertDtoToContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setCandidateId(contactDto.getCandidateId());
        contact.setCandidateName(contactDto.getCandidateName());
        contact.setAddress(contactDto.getAddress());
        contact.setYearExperience(contactDto.getYearExperience());
        contact.setLinkCv(contactDto.getLinkCv());
        contact.setWorkLocation(contactDto.getWorkLocation());
        contact.setSex(contactDto.getSex());
        contact.setEmail1(contactDto.getEmail1());
        contact.setEmail2(contactDto.getEmail2());
        contact.setPhone1(contactDto.getPhone1());
        contact.setPhone2(contactDto.getPhone2());
        contact.setLevels(contactDto.getLevels());

        return contact;
    }

    @Override
    public ContactDto convertContactToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setCandidateId(contact.getCandidateId());
        contactDto.setCandidateName(contact.getCandidateName());
        contactDto.setAddress(contact.getAddress());
        contactDto.setYearExperience(contact.getYearExperience());
        contactDto.setLinkCv(contact.getLinkCv());
        contactDto.setWorkLocation(contact.getWorkLocation());
        contactDto.setSex(contact.getSex());
        contactDto.setEmail1(contact.getEmail1());
        contactDto.setEmail2(contact.getEmail2());
        contactDto.setPhone1(contact.getPhone1());
        contactDto.setPhone2(contact.getPhone2());
        contactDto.setLevels(contact.getLevels());



        return contactDto;
    }


}
