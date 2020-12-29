package com.toprate.hr_tek_demo.excel;


import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.repository.ContactPositionRepository;
import com.toprate.hr_tek_demo.repository.ContactRepository;
import com.toprate.hr_tek_demo.repository.ContactWorkSkillRepository;
import com.toprate.hr_tek_demo.secvice.ContactService;
import com.toprate.hr_tek_demo.secvice.PositionService;
import com.toprate.hr_tek_demo.secvice.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {


    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactWorkSkillRepository contactWorkSkillRepository;
    @Autowired
    private ContactPositionRepository contactPositionRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private PositionService positionService;

    // Vị trí Contact là ở sheet 1
    private int contactSheetNumber = 0;
    // Vị trí Contact_Work_Skill ở sheet 2
    private int contactWorkSkillSheetNumber = 1;
    // Vị trí Contact_Position ở sheet 3
    private int contactPostionSheetNunmber = 2;

    // số dòng bỏ qua tính cả title
    private int countTitle = 2;

    public void saveContactInSheet(MultipartFile myFile){
        // Lưu Contact
        ContactSheet contactSheet = new ContactSheet(myFile,contactSheetNumber,countTitle,contactRepository);
        // thực hiện hành động duyệt các giá trị và lưu
        contactSheet.saveAllRow();
    }

    public void saveContactWorkSkillInSheet(MultipartFile myFile){
        // Khởi tạo một ContactWorkSkill mới
        ContactWorkSkillSheet contactWorkSkillSheet = new ContactWorkSkillSheet(
                myFile,
                contactWorkSkillSheetNumber,
                countTitle,
                contactWorkSkillRepository,
                skillService,
                contactService);
        // thực hiện hành động duyệt các giá trị và lưu
        contactWorkSkillSheet.saveAllRow();
    }

    public void saveContactPositionInSheet(MultipartFile myFile){
        // Khởi tạo một ContactPositionSheet mới
        ContactPositionSheet contactPositionSheet = new ContactPositionSheet(
                myFile,
                contactPostionSheetNunmber,
                countTitle,
                contactPositionRepository,
                contactService,
                positionService);
        // thực hiện hành động duyệt các giá trị và lưu
        contactPositionSheet.saveAllRow();
    }

}
