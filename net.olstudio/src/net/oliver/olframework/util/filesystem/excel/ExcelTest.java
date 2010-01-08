package net.oliver.olframework.util.filesystem.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelTest {
	
	public static void main(String[] args)
	{
		 	HSSFWorkbook wb = new HSSFWorkbook();
		    HSSFSheet sheet = wb.createSheet();
		    HSSFRow row = sheet.createRow((short) 0);
		    HSSFCell cell = row.createCell((short) 0);
		    cell.setCellValue("Default Palette");

		    //apply some colors from the standard palette,
		    // as in the previous examples.
		    //we'll use red text on a lime background

		    HSSFCellStyle style = wb.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.LIME.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    HSSFFont font = wb.createFont();
		    font.setColor(HSSFColor.RED.index);
		    style.setFont(font);

		    cell.setCellStyle(style);
		    try {
			    //save with the default palette
			    FileOutputStream out = new FileOutputStream("D:\\default_palette.xls");
			    wb.write(out);
			    out.close();
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	 	/*HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow((short) 1);

	    // Create a new font and alter it.
	    HSSFFont font = wb.createFont();
	    font.setFontHeightInPoints((short)24);
	    font.setFontName("Courier New");
	    font.setItalic(true);
	    // 穿条线过去
	    font.setStrikeout(true);

	    // Fonts are set into a style so create a new one to use.
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setFont(font);

	    // Create a cell and put a value in it.
	    HSSFCell cell = row.createCell((short) 1);
	    cell.setCellValue("This is a test of fonts");
	    cell.setCellStyle(style);*/
	
	/* 合并单元格
	 
		HSSFRow row = sheet.createRow((short) 1);
	    HSSFCell cell = row.createCell((short) 1);
	    cell.setCellValue("This is a test of merging");
	    // 从第1行开始,从第3个字段开始,垂直合并4行,平行合并5行
	    sheet.addMergedRegion(new Region(1,(short)3,4,(short)5));
    
    */
    
	/**
     * Creates a cell and aligns it a certain way.
     * 
     *  createCell(wb, row, (short) 0, HSSFCellStyle.ALIGN_CENTER);
     *  createCell(wb, row, (short) 1, HSSFCellStyle.ALIGN_CENTER_SELECTION);
     *  createCell(wb, row, (short) 2, HSSFCellStyle.ALIGN_FILL);
     *  createCell(wb, row, (short) 3, HSSFCellStyle.ALIGN_GENERAL);
     *  createCell(wb, row, (short) 4, HSSFCellStyle.ALIGN_JUSTIFY);
     *  createCell(wb, row, (short) 5, HSSFCellStyle.ALIGN_LEFT);
     *  createCell(wb, row, (short) 6, HSSFCellStyle.ALIGN_RIGHT);
     *
     * @param wb        the workbook
     * @param row       the row to create the cell in
     * @param column    the column number to create the cell in
     * @param align     the alignment for the cell.
     */
    private static void createCell(HSSFWorkbook wb, HSSFRow row, short column, short align)
    {
        HSSFCell cell = row.createCell(column);
        cell.setCellValue("Align It");
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(align);
        cell.setCellStyle(cellStyle);
    }

	public static void workBorders()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow((short) 1);

	    // Create a cell and put a value in it.
	    HSSFCell cell = row.createCell((short) 1);
	    cell.setCellValue(4);

	    // Style the cell with borders all around.
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setLeftBorderColor(HSSFColor.GREEN.index);// 绿色左边框
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setRightBorderColor(HSSFColor.BLUE.index);// 蓝色右边框
	    style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);// 上虚线
	    style.setTopBorderColor(HSSFColor.BLACK.index);
	    cell.setCellStyle(style);

	    // Write the output to a file
		try {
			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
