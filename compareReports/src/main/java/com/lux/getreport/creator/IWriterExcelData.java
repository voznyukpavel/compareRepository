package com.lux.getreport.creator;

import java.io.IOException;

public interface IWriterExcelData {

	public void writeData(String path, String reportType) throws IOException;

}