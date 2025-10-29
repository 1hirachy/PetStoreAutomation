package api.utilities;

import org.testng.annotations.DataProvider;
import api.utilities.XLUtility;
import java.io.IOException;

public class DataProviders {



    /**
     * DataProvider to get all data from a specific sheet in an Excel file.
     * This method skips the header row (index 0).
     *
     * @return A 2D Object array containing the test data.
     * @throws IOException If there's an issue reading the file.
     */
    @DataProvider(name = "Data")
    public static Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        String sheetName = "Sheet1";

        // Create a single instance of XUtility for all operations
        XLUtility x1 = new XLUtility(path, sheetName);

        int rowNum = x1.getRowsCount();
        System.out.println("Total rows found (including header): " + rowNum);

        int colCount = x1.getCellCount(1); // Assuming header row is at index 1 in your data
        System.out.println("Total Columns found (including header): " + colCount);

        // Determine max columns from the header row (row index 0)
        int maxColCount = x1.getCellCount(0);
        System.out.println("Max columns based on header: " + maxColCount);
        Object apiData[][] = new Object[rowNum - 1][colCount]; // Exclude header

        for (int i = 1; i < rowNum; i++) {
            int currentRowCellCount = x1.getCellCount(i); // Get actual column count for current row

            // Ensure the data array is filled correctly, even for rows with fewer columns
            for (int j = 0; j < currentRowCellCount && j < maxColCount; j++) {
                apiData[i - 1][j] = x1.getCellData(i, j);
            }
            // For columns that are missing in this row, set them to null or a default value
            for (int j = currentRowCellCount; j < maxColCount; j++) {
                apiData[i - 1][j] = "";
            }
        }

        x1.close(); // Close the resources after reading the data
        return apiData;
    }

    /**
     * DataProvider to get all usernames from a specific column in an Excel file.
     * This method also skips the header row (index 0).
     *
     * @return An Object array containing all the usernames.
     * @throws IOException If there's an issue reading the file.
     */
    @DataProvider(name = "UserNames")
    public static Object[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        String sheetName = "Sheet1";

        XLUtility x1 = new XLUtility(path, sheetName);

        int rowNum = x1.getRowsCount();
        Object[] apiData = new Object[rowNum - 1];

        // Loop over rows, starting from index 1 to skip the header (index 0).
        for (int i = 1; i < rowNum; i++) {
            // Get the data from the specified column (index 1 for the second column).
            apiData[i - 1] = x1.getCellData(i, 1);
        }

        x1.close();
        return apiData;
    }
}
