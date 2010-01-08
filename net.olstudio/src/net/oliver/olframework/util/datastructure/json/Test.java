package net.oliver.olframework.util.datastructure.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <title></title>
 * <description>
 * 
 * Json-lib的依赖项:
 * jakarta commons-lang 2.3
 * jakarta commons-beanutils 1.7.0
 * jakarta commons-collections 3.2
 * jakarta commons-logging 1.1
 * ezmorph 1.0.4
 * 
 * </description>
 * @author 李甫
 * <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0
 * 2008-4-24 上午11:01:27
 */
public class Test
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        System.out.println("JSONArray:boolean array...");
//        boolean[] boolArray = new boolean[]{ true, false, true };
//        JSONArray jsonArray = JSONArray.fromObject(boolArray);
//        System.out.println(jsonArray);
//        
//        System.out.println("JSONArray:string list...");
//        List list = new ArrayList();  
//        list.add( "first" );  
//        list.add( "second" );  
//        jsonArray = JSONArray.fromObject( list );  
//        System.out.println( jsonArray );  
//        
//        System.out.println("JSONArray:inverse direction...");
//        jsonArray = JSONArray.fromObject( "['json','is','easy']" );  
//        System.out.println( jsonArray ); 
//        
//        Map map = new HashMap();  
//        map.put( "name", "json" );  
//        map.put( "bool", Boolean.TRUE );  
//        map.put( "int", new Integer(1) );  
//        map.put( "arr", new String[]{"a","b"} );  
//        map.put( "func", "function(i){ return this.arr[i]; }" );  
//        JSONObject jsonObject = JSONObject.fromObject( map );  
//        System.out.println( jsonObject );  
        
    	JSONObject obj = new JSONObject();
    	obj.put("a", "1");
    	obj.put("b", "x");
    	JSONObject obj2 = new JSONObject();
    	obj2.put("e", "e");
    	obj.put("c", obj2);
    	
    	
//        System.out.println("Java Object to JSONObject...");
//        JSONObject jsonObject = JSONObject.fromObject( new TestBean() );  
        System.out.println( obj.toString() );  
        
        
        // From JSON to Beans
        
        
        
    }
}
