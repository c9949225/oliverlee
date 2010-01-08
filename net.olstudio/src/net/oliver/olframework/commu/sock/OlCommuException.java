package net.oliver.olframework.commu.sock;

/**
 * <DL>
 * <DT><B>Tcp/Ip通讯顶级异常</B></DT>
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
 * @version 1.00 2008-11-21下午03:23:30
 */
public class OlCommuException extends Exception {

	private static final long serialVersionUID = 1L;

	public OlCommuException() {
		super();
	}

	public OlCommuException(String message, Throwable cause) {
		super(message, cause);
	}

	public OlCommuException(String message) {
		super(message);
	}

	public OlCommuException(Throwable cause) {
		super(cause);
	}

}
