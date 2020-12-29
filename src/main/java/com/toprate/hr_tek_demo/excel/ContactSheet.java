package com.toprate.hr_tek_demo.excel;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;

public class ContactSheet extends SheetToObject<Contact> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private JpaRepository contactRepo;

    // Các cột cần lấy trong excel
    private final int id = 0;
    private final int name = 1;
    private final int dayOfBirth = 2;
    private final int address = 3;
    private final int email_1 = 4;
    private final int phone_1 = 5;
    private final int email_2 = 6;
    private final int phone_2 = 7;
    private final int work_location = 8;
    private final int link_cv = 9;
    private final int year_experience = 10;
    private final int sex = 11;
    private final int level = 12;

    public ContactSheet(MultipartFile myFile, int index, int countRow, JpaRepository jpaRepository) {
        super(myFile, index, countRow);
        this.contactRepo = jpaRepository;
    }

    public void saveAllRow() {
        // Duyệt từng hàng của sheet và gọi hàm doWithRow()
        int countRow = forEachRow();
        // Hiển thị đã lưu record chưa ?
        System.out.println("Contact Save ----> " + countRow + " <---- ");
    }

    @Override
    protected Contact doWithRow(Row row) throws Exception {

        Contact contact = new Contact();
        // Lấy ID dạng Int
        int idContact = getIntInCell(row, id);
        contact.setCandidateId(String.valueOf(idContact));

        contact.setCandidateName(evaluatorGetString(row, name));

        String date = getDateInCell(row, dayOfBirth);
        contact.setBirthDay(dateFormat.parse(date)); // đẩy ra ngoại lệ nếu chuyển đổi sai

        contact.setSex(evaluatorGetString(row, sex));
        contact.setAddress(evaluatorGetString(row, address));
        contact.setWorkLocation(evaluatorGetString(row, work_location));
        contact.setPhone_1(evaluatorGetString(row, phone_1));
        contact.setEmail_1(evaluatorGetString(row, email_1));
        contact.setEmail_2(evaluatorGetString(row, email_2));
        contact.setPhone_2(evaluatorGetString(row, phone_2));
        contact.setLinkCv(evaluatorGetString(row, link_cv));
        contact.setLevels(evaluatorGetString(row, level));
        contact.setYearExperience((float) getDoubleCellValue(row, year_experience));
        contact.setIs_enable(true);
        // Save
        System.out.println("CONTACT IN ROW : " + row.getRowNum() + "====>" + contact.toString());
        contactRepo.save(contact);
        return contact;
    }
}
