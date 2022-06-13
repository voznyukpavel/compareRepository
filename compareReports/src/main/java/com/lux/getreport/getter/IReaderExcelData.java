package com.lux.getreport.getter;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lux.getreport.storage.InventoryReportStorage;

public interface IReaderExcelData {

	public InventoryReportStorage readData(String file) throws FileNotFoundException, IOException;

}