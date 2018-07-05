package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtils {
	
    private static final String EXCEL_FILE_LOCATION = "C:\\temp\\FoxShows.xls";

	public static void writeExcel(List<String> shows) {

		WritableWorkbook workbook = null;
		
		try {

			workbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

			WritableSheet excelSheet = workbook.createSheet("Sheet 1", 0);

			Label label1 = new Label(0, 0, "Show");
			excelSheet.addCell(label1);

			for (int i=0; i < shows.size(); i++) {
				Label showLabel = new Label(0, i + 1, shows.get(i));
				excelSheet.addCell(showLabel);           	
			}

			workbook.write();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} finally {

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}


	}

	public static void writeExcelDuplicate(int row, String pages) {

		WritableWorkbook workbook = null;
		
		try {

			workbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

			WritableSheet excelSheet = workbook.createSheet("Sheet 1", 0);

			Label label1 = new Label(0, 0, "Show");
			Label label2 = new Label(1, 0, "Duplicate Record");
			excelSheet.addCell(label1);
			excelSheet.addCell(label2);

			Label pageLabel = new Label(1, row, pages);
			excelSheet.addCell(pageLabel);           	

			workbook.write();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} finally {

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}


	}
	
	public static List<String> readExcel() {

		Workbook workbook = null;
		List<String> shows = new ArrayList<String>();
		
		try {
			workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));

			Sheet sheet = workbook.getSheet(0);
			int columns = sheet.getColumns();
			for (int i=1; i < sheet.getRows(); i++) {
				String cell = sheet.getCell(0, i).getContents();
				if (columns > 1) {
					cell += " > " + sheet.getCell(1, i).getContents();
				}
				shows.add(cell);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {

			if (workbook != null) {
				workbook.close();
			}
		}
		return shows;

	}	

	
}
