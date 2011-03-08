package net.olstudio.test.categories;

import java.net.URL;
import java.net.URLDecoder;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url = Test.class.getProtectionDomain().getCodeSource().getLocation();
		String path = URLDecoder.decode(url.getFile());
		System.out.println(path);
//		Map map = new ConcurrentHashMap();
//		System.out.println(map.get(null));
//		
//		Date date = new Date("");
	}

}
