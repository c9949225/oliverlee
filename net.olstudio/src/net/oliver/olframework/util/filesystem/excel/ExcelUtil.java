package net.oliver.olframework.util.filesystem.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.oliver.olframework.util.string.Defense;
import net.oliver.olframework.util.string.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * <title>Excel操作的工具类</title>
 * <description></description>
 * @author 李甫
 * <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0
 * 2008-4-16 下午07:34:46
 */
public class ExcelUtil
{
   /**
    * Generate a Excel File
    * @param path
    * @param header column header
    * @param rows column content
    * @throws ExcelException
    * @throws FileNotFoundException
    */
    public static void generateFileFromArray(String path, String[] header,
            String[][] rows) throws ExcelException, FileNotFoundException
    {
        if (Defense.isBlank(path))
        {
            throw new ExcelException("The output path is not exist!");
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path);
        HSSFSheet sheet = wb.createSheet("sheet_one");
        // 设置字体和单元格格式
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体
        font.setFontName("宋体");
        HSSFCellStyle cellStyle = wb.createCellStyle();// 设置单元格格式
        cellStyle.setFont(font);

        HSSFRow headRow = sheet.createRow(0);//创建第一行列表列名
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
            HSSFRow contentRow = sheet.createRow(j + 1);//创建第一行列表列名
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
    
    /**
     * Generate a Excel File from list's get method
     * @param path 
     *          path to be saved
     * @param header
     *          column header
     * @param list
     *          column value collection
     * @throws ExcelException
     * @throws FileNotFoundException
     */
    public static void generateFileFromList(String path, String[] header,
            List list) throws ExcelException, FileNotFoundException
    {
        if (Defense.isBlank(path))
        {
            throw new ExcelException("The output path is not exist!");
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path);
        HSSFSheet sheet = wb.createSheet("sheet_one");
        // 设置字体和单元格格式
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体
        font.setFontName("宋体");
        HSSFCellStyle cellStyle = wb.createCellStyle();// 设置单元格格式
        cellStyle.setFont(font);

        HSSFRow headRow = sheet.createRow(0);// 创建第一行列表列名
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
            HSSFRow contentRow = sheet.createRow(i + 1);// 创建一个显示用于内容行
            
            for(int l=0;l<header.length;l++) // 对每个Bean 依据Header每一项进行显示
            {
                // 取得列名对应的属性名
                String property = header[l].substring(header[l].indexOf(":")+1);
                Method method;
                try
                {
                    // 得到 get_属性名 方法
                    method = list.get(i).getClass().getMethod(StringUtil.startWithUpCase(property, "get"),args);
                    // 调用 get_属性名 方法
                    value = method.invoke(list.get(i), invoke_args);
                    
                } catch (SecurityException e)
                {
                    throw new ExcelException("");
                } catch (NoSuchMethodException e)
                {
                    throw new ExcelException("");
                } catch (IllegalArgumentException e)
                {
                    throw new ExcelException("");
                } catch (IllegalAccessException e)
                {
                    throw new ExcelException("");
                } catch (InvocationTargetException e)
                {
                    throw new ExcelException("");
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
}
