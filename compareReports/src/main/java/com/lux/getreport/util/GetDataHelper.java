package com.lux.getreport.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class GetDataHelper {

    private GetDataHelper() {

    }

    public static String getCellData(Cell cell) {
        String data = "";
        switch (cell.getCellType()) {
        case STRING:
            data = (String) cell.getStringCellValue();
            break;
        case NUMERIC:
            data = Double.valueOf(cell.getNumericCellValue()).toString();
            break;
        default:
            data = "";
            break;
        }
        return data;
    }

    public static ArrayList<String> getHeaders(Iterator<Row> iterator) {
        Row nextRow;
        ArrayList<String> headers = new ArrayList<>();
        if (iterator.hasNext()) {
            nextRow = iterator.next();
            Iterator<Cell> headerIterator = nextRow.cellIterator();
            while (headerIterator.hasNext()) {
                Cell cell = headerIterator.next();
                String data = getCellData(cell);
                headers.add(data);
            }
        }
        return headers;

    }

}