package br.com.ax.detran.detran.Util;

import org.apache.commons.lang3.text.WordUtils;

@SuppressWarnings("deprecation")
public class Helper {
	public static boolean validNumber(String valor) {
		if (!valor.isEmpty() && !valor.equalsIgnoreCase(null) && valor.matches("[0-9]*")) {
			return true;
		}
		return false;
	}

	public static String formatText(String valor) {
		// return valor.toUpperCase().charAt(0) +
		// valor.substring(1,valor.length()).toLowerCase();
		return WordUtils.capitalizeFully(valor);
	}
}