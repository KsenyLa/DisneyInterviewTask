package com.disney.interview.util;

import com.disney.interview.models.RegisterModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelProvider implements DataProvider {
    private static final String SHEET_NAME = "Model";
    private static final String FILE_NAME = "storage.xlsx";
    private String filePath;

    public ExcelProvider() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        filePath = path.substring(0, path.length() - 1) + FILE_NAME;
    }

    /**
     * Retrieves RegisterModel from storage
     * @return Loaded RegisterModel
     */
    @Override
    public RegisterModel getRegisterModel() throws IOException {
        RegisterModel result = new RegisterModel();

        Workbook workbook = WorkbookFactory.create(new File(filePath));
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        result.firstName = readRow(sheet,0);
        result.lastName = readRow(sheet,1);
        result.birthday = readRow(sheet,2);
        result.email = readRow(sheet,3);
        result.password = readRow(sheet,4);

        return result;
    }

    /**
     * Saves RegisterModel from storage
     * @param model RegisterModel for saving
     */
    @Override
    public void setRegisterModel(RegisterModel model) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        recordRow(sheet, "First Name", model.firstName, 0);
        recordRow(sheet, "Last Name", model.lastName, 1);
        recordRow(sheet, "Birthday", model.birthday, 2);
        recordRow(sheet, "Email", model.email, 3);
        recordRow(sheet, "Password", model.password, 4);

        FileOutputStream outputStream = null;
        outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        workbook.close();
    }

    private String readRow(Sheet sheet, int rowNumber){
        return sheet.getRow(rowNumber).getCell(1).getStringCellValue();
    }

    private void recordRow(Sheet sheet, String field, String value, int rowNumber){
        Row row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(field);
        row.createCell(1).setCellValue(value);
    }
}


