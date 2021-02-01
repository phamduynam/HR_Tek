package com.toprate.hr_tek_demo.excel;


import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.impl.ContactServiceImpl;
import com.toprate.hr_tek_demo.secvice.impl.PositionServiceImpl;
import com.toprate.hr_tek_demo.secvice.impl.SkillServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private SkillServiceImpl skillService;
    @Autowired
    private PositionServiceImpl positionService;



    // quản lý các dòng của file excel
    public Iterator<Row> rowIterator;
    // dùng để chạy các cell là hàm
    public FormulaEvaluator formulaEvaluator;
    // Sheet bắt đầu
    private int indexSheet = 0  ;
    // Số dòng bở qua
    private int countNextRow = 1;
    // Các cột cần lấy trong excel
//    private final int id = 0; // ID tự động tăng
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
    private final int listSKill = 13;
    private final int listPosition = 14;
    // Format ngày
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void saveDataInFile(MultipartFile fileExcel){
        // Mở file ở đây
        try {
            Workbook workbook = new XSSFWorkbook(fileExcel.getInputStream());
            System.out.println
                    ("============ SHEET NAME = " + workbook.getSheetAt(indexSheet).getSheetName() + " ============");

            rowIterator = workbook.getSheetAt(indexSheet).rowIterator();
            // bỏ qua các hàng title
            while (countNextRow-- > 0) {
                rowIterator.next();
            }
            formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        } catch (IOException e) {
            System.out.println("Vào ra file có vấn đề");
            e.printStackTrace();
        }

        int rowSave = forEachRow();
        System.out.println("Saved : " + rowSave);
    }
    // duyệt toàn bộ hàng của sheet và chạy doWithRow với mỗi hàng
    // trả về số lượng hàng đã duyệt
    public int forEachRow() {
        int countRows = 0;
        // duyệt tất cả hàng
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println("================== ROW " + row.getRowNum() + " ==================");
            // khi giá trị cột 1 tức (B) không có thì thoát (mặc dù cột khác có giá trị)
            CellValue cellValue = processCellValue(row, 2);
            if (cellValue == null || cellValue.toString().equals("[]")) {
                break;
            }
            Contact contact = new Contact();
            try {
                contact = doWithRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
//                Nếu có lỗi gì sẽ đáp vào listError
                System.out.println("Lỗi đây này DMMMMMMMMMMMMMMMMMMMMMMM" + e.getMessage());

            }
            System.out.println(contact);
            countRows++;
        }
        return countRows;
    }

    protected Contact doWithRow(Row row) throws ParseException {
        Contact contact = new Contact();
        contact.setCandidateName(getStringCellValue(row, name));
        String date = getDateInCell(row, dayOfBirth);
        contact.setBirthDay(dateFormat.parse(date)); // đẩy ra ngoại lệ nếu chuyển đổi sai
        contact.setSex(getStringCellValue(row, sex));
        contact.setAddress(getStringCellValue(row, address));
        contact.setWorkLocation(getStringCellValue(row, work_location));
        contact.setPhone1(getStringCellValue(row, phone_1));
        contact.setEmail1(getStringCellValue(row, email_1));
        contact.setEmail2(getStringCellValue(row, email_2));
        contact.setPhone2(getStringCellValue(row, phone_2));
        contact.setLinkCv(getStringCellValue(row, link_cv));
        contact.setLevels(getStringCellValue(row, level));
        contact.setYearExperience((float) getDoubleCellValue(row, year_experience));
        contact.setEnable(true);
        contact.setBlackList(false);
        // list skill String
        String[] listSkillString = processString(row, listSKill);
        String[] listPositionString = processString(row, listPosition);
        // Tạo list skill cho contact
        List<ContactWorkSkill> contactWorkSkillList = new ArrayList<>();
        for (String skillName : listSkillString) {
            Skill skill = skillService.getSkillByName(skillName);
            if(skill != null){
                contactWorkSkillList.add(new ContactWorkSkill(skill));
            }
        }
        contact.setContactWorkSkillList(contactWorkSkillList);
        // Tạo list position cho contact
        List<ContactPosition> contactPositionList = new ArrayList<>();
        for (String positionName : listPositionString) {
            Position position = positionService.getPositionByName(positionName);
            if(position != null){
                contactPositionList.add(new ContactPosition(position));
            }
        }
        contact.setContactPositionList(contactPositionList);

        contactService.saveContact(contact);

        System.out.println(contact);
//        // Cập nhật lại khóa ngoại
        for (ContactPosition contactPosition : contactPositionList){
            contactPosition.setContact(contact);
            System.out.println(contactPosition);
        }

        for (ContactWorkSkill contactWorkSkill : contactWorkSkillList){
            contactWorkSkill.setContact(contact);
        }

        contactService.saveContact(contact);
        return contact;
    }


    private boolean checkGmail(String gmail){
        // check gmail 1
        if(gmail == null || gmail.equals("")){
            return true;
        }else{
            // check gmail 1 có chưa
            String findGmail1 = contactService.getContactByGmail_1(gmail).getEmail1();
            String findGmail2 = contactService.getContactByGmail_2(gmail).getEmail2();
            if(findGmail1.equals(gmail) || findGmail2.equals(gmail)){
                return false;
            }
        }
        return true;
    }
    private boolean checkPhone(String phone){
        // check gmail 1
        if(phone == null || phone.equals("")){
            return true;
        }else{
            // check gmail 1 có chưa
            String findGmail1 = contactService.getContactByPhone_1(phone).getPhone1();
            String findGmail2 = contactService.getContactByPhone_2(phone).getPhone2();
            if(findGmail1.equals(phone) || findGmail2.equals(phone)){
                return false;
            }
        }
        return true;
    }
    public String[] processString(Row row, int colum) {
        String listObjectString = getStringCellValue(row, colum);
        // Tách thành các mảng bởi dấu ','
        String[] arrayObject = listObjectString.split(",");
        return arrayObject;
    }
    // xử lý evaluator (công thức trong excel) nếu có lỗi thì trả về null
    protected CellValue processCellValue(Row row, int column) {
        try {
            return formulaEvaluator.evaluate(row.getCell(column));
        } catch (Exception e) {
            System.out.println("\ncontent cell error = " + row.getCell(column));
            e.printStackTrace();
            return null;
        }
    }

    // trả giá trị int nếu lỗi trả về 0
    protected int getIntInCell(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return (int) cellValue.getNumberValue();
        }
        return 0;
    }

    // trả về ngày tháng đã chuyển sang String nếu không thì trả về rỗng
    protected String getDateInCell(Row row, int colum) throws ParseException {
        CellValue cellValue = processCellValue(row, colum);
        if (cellValue == null) {
            return "";
        }
        Date date = DateUtil.getJavaDate(row.getCell(colum).getNumericCellValue());
        if (cellValue != null) {
            return dateFormat.format(date);
        }
        return "";
    }


    // trả giá trị string từ cell nếu lỗi trả về rỗng
    protected String getStringCellValue(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getStringValue();
        }
        return "";
    }

    // trả giá trị boolean nếu lỗi trả về false
    protected boolean getBolleanCellValue(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getBooleanValue();
        }
        return false;
    }

    protected double getDoubleCellValue(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getNumberValue();
        }
        return 0;
    }

}
