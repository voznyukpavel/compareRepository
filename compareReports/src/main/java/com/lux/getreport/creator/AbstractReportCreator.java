package com.lux.getreport.creator;

import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lux.getreport.util.Stylist;

abstract class AbstractReportCreator {

    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;

    protected void createHeader(List<String> headers, XSSFRow row, int colupnWidth) {
        XSSFCell cell;
        XSSFCellStyle style = Stylist.createStyleForTitle(workbook);
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, colupnWidth * 256);
        }
    }
}
