package com.lux.getreport.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lux.getreport.creator.IWriterExcelData;
import com.lux.getreport.creator.InventoryReportCreator;
import com.lux.getreport.getter.IReaderExcelData;
import com.lux.getreport.getter.InventoryReportGetter;
import com.lux.getreport.storage.InventoryReportStorage;

class Executor {

    private static final String INVENTORY = "Inventory";

    private static final String IO_READ_ERROR_MESSAGE = "Exception occured during reading report";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File not found";
    private static final String IO_WRITE_ERROR_MESSAGE = "Exception occured during writing report";

    private static final String FOLDER = "compare";

    private static final String INVENTORY_PATH_OLD = "C:\\Users\\Pavel\\Desktop\\" + FOLDER + "\\" + "old.xlsx";
    private static final String INVENTORY_PATH_NEW = "C:\\Users\\Pavel\\Desktop\\" + FOLDER + "\\" + "new.xlsx";
    private static final String INVENTORY_RESULT = "C:\\Users\\Pavel\\Desktop\\" + FOLDER + "\\" + "result.xlsx";
    //private static final String INVENTORY_OLD_RESULT = "C:\\Users\\Pavel\\Desktop\\" + FOLDER + "\\" + "resultOld.xlsx";

    private final Logger logger = Logger.getLogger(Executor.class.getName());

    void run() {
        InventoryReportStorage oldReport = read(new InventoryReportGetter(), INVENTORY_PATH_OLD, INVENTORY);
        InventoryReportStorage newReport = read(new InventoryReportGetter(), INVENTORY_PATH_NEW, INVENTORY);
        write(new InventoryReportCreator(oldReport, newReport), INVENTORY_RESULT, INVENTORY);
        //write(new InventoryReportCreator(newReport, oldReport), INVENTORY_OLD_RESULT, INVENTORY);
    }

    private InventoryReportStorage read(IReaderExcelData fromReportData, String path, String report) {
        InventoryReportStorage storage = null;
        try {
            storage = fromReportData.readData(path);
            System.out.println(path + " was read");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, path + " Report type: " + report + " " + FILE_NOT_FOUND_ERROR_MESSAGE, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, path + " Report type: " + report + " " + IO_READ_ERROR_MESSAGE, e);
        }
        return storage;
    }

    private void write(IWriterExcelData reportData, String path, String report) {
        try {
            reportData.writeData(path, report);
            System.out.println(path + " is completed");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, path + " Report type: " + report + " " + FILE_NOT_FOUND_ERROR_MESSAGE, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, path + " Report type: " + report + " " + IO_WRITE_ERROR_MESSAGE, e);
        }
    }
}