package com.toprate.hr_tek_demo.excel;

import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.repository.ContactPositionRepository;
import com.toprate.hr_tek_demo.secvice.ContactService;
import com.toprate.hr_tek_demo.secvice.PositionService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;


public class ContactPositionSheet extends SheetToObject<ContactPosition> {

    private JpaRepository contactPositionRepo;
    private ContactService contactService;
    private PositionService positionService;

    public ContactPositionSheet(MultipartFile fileExcel, int indexSheet, int countNextRow,
                                JpaRepository jpaRepository, ContactService contactService, PositionService positionService) {
        super(fileExcel, indexSheet, countNextRow);
        this.contactPositionRepo = jpaRepository;
        this.contactService = contactService;
        this.positionService = positionService;
    }

    // ID tự động tăng nên không cần
    private int contactCandidateId = 0;
    private int positonId = 1;
    private int description = 2;

    public void saveAllRow() {
        // Duyệt từng hàng của sheet và gọi hàm doWithRow()
        int countRow = forEachRow();
        // Hiển thị đã lưu record chưa ?
        System.out.println("Contact_Position Save ----> " +  countRow + " record ");
    }

    @Override
    protected ContactPosition doWithRow(Row row) throws Exception {
        ContactPosition contactPosition = new ContactPosition();

        // Lấy Id của Contact
        int contactIdNumber = getIntInCell(row,contactCandidateId);
        String contactIdString = String.valueOf(contactIdNumber);
        contactPosition.setContact(contactService.getContactById(contactIdString));

        // Lấy Id của Position
        int position_id = getIntInCell(row,positonId);
        contactPosition.setPosition(positionService.getPositonById(position_id));

        // Description
        contactPosition.setDescription(getStringCellValue(row,description));

        System.out.println("CONTACT_POSITION IN ROW : " + row.getRowNum() + "===>" + contactPosition.toString());
        // Lưu vào DB
        contactPositionRepo.save(contactPosition);

        return contactPosition;
    }
}
