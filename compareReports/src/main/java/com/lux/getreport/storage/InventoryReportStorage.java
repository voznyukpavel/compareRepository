package com.lux.getreport.storage;

import java.util.ArrayList;

import com.lux.getreport.model.InventoryReport;

public class InventoryReportStorage extends AbstractReportStorage {

    private ArrayList<InventoryReport> report;

    public InventoryReportStorage() {
        report = new ArrayList<>();
        headers = new ArrayList<>();
    }

    public ArrayList<InventoryReport> getReport() {
        return report;
    }

    public void setReport(ArrayList<InventoryReport> report) {
        this.report = report;
    }

    public void showReport() {
        report.forEach(i -> System.out.println(i));
    }

    public ArrayList<InventoryReport> getMultiLicensesLibs() {
        ArrayList<InventoryReport> chosenLibs = new ArrayList<>();
        report.forEach(i -> {
            if (i.getLicenses().contains(" or ") || i.getLicenses().contains(",")) {
                chosenLibs.add(i);
            }
        });
        return chosenLibs;
    }

    public ArrayList<InventoryReport> getLibsByLicenseType(String reportType) {
        ArrayList<InventoryReport> chosenLibs = new ArrayList<>();
        report.forEach(i -> {
            if (i.getLicenses().equals(reportType)) {
                chosenLibs.add(i);
            }
        });
        return chosenLibs;
    }

    public ArrayList<InventoryReport> getLibsByLicenseTypeContent(String reportType) {
        ArrayList<InventoryReport> chosenLibs = new ArrayList<>();
        report.forEach(i -> {
            if (i.getLicenses().contains(reportType)) {
                chosenLibs.add(i);
            }
        });
        return chosenLibs;
    }
}
