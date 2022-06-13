package com.lux.getreport.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileMaker {

    private FileMaker() {

    }

    public static void makeFile(String path, XSSFWorkbook workbook) throws FileNotFoundException, IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
    }
}