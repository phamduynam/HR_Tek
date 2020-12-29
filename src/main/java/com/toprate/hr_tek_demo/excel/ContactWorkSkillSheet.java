package com.toprate.hr_tek_demo.excel;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.model.Skill;
import com.toprate.hr_tek_demo.secvice.ContactService;
import com.toprate.hr_tek_demo.secvice.SkillService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;

public class ContactWorkSkillSheet extends SheetToObject<ContactWorkSkill> {

    private JpaRepository contactWorkSkillRepo;
    private SkillService skillService;
    private ContactService contactService;

    private int contactIdNumber = 0;
    private int skillIdNumber = 1;
    private int yearExNumber = 2;
    private int descriptionNumber = 3;

    public ContactWorkSkillSheet(MultipartFile fileExcel, int indexSheet, int countNextRow,
                                 JpaRepository jpaRepository,SkillService skillService, ContactService contactService
    ) {
        super(fileExcel, indexSheet, countNextRow);
        this.contactWorkSkillRepo = jpaRepository;
        this.contactService = contactService;
        this.skillService = skillService;
    }

    public void saveAllRow() {
        // Duyệt từng hàng của sheet và gọi hàm doWithRow()
        int countRow = forEachRow();
        // Hiển thị đã lưu record chưa ?
        System.out.println("Contact_Work_Skill Save ----> " +  countRow + " record ");
    }


    @Override
    protected ContactWorkSkill doWithRow(Row row) throws ParseException, Exception {
        ContactWorkSkill contactWorkSkill = new ContactWorkSkill();

        // Lấy ID Contact
        int contactIdInt = getIntInCell(row,contactIdNumber);
        String contactIdString = String.valueOf(contactIdInt);
        // Lấy Contact
        Contact contact = contactService.getContactById(contactIdString);
        // đẩy vào contactWorkSkill
        contactWorkSkill.setContact(contact);

        // Lấy ID Skill
        int idSkill = getIntInCell(row,skillIdNumber);
        // Lấy Skill by Id
        Skill skill = skillService.getSkillById(idSkill);
        // Đẩy vào contactWorlSkill
        contactWorkSkill.setSkill(skill);

        //Lấy số năm kinh nghiệm
        float yearExperience = (float)getDoubleCellValue(row,yearExNumber);
        contactWorkSkill.setSkillYearExperience(yearExperience);

        contactWorkSkill.setDescription(getStringCellValue(row,descriptionNumber));

        System.out.println("CONTACT WORK SKILL IN ROW : " + row.getRowNum() + "====>" + contactWorkSkill.toString());
        contactWorkSkillRepo.save(contactWorkSkill);

        return contactWorkSkill;
    }
}
