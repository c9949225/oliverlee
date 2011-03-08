package net.oliver.olframework.lang.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dateStr = "23:19";
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
		try {
			Date date = dateFormat.parse(dateStr);
			
			System.out.println(date);
		} catch (ParseException e) {
		}

	}

}
