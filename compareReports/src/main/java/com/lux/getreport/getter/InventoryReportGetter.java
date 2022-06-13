package com.lux.getreport.getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lux.getreport.model.InventoryReport;
import com.lux.getreport.storage.InventoryReportStorage;
import com.lux.getreport.util.GetDataHelper;

public class InventoryReportGetter implements IReaderExcelData {

	@Override
	public InventoryReportStorage readData(String file) throws FileNotFoundException, IOException {
		ZipSecureFile.setMinInflateRatio(0);
		XSSFWorkbook inventory = new XSSFWorkbook(new FileInputStream(file));
		ArrayList<InventoryReport> report = new ArrayList<>();
		Sheet inventorySheet = inventory.getSheetAt(0);
		Iterator<Row> iterator = inventorySheet.iterator();
		ArrayList<String> headers = GetDataHelper.getHeaders(iterator);
		InventoryReportStorage storage= new InventoryReportStorage();
		storage.setHeaders(headers);
		Row nextRow;
		while (iterator.hasNext()) {
			nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			InventoryReport library = new InventoryReport();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				String data = GetDataHelper.getCellData(cell);
				setDataToModel(library, columnIndex, data);
			}
			report.add(library);
		}
		inventory.close();
		storage.setReport(report);
    return storage;
	}

	private void setDataToModel(InventoryReport library, int columnIndex, String data) {
		switch (columnIndex) {
		case 0:
			library.setComponentName(data);
			break;
		case 1:
		  library.setLicenses(data);
			break;
		case 2:
			library.setLicensesLinks(data);
			break;
		case 3:
		  library.setPackageType(data);
			break;
		case 4:
			library.setComponentId(data);
			break;
		case 5:
			library.setPackageId(data);
			break;
		case 6:
		  library.setVersion(data);
			break;
		}
	}
}