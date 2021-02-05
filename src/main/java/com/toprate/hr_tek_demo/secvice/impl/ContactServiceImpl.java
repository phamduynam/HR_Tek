package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.SearchDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.specification.ContactSpecification;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactPositionServiceImpl contactPositionService;

    @Autowired
    ContactWorkSkillServiceImpl contactWorkSkillService;

    @Override
    public Contact getContactById(String id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public List<Contact> getAllContactTrue() {
        return (List<Contact>) contactRepository.findByIs_enableTrue();
    }

    @Override
    public void updateContact(Contact contact) {
        String id = contact.getCandidateId();
        // Lấy thằng đã có trong DB
        Contact haveContact = contactRepository.findById(id).get();
        // cập nhật lại những gì mới và lưu vào haveContact
        haveContact.setCandidateName(contact.getCandidateName());
        haveContact.setBirthDay(contact.getBirthDay());
        haveContact.setSex(contact.getSex());
        haveContact.setYearExperience(contact.getYearExperience());
        haveContact.setLevels(contact.getLevels());
        haveContact.setAddress(contact.getAddress());
        haveContact.setPhone1(contact.getPhone1());
        haveContact.setPhone2(contact.getPhone2());
        haveContact.setEmail1(contact.getEmail1());
        haveContact.setEmail2(contact.getEmail2());

        // Cập nhật ContactWorkSkill

        List<ContactWorkSkill> haveContactWorkSkill = new ArrayList<>(haveContact.getContactWorkSkillList());
        List<ContactWorkSkill> contactWorkSkills = new ArrayList<>(contact.getContactWorkSkillList());

        List<ContactPosition> haveContactPosition = new ArrayList<>(haveContact.getContactPositionList());
        List<ContactPosition> contactPositionList = new ArrayList<>(contact.getContactPositionList());

        for (ContactWorkSkill contactWorkSkill : haveContact.getContactWorkSkillList()) {
            for (ContactWorkSkill contactWorkSkill1 : contact.getContactWorkSkillList()) {
                if (contactWorkSkill.getSkill().equals(contactWorkSkill1.getSkill())) {
                    contactWorkSkill1.setContactWorkSkillId(contactWorkSkill.getContactWorkSkillId());
                    break;
                }
            }
        }


        for (ContactPosition contactPosition : haveContact.getContactPositionList()) {
            for (ContactPosition contactPosition1 : contact.getContactPositionList()) {
                if (contactPosition.getPosition().equals(contactPosition1.getPosition())) {
                    contactPosition1.setContactPositionId(contactPosition.getContactPositionId());
                    break;
                }
            }
        }

        // Xóa list cũ
        for (ContactWorkSkill contactWorkSkill : haveContactWorkSkill) {
            haveContact.removeContactWorkSkill(contactWorkSkill);
            contactWorkSkill.getSkill().removeContactWorkSkill(contactWorkSkill);
        }

        for (ContactPosition contactPosition : haveContactPosition) {
            haveContact.removeContactPosition(contactPosition);
            contactPosition.getPosition().removeContactPosition(contactPosition);
        }

        // Thêm List mới lại từ đầu
        for (ContactWorkSkill contactWorkSkill : contactWorkSkills) {
            haveContact.addContactWorkSkill(contactWorkSkill);
        }

        for (ContactPosition contactPosition : contactPositionList) {
            haveContact.addContactPosition(contactPosition);
        }

        contactRepository.saveAndFlush(haveContact);
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contact> search(SearchDto searchDto) {

        // Logic search

        String testQuery = "SELECT c.* FROM Contact c WHERE c.is_black_list = '0'";

        Query query = entityManager.createNativeQuery(testQuery, Contact.class);

        List<Contact> seachContact = (List<Contact>) query.getResultList();
        return seachContact;
    }

    public List<Contact> searchSpecification(SearchDto data) {
        return contactRepository.findAll(new ContactSpecification().searchFilter(data));
    }

    @Override
    public void saveContact(Contact contact) {
        // Lưu contact
        // Lưu contact trong ContactWorkSkil
        // lấy danh sách các skill của contact gửi lên
        List<ContactWorkSkill> contactWorkSkillList = contact.getContactWorkSkillList();
        for (ContactWorkSkill contactWorkSkill : contactWorkSkillList) {
            contactWorkSkill.setContact(contact);
        }
        // Lưu contact trong ContactPosition
        List<ContactPosition> contactPositionList = contact.getContactPositionList();
        for (ContactPosition contactPosition : contactPositionList) {
            contactPosition.setContact(contact);
        }
        Contact contactSave = contactRepository.saveAndFlush(contact);
    }


    @Override
    public List<Contact> findAllContactForJob(String id) {
        return contactRepository.findAllContactForJob(id);
    }

    public Contact getContactByGmail_1(String gmail) {
        return null;
    }

    @Override
    public Contact getContactByGmail_2(String gmail) {
        return null;
    }

    @Override
    public Contact getContactByPhone_1(String phone) {
        return null;
    }

    @Override
    public Contact getContactByPhone_2(String phone) {
        return null;
    }


}
