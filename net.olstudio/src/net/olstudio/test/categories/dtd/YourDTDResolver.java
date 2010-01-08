package net.olstudio.test.categories.dtd;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class YourDTDResolver implements EntityResolver {


	
	Logger logger = Logger.getLogger(YourDTDResolver.class);

	private String _configuredPath = "net/olstudio/test/categories/dtd/";
	
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {

		System.out.println("publicId:" + publicId);
		System.out.println("systemId:" + systemId);

		
		if (systemId != null) {
			String dtdname = _configuredPath + systemId.substring(systemId.lastIndexOf("/")+1);
			InputStream dtdStream = this.getClass().getClassLoader().getResourceAsStream(dtdname);
			if (dtdStream == null) {
				logger.debug(systemId + " not found in classpath");
				return null;
			} else {
				logger.debug("found " + systemId + " in classpath");
				InputSource source = new InputSource(dtdStream);
				source.setPublicId(publicId);
				source.setSystemId(systemId);
				return source;
			}

		} else {
			// use the default behaviour
			return null;
		}

	}
}