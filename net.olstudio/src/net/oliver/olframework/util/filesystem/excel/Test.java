package net.oliver.olframework.util.filesystem.excel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Test
{

     class Samplebean
    {
        public Samplebean()
        {
            
        }
        public String getValue1(){
            return "value1";
        }
        
        public String getValue2(){
            return "value2";
        }
        
        public String getValue3(){
            return "value3";
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        try
//        {
//            String[] header = {"姓名","年龄","住址"};
//            String[][] content = {{"oliver","age","address"},{"oliver","age","address"},{"oliver","age","address"}};
//            ExcelUtil.generateFile("D:\\test.xls", header, content);
//            
//        } catch (FileNotFoundException e)
//        {
//            System.out.println(e.getMessage());
//        } catch (ExcelException e)
//        {
//            System.out.println(e.getMessage());
//        }
        Test test = new Test();
        test.test();
       
    }
    
    public void test(){
        String[] header = {"姓名:value3","年龄:value1","住址:value2"};
        List list = new ArrayList();
        Samplebean bean = new Samplebean();
        
        list.add(bean);
        list.add(bean);
        list.add(bean);
        
        try
        {
            ExcelUtil.generateFileFromList("D:\\test.xls", header, list);
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (ExcelException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
