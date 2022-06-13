package com.lux.getreport.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Stylist {

    private Stylist() {

    }

    public static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        short white = IndexedColors.WHITE.getIndex();
        font.setBold(true);
        font.setColor(white);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        byte[] rgb = { (byte) 79, (byte) 129, (byte) 189 };
        XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(xssfColor);
        return style;
    }

    public static CellStyle setStyle(int i, XSSFWorkbook workbook) {
        if (i % 2 == 0) {
            return evenCellStyleSetter(workbook);
        } else {
            return oddCellStyleSetter(workbook);
        }
    }

    private static XSSFCellStyle evenCellStyleSetter(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        byte[] foregroundColor = { (byte) 220, (byte) 230, (byte) 241 };
        byte[] topButtomBorderColor = { (byte) 149, (byte) 179, (byte) 215 };
        XSSFColor xssfColor = new XSSFColor(foregroundColor, new DefaultIndexedColorMap());
        XSSFColor xssfTopButtomBorderColor = new XSSFColor(topButtomBorderColor, new DefaultIndexedColorMap());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(xssfTopButtomBorderColor);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(xssfTopButtomBorderColor);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(xssfColor);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(xssfColor);
        style.setFillForegroundColor(xssfColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private static XSSFCellStyle oddCellStyleSetter(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        byte[] foregroundColor = { (byte) 255, (byte) 255, (byte) 255 };
        byte[] leftRightBorderColor = { (byte) 212, (byte) 212, (byte) 212 };
        byte[] topButtomBorderColor = { (byte) 149, (byte) 179, (byte) 215 };
        XSSFColor xssfTopButtomBorderColor = new XSSFColor(topButtomBorderColor, new DefaultIndexedColorMap());
        XSSFColor xssfLeftRightBorderColor = new XSSFColor(leftRightBorderColor, new DefaultIndexedColorMap());
        XSSFColor xssfColor = new XSSFColor(foregroundColor, new DefaultIndexedColorMap());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(xssfTopButtomBorderColor);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(xssfTopButtomBorderColor);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(xssfLeftRightBorderColor);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(xssfLeftRightBorderColor);
        style.setFillForegroundColor(xssfColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    public static XSSFCellStyle setHighRiskStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        byte[] foregroundColor = { (byte) 255, (byte) 199, (byte) 206 };
        byte[] fontColor = { (byte) 156, (byte) 0, (byte) 6 };
        XSSFColor xssfColor = new XSSFColor(foregroundColor, new DefaultIndexedColorMap());
        XSSFColor xssfFontColor = new XSSFColor(fontColor, new DefaultIndexedColorMap());
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(xssfFontColor);
        style.setFont(font);
        style.setFillForegroundColor(xssfColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}