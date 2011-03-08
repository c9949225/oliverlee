package net.oliver.olframework.util.filesystem.xml.dom;

/**   
  *   Created   by   IntelliJ   IDEA.   
  *   User:   Dickensi   Wen   Dickensi@hotmail.com   
  *   Date:   Jan   14,   2003   
  *   Time:   10:41:24   PM   
  *   To   change   this   template   use   Options   |   File   Templates.   
  */   
import   org.dom4j.*;   
import   org.dom4j.io.DOMReader;   
  
import   javax.xml.parsers.DocumentBuilderFactory;   
import   javax.xml.parsers.DocumentBuilder;   
  
//import   com.dickensi.util.Outer;   
//import   com.corecraft.db.xml.XMLQuery;   
  
public   class   XMLMethodFactory   {   
        private   static   DocumentBuilderFactory   dbf   =   null;   
        private   static   DocumentBuilder   builder   =   null;   
        private   static   org.dom4j.Document   domTemplate   =   null;   
//        private   static   DocumentFactory   df   =   DocumentFactory.getInstance();   
        private   static   DOMReader   domReader   =   new   DOMReader();   
  
        static     {   
                try   {   
//                        Outer.trace("Parpare   to   initial   Dom");   
                        dbf   =   DocumentBuilderFactory.newInstance();   
                        builder   =   dbf.newDocumentBuilder();   
//                        domTemplate   =   XMLQuery.createDom();   
//                        Outer.trace("Successed   in   initial   Dom");   
                }   catch   (Exception   e)   {   
  
                }   
        }   
  
        static   public   org.dom4j.Document   buildDocument(String   buffer){   
                //Hel   
                Document   dom   =   null;   
                try{   
                        dom   =   DocumentHelper.parseText(buffer);   
                }catch(Exception   e){dom   =   null;}   
                return   dom;   
        }   
  
        static   public   org.dom4j.Document   loadDocument(String   pathName){   
                Document   dom   =   null;   
                try{   
                        dom   =   domReader.read(builder.parse(pathName));   
                }catch(Exception   e){dom   =   null;}   
                return   dom;   
        }   
  
}   
