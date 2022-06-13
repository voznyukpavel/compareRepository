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
    
    private static final String[] HEADERS= {"Name","Group old => new","Artifact old => new","Version old => new","Licenses: old => new","Type old => new","SHA1 old => new","Description old => new","Match Type old => new"};
    
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
                if (oldLibs.get(j).getName().equals(newLibs.get(i).getName())) {
                    isPresentInOld = true;
                    if (!oldLibs.get(j).getLicenses().equals(newLibs.get(i).getLicenses())) {
                        
                        newLibs.get(i).setGroup(oldLibs.get(j).getGroup()+" => "+newLibs.get(i).getGroup());
                        newLibs.get(i).setArtifact(oldLibs.get(j).getArtifact()+" => "+newLibs.get(i).getArtifact());
                        newLibs.get(i).setVersion(oldLibs.get(j).getVersion()+" => "+newLibs.get(i).getVersion());
                        newLibs.get(i).setLicenses(oldLibs.get(j).getLicenses()+" => "+newLibs.get(i).getLicenses());
                        newLibs.get(i).setType(oldLibs.get(j).getType()+" => "+newLibs.get(i).getType());
                        newLibs.get(i).setSha1(oldLibs.get(j).getSha1()+" => "+newLibs.get(i).getSha1());
                        newLibs.get(i).setDescription(oldLibs.get(j).getDescription()+" => "+newLibs.get(i).getDescription());
                        newLibs.get(i).setMatchType(oldLibs.get(j).getMatchType()+" => "+newLibs.get(i).getMatchType());
                        
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
            cell.setCellValue(library.getName());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(library.getGroup());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(library.getArtifact());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(library.getVersion());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(library.getLicenses());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(library.getType());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(library.getSha1());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(library.getDescription());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(library.getMatchType());
            cell.setCellStyle(Stylist.setStyle(i, workbook));
            
          
        }
    }
}