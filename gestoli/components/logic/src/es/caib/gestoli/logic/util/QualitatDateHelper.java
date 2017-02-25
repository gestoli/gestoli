package es.caib.gestoli.logic.util;

import java.util.Calendar;
import java.util.Date;

public class QualitatDateHelper {

	public static Date afegirData(Date data, String freq) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		if ("diari".equals(freq))			cal.add( Calendar.DATE, 1);
		else if ("setmanal".equals(freq))	cal.add( Calendar.DATE, 7);
		else if ("quinzenal".equals(freq))	cal.add( Calendar.DATE, 15);
		else if ("mensual".equals(freq))	cal.add( Calendar.MONTH, 1);
		else if ("bimensual".equals(freq))	cal.add( Calendar.MONTH, 2);
		else if ("trimestral".equals(freq))	cal.add( Calendar.MONTH, 3);
		else if ("semestral".equals(freq))	cal.add( Calendar.MONTH, 6);
		else if ("anual".equals(freq))		cal.add( Calendar.YEAR, 1);
		else if ("bianual".equals(freq))	cal.add( Calendar.YEAR, 2);
		else if ("trianual".equals(freq))	cal.add( Calendar.YEAR, 3);
		
		return cal.getTime();
	}
	
	
	public static Date restarData(Date data, String freq) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		if ("diari".equals(freq))			cal.add( Calendar.DATE, -1);
		else if ("setmanal".equals(freq))	cal.add( Calendar.DATE, -7);
		else if ("quinzenal".equals(freq))	cal.add( Calendar.DATE, -15);
		else if ("mensual".equals(freq))	cal.add( Calendar.MONTH, -1);
		else if ("bimensual".equals(freq))	cal.add( Calendar.MONTH, -2);
		else if ("trimestral".equals(freq))	cal.add( Calendar.MONTH, -3);
		else if ("semestral".equals(freq))	cal.add( Calendar.MONTH, -6);
		else if ("anual".equals(freq))		cal.add( Calendar.YEAR, -1);
		else if ("bianual".equals(freq))	cal.add( Calendar.YEAR, -2);
		else if ("trianual".equals(freq))	cal.add( Calendar.YEAR, -3);
		
		return cal.getTime();
	}
	
}
