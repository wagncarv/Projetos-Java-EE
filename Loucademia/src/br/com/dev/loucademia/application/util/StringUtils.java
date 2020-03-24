package br.com.dev.loucademia.application.util;

/**
 * 
 * @author Wagner Carvalho
 * Utilitaria class
 */
public class StringUtils {

	/**
	 * 
	 * @param String s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}
		
		return s.trim().length() == 0;
	}
	
	/**
	 * 
	 * @param int value
	 * @param int finalSize
	 * @return String
	 */
	public static String leftZeroes (int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
		
	}
}
