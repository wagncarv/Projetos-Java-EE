package br.com.dev.loucademia.application.util;

/**
 * 
 * @author Wagner Carvalho
 * Utilitarian class for validations
 */
public class Validation {

	/**
	 * 
	 * @param Object obj
	 * @return void
	 * 
	 * Assures if a given object is not null or empty
	 */
	public static void assertNotEmpty(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			if (StringUtils.isEmpty(s)) {
				throw new ValidationException("O texto não pode ser nulo");
			}
		}

		if (obj == null) {
			throw new ValidationException("Valor não pode ser nulo");
		}
	}

}
