package com.lux.getreport.creator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lux.getreport.comparator.InventoryNameComparator;
import com.lux.getreport.model.InventoryReport;
import com.lux.getreport.storage.InventoryReportStorage;
import com.lux.getreport.util.FileMaker;
import com.lux.getreport.util.Stylist;

public class InventoryReportCreator extends AbstractReportCreator implements IWriterExcelData {

    private static final int COLUMN_WIDTH = 25;
    
    private static final String[] HEADERS= {"Component Name","Licenses old => new","Licenses Links old => new","Package Type old => new","Component Id: old => new","Package Id old => new","Version old => new"};
    
    private InventoryReportStorage oldReport, newReport;

    public InventoryReportCreator(InventoryReportStorage oldReport, InventoryReportStorage newReport) {
        this.oldReport = oldReport;
        this.newReport = newReport;
    }

    @Override
    public void writeData(String path, String reportType) throws FileNotFoundException, IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(reportType);
        XSSFRow row = sheet.createRow(0);
        createHeader(Arrays.asList(HEADERS), row, COLUMN_WIDTH);
        ArrayList<InventoryReport> oldLibs = oldReport.getReport();
        ArrayList<InventoryReport> newLibs = newReport.getReport();
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, HEADERS.length - 1));
        sheet.createFreezePane(0, 1);
        oldLibs.sort(new InventoryNameComparator());
        newLibs.sort(new InventoryNameComparator());
        ArrayList<InventoryReport> chosenLibs = addSelectedLicenses(newLibs, oldLibs);
        createData(chosenLibs);
        FileMaker.makeFile(path, workbook);
        workbook.close();
    }

    private ArrayList<InventoryReport> addSelectedLicenses(ArrayList<InventoryReport> newLibs,
            ArrayList<InventoryReport> oldLibs) {
        ArrayList<InventoryReport> result = new ArrayList<InventoryReport>();
        int newSize = newLibs.size();
        int oldSize = oldLibs.size();
        for (int i = 0; i < newSize; i++) {
            boolean isPresentInOld = false;
            for (int j = 0; j < oldSize; j++) {
                if (oldLibs.get(j).getComponentName().equals(newLibs.get(i).getComponentName())) {
                    isPresentInOld = true;
                    if (!oldLibs.get(j).getLicenses().equals(newLibs.get(i).getLicenses())) {
                        
                        newLibs.get(i).setComponentName(oldLibs.get(j).getComponentName()+" => "+newLibs.get(i).getComponentName());
                        newLibs.get(i).setLicenses(oldLibs.get(j).getLicenses()+" => "+newLibs.get(i).getLicenses());
                        newLibs.get(i).setLicensesLinks(oldLibs.get(j).getLicensesLinks()+" => "+newLibs.get(i).getLicensesLinks());
                        newLibs.get(i).setPackageType(oldLibs.get(j).getPackageType()+" => "+newLibs.get(i).getPackageType());
                        newLibs.get(i).setComponentId(oldLibs.get(j).getComponentId()+" => "+newLibs.get(i).getComponentId());
                        newLibs.get(i).setPackageId(oldLibs.get(j).getPackageId()+" => "+newLibs.get(i).getPackageId());
                        newLibs.get(i).setVersion(oldLibs.get(j).getVersion()+" => "+newLibs.get(i).getVersion());

                        result.add(newLibs.get(i));
                    }
                    break;
                }
            }
            if (isPresentInOld == false) {
                result.add(newLibs.get(i));
            }
        }
        return result;
    }

    private void createData(ArrayList<InventoryReport> report) {
        XSSFCell cell;
        XSSFRow row;
        int size = report.size();
        for (int i = 0; i < size; i++) {
            row = sheet.createRow(i + 1);
            InventoryReport library = (InventoryReport) report.get(i);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(library.getComponentName());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(library.getLicenses());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(library.getLicensesLinks());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(library.getPackageType());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(library.getComponentId());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(library.getPackageId());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(library.getVersion());
            cell.setCellStyle(Stylist.setStyle(i, workbook));

        }
    }
}