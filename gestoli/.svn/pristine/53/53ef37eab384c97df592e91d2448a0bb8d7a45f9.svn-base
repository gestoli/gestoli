package es.caib.gestoli.front.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Util
 * @author obarnes
 *
 */
public class Util {


	// Metodo para validar correo electronico
	public static boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;        
		pat = Pattern.compile(".+@.+\\.[a-z]+");
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}        
	}


	// Metodo para validar correo electronico
	public static boolean isHora(String correo) {
		Pattern pat = null;
		Matcher mat = null;        
		pat = Pattern.compile("^(0[1-9]|1\\d|2[0-3]):([0-5]\\d)$");								
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}        
	}


	// Metodo para validar que un campo sea numérico
	public static boolean isNumerico(String campo) {
		return Pattern.matches("^(\\d+)$", campo);
	}


	// Metodo para validar que un campo sea fecha 4 digitos
	public static boolean isFechaCuatroDigitos(String campo) {
		return Pattern.matches("^(\\d{4})$", campo);
	}


	// Metodo para validar que un campo sea fecha 4 digitos
	public static boolean isFechaCuatroDigitos(Integer campo) {
		String sCampo = campo.toString();
		return Pattern.matches("^(\\d{4})$", sCampo);
	}


	// Metodo para validar que un campo sea nif
	public static boolean isNif(String nif) {
		return Pattern.matches("^(\\d+[A-Za-z]?)$", nif);
	}


	// Metodo para validar que una fecha tenga el formato correcto
	public static boolean isDataCorrecta(String fecha, String formato) {
		boolean valido = true;
		if (fecha == null || fecha.equals("")) {
			valido = false;
			return (valido);
		}
		try {
			(new SimpleDateFormat(formato)).parse(fecha);
		} catch (Exception e) {
			valido = false;
		}
		return (valido);
	}


	/**
	 * Method 'parseContrasenya'
	 * @param username
	 * @param password
	 * @param md5String
	 * @return
	 */
	public static String parseContrasenya(String username, String contrasenya, String md5String) {
		String ret = "";

		if (md5String == null) {
			ret = contrasenya;
			ret = (new MD5(ret)).asHex();
		} else if (!md5String.equals("")) {
			ret = replaceString(md5String, "%%USER%%", username);
			ret = replaceString(ret, "%%PASSWORD%%", contrasenya);
			ret = (new MD5(ret)).asHex();
		} else {
			//plain
			ret = contrasenya;
		}

		return ret;
	}


	/**
	 * Method 'parseContrasenya'
	 * @param password
	 * @return
	 */
	public static String parseContrasenya(String contrasenya) {
		return parseContrasenya(null, contrasenya, null);
	}


    /**
     * Replace a string with another string inside a larger string, for
     * all of the search string.
     * @param text String to do search and replace in
     * @param repl String to search for
     * @param with String to replace with
     * @return String with all values replaced
     */
    public static String replaceString(String text, String repl, String with) {
        return replaceString(text, repl, with, -1);
    }
    public static String replaceString(String text, String repl, String with, String n) {
        return replaceString(text, repl, with, Integer.parseInt(n));
    }


    /**
     * Replace a string with another string inside a larger string, for
     * the first n values of the search string.
     * @param text String to do search and replace in
     * @param repl String to search for
     * @param with String to replace with
     * @param n    int    values to replace
     * @return String with n values replacEd
     */
    public static String replaceString(String text, String repl, String with, int max) {
        if(text == null) {
            return null;
        }
 
        StringBuffer buffer = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        while( (end = text.indexOf(repl, start)) != -1 ) {
            buffer.append(text.substring(start, end)).append(with);
            start = end + repl.length();
 
            if(--max == 0) {
                break;
            }
        }
        buffer.append(text.substring(start));
 
        return buffer.toString();      
    }
    
    /**
     * Retorna el número de dies entre dues dates
     * @param d1
     * @param d2
     * @return
     */
    public static int daysBetween(Date d1, Date d2){
    	final int MILISEGONS_PER_DIA = 1000 * 60 * 60 * 24;
        return (int)( (d2.getTime() - d1.getTime()) / MILISEGONS_PER_DIA);
    }
    
    /**
     * Retorna una data formatejada
     * @param data
     * @return
     */
	public static String getDataFormat(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}


}
