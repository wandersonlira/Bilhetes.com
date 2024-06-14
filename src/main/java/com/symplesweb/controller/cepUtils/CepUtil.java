package com.symplesweb.controller.cepUtils;

import com.symplesweb.controller.cepUtils.exception.ViaCepFormatException;

public class CepUtil {
	
	private static void cepValid(String cep) {
		if (cep.length() > 8) {
			System.out.println("SOU MAIOR QUE 8");
			throw new ViaCepFormatException("CEP fora do formato ou números a MAIS");
		}
		
		else if (cep.length() < 8) {
			System.out.println("SOU MENOR QUE 8");
			throw new ViaCepFormatException("CEP fora do formato ou número a MENOS");
		}
	}
	
	
	
	public static String removeMascaraCep(String cep) {
		cepValid(cep.replace("-", ""));
		System.out.println("DIGITEI: " + cep);
		return cep.replace("-", "");
	}
	
	
	public static String mascararCep(String cep) {
		cepValid(cep);
		return cep.substring(0, 5) + "-" + cep.substring(5);
		
		
	}



}
