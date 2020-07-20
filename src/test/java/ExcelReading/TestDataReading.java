package ExcelReading;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReading {

	public ArrayList<String> getdata(String data, String sheetname) throws IOException {
		
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Rajeshwari\\eclipse-workspace\\APIBddFramework\\Reading Data from Excel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int no_sheet = workbook.getNumberOfSheets();
		ArrayList<String> test_data = new ArrayList<String>();
		for (int i = 0; i < no_sheet; i++) {
			if (workbook.getSheetName(i).trim().equalsIgnoreCase(sheetname)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// identifying the first row to scan the header
				Iterator<Row> rows = sheet.rowIterator();
				Row firstrow = rows.next();// get all the cells of the first row
				Iterator<Cell> cells = firstrow.cellIterator();
				int k = 0;
				int col_num = 0;
				while (cells.hasNext()) {
					Cell value = cells.next();
					if (value.getStringCellValue().trim().equalsIgnoreCase("testcase")) {
						// desired column number
						col_num = k;
						break;
					}
					k++;
				}
				
				// Now the we need to locate the row whose testdata we have to read:
				while (rows.hasNext()) {
					Row row = rows.next();
					System.out.println("hello world in the excel " + row.getCell(col_num).getStringCellValue().equalsIgnoreCase(data));
					
					if (row.getCell(col_num).getStringCellValue().trim().equalsIgnoreCase(data.trim())) {
						// read the data of a particular row with a coulumm (add profile name)
						System.out.println(row.getCell(1));
						// now read all the cell data for that homepage Tc
						Iterator<Cell> td = row.cellIterator();
						while (td.hasNext()) {
							Cell cell = td.next();
							// System.out.print(td.next());
							if (cell.getCellType() == CellType.STRING)
							{
								test_data.add(cell.getStringCellValue());
							}
							else
								test_data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
						}

					}
				}
			}
		}
		return (test_data);

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TestDataReading s1 = new TestDataReading();
		ArrayList<String> test_data = s1.getdata("Rest AddBook ","RestAssured");
		System.out.println(test_data);

	}

}
