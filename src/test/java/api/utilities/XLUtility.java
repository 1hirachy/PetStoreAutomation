package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    private final FileInputStream fi;
    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;
    private final String path;

    // Constructor to initialize the file path, workbook, and sheet
    public XLUtility(String path, String sheetName) throws IOException {
        this.path = path;
        this.fi = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(fi);
        this.sheet = workbook.getSheet(sheetName);
        if (this.sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook.");
        }
    }

    // Method to get total number of rows in a sheet
    public int getRowsCount() {
        // LastRowNum is 0-based, so add 1 to get the total number of rows.
        return sheet.getLastRowNum() + 1;
    }

    // Method to get total number of columns in a specific row
    public int getCellCount(int rowNum) {
        XSSFRow r = sheet.getRow(rowNum);
        return r != null ? r.getLastCellNum() : 0;
    }

    // Method to get the cell data (String) from a specific cell
    public String getCellData(int rowNum, int colNum) {
        XSSFRow r = sheet.getRow(rowNum);
        if (r == null) {
            return "";
        }
        XSSFCell c = r.getCell(colNum);
        if (c == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(c);
    }

    // **Note:** Methods that modify the file (setCellData, createSheet, fillGreenColor, fillRedColor) will need to handle file I/O properly.
    // They should get the workbook instance from the object, modify it, and then write it to disk.
    // The current implementation repeatedly opens and closes the file, which is inefficient.
    // For reading data, this simplified version is sufficient.

    // A method to close the file stream after all operations are done
    public void close() throws IOException {
        workbook.close();
        fi.close();
    }
}