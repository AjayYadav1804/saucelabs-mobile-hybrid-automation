package com.saucelabs.mobile.utils;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private String filePath;

    public ExcelUtil(String filePath) {
        this.filePath = filePath;
    }

    public List<Map<String, String>> getTestData(String sDataDrivenSheetName, String sTestCaseName) {
        List<Map<String, String>> testData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sDataDrivenSheetName);
            Row headerRow = sheet.getRow(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                Map<String, String> rowData = new HashMap<>();
                for (Cell cell : row) {
                    String header = headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
                    String value = getCellValue(cell);
                    rowData.put(header, value);
                }

                // Add data to list if RunMode is "Y"
                if ("Y".equals(rowData.get("RunMode")) &&  sTestCaseName.equals(rowData.get("TestMethodName"))) {
                    testData.add(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if the numeric value should be integer
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
    
    
}