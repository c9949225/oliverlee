/** This file has been automatically generated 
    by the J2EE Design Pattern Generator tool.
    More info about the tool can be found at:
    http://sourceforge.net/projects/j2ee-dp-gen/. 
    You can read about the J2EE design patterns at:
    http://java.sun.com/blueprints/patterns/.
    
    @author of the tool Pawel Koziol, pkoziol@users.sourceforge.net
 */

/**
 * @author Pawel Koziol
 * 
 */
public class ServiceLocatorException extends Exception {
	/**
	 * Creates ServiceLocatorException with a message
	 * 
	 * @param mesg
	 *            the exception message
	 */
	public ServiceLocatorException(String mesg) {
		super(mesg);
	}

	/**
	 * Creates a ServiceLocatorException basing on another exception
	 * 
	 * @param e
	 *            another exception used to create the ServiceLocatorException
	 */
	public ServiceLocatorException(Throwable e) {
		super(e);
	}

}
