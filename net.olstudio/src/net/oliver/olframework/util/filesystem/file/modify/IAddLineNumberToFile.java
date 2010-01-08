package net.oliver.olframework.util.filesystem.file.modify;

import java.io.File;

/**
 * <DL>
 * <DT><B>为文件添加行号</B></DT>
 * <p>
 * <DD>Description</DD>
 * </DL>
 * <p>
 * 
 * <DL>
 * <DT><B>Usage </B></DT>
 * <p>
 * <DD>Usage Details.</DD>
 * </DL>
 * <p>
 * 
 * @author Oliver Lee &li.fu@agree.com.cn&gt;
 * @copyright Agree Tech Co.
 * @version 1.00 2008-11-20下午03:46:55
 */
public interface IAddLineNumberToFile {
	
	public String addLineNumberFor(File file);
	
	public String addLineNumberFor(String file);

}
