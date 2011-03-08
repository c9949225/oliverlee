package net.oliver.olframework.util.filesystem.file.detect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 当配置文件有修改时，重新加载配置文件
 * 
 * @author oliver
 *
 */
public class ConfigDetector {

	 private static Properties props = null;       
	  private static File configFile = null;   
	  private static long fileLastModified = 0L;   
	     
	  private static void init(){   
	      URL url = ConfigDetector.class.getClassLoader().getResource("global.properties");  
	      configFile = new File(url.getFile());   
	      fileLastModified = configFile.lastModified();         
	      props = new Properties();;   
	      load();
	  }   
	     
	  private static void load(){    
	      try {   
	          props.load(new FileInputStream(configFile));   
	          fileLastModified = configFile.lastModified();   
	      } catch (IOException e){               
	          throw new RuntimeException(e);   
	      }   
	  }   

	  public static String getConfig(String key){   
	      if ((configFile == null) || (props == null)) init();   
	      if (configFile.lastModified() > fileLastModified); load();   
	      return props.getProperty(key);   
	  }   
	
}
