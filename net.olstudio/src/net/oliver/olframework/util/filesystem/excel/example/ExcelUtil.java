package net.oliver.olframework.util.filesystem.excel.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.oliver.olframework.util.string.SJPStringUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;


/**
 * <title>Excel�����Ĺ�����</title> 
 * <description></description>
 * @author � 
 * <copyright>������ͬ�Ƽ����޷�չ��˾@2008</copyright>
 * @version 1.0 2008-4-16 ����07:34:46
 */

class Bean{
    String value;
    
    public Bean(String value)
    {
    	this.value = value;
    }
    
    public void setValue(String v)
    {
        value = v;
    }
    
    public String getValue()
    {
        return value;
    }
}
public abstract class ExcelUtil
{
    
    public static void main(String[] args)
    {
    	// 1����-����1 : �ֶ���
    	// value : bean��������
        String[] part1 = {"1����-����1:value","1����-����2:value"};
        String[] part2 = {"2����-����1:value","2����-����2:value","2����-����3:value"};
        String[] part3 = {"3����-����1:value","3����-����2:value","3����-����3:value","3����-����4:value"};
        
        Bean[] beans = new Bean[30];
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        
        for(int i=0;i<30;i++)
        {
        	beans[i] = new Bean(String.valueOf(i));
        	if(i<10)
        	{
        		list1.add(beans[i]);
        	}else if(i>10&&i<20)
        	{
        		list2.add(beans[i]);
        	}else{
        		list3.add(beans[i]);
        	}
        }
        
        ExcelContent c1 = new ExcelContent(part1,list1);
        ExcelContent c2 = new ExcelContent(part2,list2);
        ExcelContent c3 = new ExcelContent(part3,list3);
        
        
        try {
			genMultiPartFileFromList("D:/test.xls",new ExcelContent[]{c1,c2,c3});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExcelException e) {
			e.printStackTrace();
		}
    }
    
    public static void genMultiPartFileFromList(String path,ExcelContent[] contents) throws ExcelException, FileNotFoundException
    {
    	
//        if (Defense.isBlank(path))
//        {
//            throw new ExcelException("The output path is not exist!");
//        }
        
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path);
        HSSFSheet sheet = wb.createSheet("sheet_one");
        // ��������͵�Ԫ���ʽ
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����
        font.setFontName("����");
        HSSFCellStyle cellStyle = wb.createCellStyle();// ���õ�Ԫ���ʽ
        cellStyle.setFont(font);
        
        int row = 0;
        for(int j=0;j<contents.length;j++)
        {   
            HSSFRow headRow = sheet.createRow(row++);
            
            HSSFCell cell = null;
            for (int i = 0; i < contents[j].getHeaders().length; i++)
            {
                cell = headRow.createCell((short) i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString( contents[j].getHeaders()[i].substring(0,contents[j].getHeaders()[i].indexOf(":"))));
            }
            
            Object value = null;
            Class[] args = new Class[0];
            Object[] invoke_args = new Object[0];
            
            for (int i = 0; i < contents[j].getObjects().size(); i++)
            {
                HSSFRow contentRow = sheet.createRow(row++);// ����һ����ʾ����������
                
                for(int l=0;l<contents[j].getHeaders().length;l++) // ��ÿ��Bean ����Headerÿһ�������ʾ
                {
                    // ȡ��������Ӧ��������
                    String property = contents[j].getHeaders()[l].substring(contents[j].getHeaders()[l].indexOf(":")+1);
                    Method method;
                    try
                    {
                        // �õ� get_������ ����
                        method = contents[j].getObjects().get(i).getClass().getMethod(SJPStringUtil.startWithUpCase(property, "get"),args);
                        // ���� get_������ ����
                        value = method.invoke(contents[j].getObjects().get(i), invoke_args);
                        
                    } catch (SecurityException e)
                    {
                        throw new ExcelException("�����쳣:SecurityException");
                    } catch (NoSuchMethodException e)
                    {
                        throw new ExcelException("�����쳣:NoSuchMethodException");
                    } catch (IllegalArgumentException e)
                    {
                        throw new ExcelException("�����쳣:IllegalArgumentException");
                    } catch (IllegalAccessException e)
                    {
                        throw new ExcelException("�����쳣:IllegalAccessException");
                    } catch (InvocationTargetException e)
                    {
                        throw new ExcelException("�����쳣:InvocationTargetException");
                    }
                    
                    cell = contentRow.createCell((short)l);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new HSSFRichTextString(value != null? (String)value : ""));
                }
            }
        }
        
        try
        {
            wb.write(fileOut);

        } catch (IOException e)
        {
            throw new ExcelException("Can output the file");
        } finally
        {
            try
            {
                fileOut.close();
            } catch (IOException e)
            {
                throw new ExcelException("Can not close the FileOutputStream");
            }
        }
    }
    
    /**
     * Generate a Excel File from list's get method
     * @param path 
     *          path to be saved
     * @param header
     *          column header {ColumnName:name,ColumnName2:age,..}
     * @param list
     *          column value collection
     * @throws ExcelException
     * @throws FileNotFoundException
     */
    public static void generateFileFromList(String path, String[] header,
            List list,String title) throws ExcelException, FileNotFoundException
    {
//        if (Defense.isBlank(path))
//        {
//            throw new ExcelException("The output path is not exist!");
//        }
        
        //�����Ŀ¼�Ƿ���ڣ����������򴴽�Ŀ¼
        int end = path.lastIndexOf("/");
        String directory = path.substring(0, end);
        File d = new File(directory);// ��������outPathĿ¼��File���󣬲��õ�����һ������
        if (!d.exists())
        {// ���Ŀ���Ƿ����
            d.mkdirs();// ����Ŀ��Ŀ¼
        }
        
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path);
        HSSFSheet sheet = wb.createSheet("sheet_one");
        // ��������͵�Ԫ���ʽ
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����
        font.setFontName("����");
        HSSFCellStyle cellStyle = wb.createCellStyle();// ���õ�Ԫ���ʽ
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ���� 
		
		sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) (header.length-1))); //
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell headcell = titleRow.createCell((short) 0);
		headcell.setCellType(HSSFCell.CELL_TYPE_STRING);
		headcell.setCellStyle(cellStyle);
		headcell.setCellValue(new HSSFRichTextString(title));
        
        HSSFRow headRow = sheet.createRow(2);// ������һ���б�����
        HSSFCell cell = null;
        for (int i = 0; i < header.length; i++)
        {
            cell = headRow.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(header[i].substring(0,header[i].indexOf(":"))));
        }
        
        Object value = null;
        Class[] args = new Class[0];
        Object[] invoke_args = new Object[0];
        
        for (int i = 0; i < list.size(); i++)
        {
            HSSFRow contentRow = sheet.createRow(i + 3);// ����һ����ʾ����������
            
            for(int l=0;l<header.length;l++) // ��ÿ��Bean ����Headerÿһ�������ʾ
            {
                // ȡ��������Ӧ��������
                String property = header[l].substring(header[l].indexOf(":")+1);
                Method method;
                try
                {
                    // �õ� get_������ ����
                    method = list.get(i).getClass().getMethod(SJPStringUtil.startWithUpCase(property, "get"),args);
                    // ���� get_������ ����
                    value = method.invoke(list.get(i), invoke_args);
                    
                } catch (SecurityException e)
                {
                    throw new ExcelException("�����쳣:SecurityException");
                } catch (NoSuchMethodException e)
                {
                    throw new ExcelException("�����쳣:SecurityException");
                } catch (IllegalArgumentException e)
                {
                    throw new ExcelException("�����쳣:IllegalArgumentException");
                } catch (IllegalAccessException e)
                {
                    throw new ExcelException("�����쳣:IllegalAccessException");
                } catch (InvocationTargetException e)
                {
                    throw new ExcelException("�����쳣:InvocationTargetException");
                }
                
                cell = contentRow.createCell((short)l);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(value != null? (String)value : ""));
            }
        }
        
        try
        {
            wb.write(fileOut);

        } catch (IOException e)
        {
            throw new ExcelException("Can output the file");
        } finally
        {
            try
            {
                fileOut.close();
            } catch (IOException e)
            {
                throw new ExcelException("Can not close the FileOutputStream");
            }
        }
    }
    

    /**
     * by maronggang
     * @param path
     * @param header
     * @param list
     * @param title
     * @throws ExcelException
     * @throws FileNotFoundException
     */
    public static void TansferListFileFromList(String path, String[] header,
            List list,String title,String slgm,String slr,String slrq,String sljs,String zlzys) throws ExcelException, FileNotFoundException
    {
//    	if (Defense.isBlank(path)) {
//			throw new ExcelException("The output path is not exist!");
//		}
		
         //�����Ŀ¼�Ƿ���ڣ����������򴴽�Ŀ¼
        int end = path.lastIndexOf("/");
        String directory = path.substring(0, end);
        File d = new File(directory);// ��������outPathĿ¼��File���󣬲��õ�����һ������
        if (!d.exists())
        {// ���Ŀ���Ƿ����
            d.mkdirs();// ����Ŀ��Ŀ¼
        }

		HSSFWorkbook wb = new HSSFWorkbook();
		FileOutputStream fileOut = new FileOutputStream(path);
		HSSFSheet sheet = wb.createSheet("sheet_one");
		// ��������͵�Ԫ���ʽ
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����
		font.setFontName("����");
		HSSFCellStyle cellStyle = wb.createCellStyle();// ���õ�Ԫ���ʽ
		cellStyle.setFont(font);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����   
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ����
        
        sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) (header.length-1))); //
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell headcell = titleRow.createCell((short) 0);
		headcell.setCellType(HSSFCell.CELL_TYPE_STRING);
		headcell.setCellStyle(cellStyle);
		headcell.setCellValue(new HSSFRichTextString(title));

		// ������棺ȫ��       �����ˣ�ȫ��     ҵ�����ͣ� ����ҵ������
		HSSFRow headRow2 = sheet.createRow(2);
		HSSFCell cell = null;
		cell = headRow2.createCell((short) 0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("������棺"));
        cell = headRow2.createCell((short) 1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(slgm));
        cell = headRow2.createCell((short) 2);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("�����ˣ�"));
        cell = headRow2.createCell((short) 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(slr));
        cell = headRow2.createCell((short) 4);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("ҵ�����ͣ�"));
        cell = headRow2.createCell((short) 5);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("����ҵ������"));
        
        //�������ڣ�*******��*********����������� **  ������ҳ����****
        HSSFRow headRow3 = sheet.createRow(3);
		cell = headRow3.createCell((short) 0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("�������ڣ�"));
        cell = headRow3.createCell((short) 1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(slrq));
        cell = headRow3.createCell((short) 2);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("���������"));
        cell = headRow3.createCell((short) 3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(sljs));
        cell = headRow3.createCell((short) 4);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString("������ҳ����"));
        cell = headRow3.createCell((short) 5);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(zlzys));
		
		
		HSSFRow headRow = sheet.createRow(5);// ������һ���б�����
		for (int i = 0; i < header.length; i++) {
			cell = headRow.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(header[i].substring(0,
					header[i].indexOf(":"))));
		}

		Object value = null;
		Class[] args = new Class[0];
		Object[] invoke_args = new Object[0];

		for (int i = 0; i < list.size(); i++) {
			HSSFRow contentRow = sheet.createRow(i + 6);// ����һ����ʾ����������

			for (int l = 0; l < header.length; l++) // ��ÿ��Bean ����Headerÿһ�������ʾ
			{
				// ȡ��������Ӧ��������
				String property = header[l]
						.substring(header[l].indexOf(":") + 1);
				Method method;
				try {
					// �õ� get_������ ����
					method = list.get(i).getClass().getMethod(
							SJPStringUtil.startWithUpCase(property, "get"), args);
					// ���� get_������ ����
					value = method.invoke(list.get(i), invoke_args);

				} catch (SecurityException e) {
					throw new ExcelException("�����쳣:SecurityException");
				} catch (NoSuchMethodException e) {
					throw new ExcelException("�����쳣:SecurityException");
				} catch (IllegalArgumentException e) {
					throw new ExcelException("�����쳣:IllegalArgumentException");
				} catch (IllegalAccessException e) {
					throw new ExcelException("�����쳣:IllegalAccessException");
				} catch (InvocationTargetException e) {
					throw new ExcelException("�����쳣:InvocationTargetException");
				}

				cell = contentRow.createCell((short) l);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(
						value != null ? (String) value : ""));
			}
		}

		try {
			wb.write(fileOut);

		} catch (IOException e) {
			throw new ExcelException("Can output the file");
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				throw new ExcelException("Can not close the FileOutputStream");
			}
		}

        


    }
    
    /**
     * Generate a Excel File from String Array
     * 
     * @param path
     * @param header
     *            column header
     * @param rows
     *            column content
     * @throws ExcelException
     * @throws FileNotFoundException
     */
    public static void generateFileFromArray(String path, String[] header,
            String[][] rows) throws ExcelException, FileNotFoundException
    {
//        if (Defense.isBlank(path))
//        {
//            throw new ExcelException("The output path is not exist!");
//        }

        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path);
        HSSFSheet sheet = wb.createSheet("sheet_one");
        // ��������͵�Ԫ���ʽ
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����
        font.setFontName("����");
        HSSFCellStyle cellStyle = wb.createCellStyle();// ���õ�Ԫ���ʽ
        cellStyle.setFont(font);

        HSSFRow headRow = sheet.createRow(0);// ������һ���б�����
        HSSFCell cell = null;
        for (int i = 0; i < header.length; i++)
        {
            cell = headRow.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(header[i]));
        }

        for (int j = 0; j < rows.length; j++)
        {
            HSSFRow contentRow = sheet.createRow(j + 1);// ������һ���б�����
            for (int k = 0; k < rows[j].length; k++)
            {
                cell = contentRow.createCell((short) k);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(rows[j][k]));
            }
        }

        try
        {
            wb.write(fileOut);

        } catch (IOException e)
        {
            throw new ExcelException("Can output the file");
        } finally
        {
            try
            {
                fileOut.close();
            } catch (IOException e)
            {
                throw new ExcelException("Can not close the FileOutputStream");
            }
        }
    }
}
