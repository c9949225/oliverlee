/** This file has been automatically generated 
    by the J2EE Design Pattern Generator tool.
    More info about the tool can be found at:
    http://sourceforge.net/projects/j2ee-dp-gen/. 
    You can read about the J2EE design patterns at:
    http://java.sun.com/blueprints/patterns/.
    
    @author of the tool Pawel Koziol, pkoziol@users.sourceforge.net
 */

/*This code is based on "Example 7.3: Implementing a Service Locator" from the Core J2EE Patterns book by Deepak Alur, John Crupi and Dan Malks*/

// imports
/*TODO: Update the necessary libraries in Project->Properties->Java Build Path */
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

// ...
public class ServiceLocator {

	private InitialContext initialContext;
	private Map cache;

	private static ServiceLocator _instance;

	static {
		try {
			_instance = new ServiceLocator();
		} catch (ServiceLocatorException se) {
			System.err.println(se);
			se.printStackTrace(System.err);
		}
	}

	/**
	 * the private constructor
	 */
	private ServiceLocator() throws ServiceLocatorException {
		try {
			initialContext = new InitialContext();
			cache = Collections.synchronizedMap(new HashMap());
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (Exception e) {
			throw new ServiceLocatorException(e);
		}
	}

	/**
	 * Returns the only instance of the Service Locator class
	 * 
	 * @return the only instance of the Service Locator class
	 */
	static public ServiceLocator getInstance() {
		return _instance;
	}

	// implement lookup methods here

}
