package com.toprate.hr_tek_demo.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

// FormulaEvaluator hỗ trợ không tốt , nên copy sang cột khác set cứng giá trị

public abstract class SheetToObject<T> {

    // quản lý các dòng của file excel
    public Iterator<Row> rowIterator;
    // dùng để chạy các cell là hàm
    public FormulaEvaluator formulaEvaluator;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // indexSheet vị trí sheet trong file excel
    // countNextRow số dòng bỏ qua lúc đầu (title)

    public SheetToObject(MultipartFile fileExcel, int  indexSheet, int countNextRow) {

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
            e.printStackTrace();
        }

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
            CellValue cellValue = processCellValue(row, 1);
            if (cellValue == null || cellValue.toString().equals("[]")) {
                break;
            }
            
            T object = (T) new Object();
            try {
                object = doWithRow(row);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(object);
            countRows++;
        }
        return countRows;
    }

    // làm việc với 1 row trả về đối tượng T
    // Những thằng con kế thừa phải tự định nghĩa phương thức đọc
    protected abstract T doWithRow(Row row) throws ParseException, Exception;

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
    protected String getDateInCell(Row row,int colum) throws ParseException {
        CellValue cellValue = processCellValue(row,colum);
        if(cellValue == null){
            return "";
        }
        Date date =  DateUtil.getJavaDate(row.getCell(colum).getNumericCellValue());
        if(cellValue != null){
            return dateFormat.format(date);
        }
        return "";
    }

    // trả giá trị string nếu lỗi trả về rỗng
    protected String evaluatorGetString(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getStringValue();
        }
        return "";
    }
    // trả giá trị string từ cell nếu lỗi trả về rỗng
    protected String getStringCellValue(Row row, int column) {
        Cell temp = row.getCell(column);
        if (temp != null) {
            return temp.getStringCellValue();
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
