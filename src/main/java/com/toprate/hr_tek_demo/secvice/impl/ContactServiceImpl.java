package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        // Cập nhật Id cho các contactWorkSkill đã có để nó biết đường update
        List<ContactWorkSkill> beforeContactWorkSkillList = haveContact.getContactWorkSkillList();
        List<ContactWorkSkill> contactWorkSkillList = contact.getContactWorkSkillList();
        List<ContactWorkSkill> savedContactWorkSkill = new ArrayList<>();

        for (ContactWorkSkill contactWorkSkill : beforeContactWorkSkillList) {
            for (ContactWorkSkill contactWorkSkillTemp : contactWorkSkillList) {
                if (contactWorkSkill.getSkill() == contactWorkSkillTemp.getSkill()) {
                    // cập nhật lần đầu tiên tìm thấy id
                    contactWorkSkillTemp.setContactWorkSkillId(contactWorkSkill.getContactWorkSkillId());
                    // thêm vào list đã có
                    savedContactWorkSkill.add(contactWorkSkill);
                    break;
                }
            }
        }
        // Cập nhật list skill
        contact.setContactWorkSkillList(contactWorkSkillList);
        // Cập nhật Id cho các contactPosition đã có để nó biết đường update
        List<ContactPosition> beforeContactPositionList = haveContact.getContactPositionList();
        List<ContactPosition> contactPositionList = contact.getContactPositionList();
        List<ContactPosition> savedContactPosition = new ArrayList<>();
        // Gặp phần tử đầu tiên trùng skill thì cập nhật id rồi break
        for (ContactPosition contactPosition : beforeContactPositionList) {
            for (ContactPosition contactPositionTemp : contactPositionList) {
                if (contactPosition.getPosition() == contactPositionTemp.getPosition()) {
                    // Cập nhật Id cho contactPosition
                    contactPositionTemp.setContactPositionId(contactPosition.getContactPositionId());
                    // Thêm vào list position đã có
                    savedContactPosition.add(contactPosition);
                    break;
                }
            }
        }
        // cập nhật list position
        contact.setContactPositionList(contactPositionList);
        // Danh sách các skill và position cần xóa
       beforeContactWorkSkillList.removeAll(savedContactWorkSkill);
       beforeContactPositionList.removeAll(savedContactPosition);

        // Xóa ContactWorkSkill thừa
        for(Iterator<ContactWorkSkill> iterator = beforeContactWorkSkillList.iterator(); iterator.hasNext();){
            ContactWorkSkill contactWorkSkill = iterator.next();
            haveContact.removeContactWorkSkill(contactWorkSkill);
            contactWorkSkill.getSkill().removeContactWorkSkill(contactWorkSkill);
        }

        // Xóa ContactPosition thừa
        for(Iterator<ContactPosition> iterator = beforeContactPositionList.iterator(); iterator.hasNext();){
            ContactPosition contactPosition = iterator.next();
            haveContact.removeContactPosition(contactPosition);
            contactPosition.getPosition().removeContactPosition(contactPosition);
        }
        contactRepository.saveAndFlush(haveContact);
        saveContact(contact);

    }

    // Hàm này đẻ tối ưu, chưa có logic
    public void deleteExcess(Contact contact,Contact newContact){
        // Muốn xóa một skill trong contact phải remove lần lượt các skill đó khỏi list
        // Không được xóa bằng cách setList =  newList vì nó sẽ bị lỗi.
    }

    @Override
    public void saveContact(Contact contact) {
        // Lưu contact
        Contact contactSave = contactRepository.saveAndFlush(contact);

        // Lưu contact trong ContactWorkSkil
        // lấy danh sách các skill của contact gửi lên
        List<ContactWorkSkill> contactWorkSkillList = contactSave.getContactWorkSkillList();
        for (ContactWorkSkill contactWorkSkill : contactWorkSkillList) {
            contactWorkSkill.setContact(contactSave);
        }
        // Lưu contact trong ContactPosition
        List<ContactPosition> contactPositionList = contactSave.getContactPositionList();
        for (ContactPosition contactPosition : contactPositionList) {
            contactPosition.setContact(contactSave);
        }
        // Lưu list Position
        contactPositionService.saveList(contactPositionList);
        // Lưu list skill
        contactWorkSkillService.saveList(contactWorkSkillList);
    }
}
