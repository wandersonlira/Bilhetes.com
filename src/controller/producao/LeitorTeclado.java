package controller.producao;

import java.util.Scanner;

public class LeitorTeclado {
	

	private static Scanner leitor = new Scanner(System.in);
	
	
	public static Integer getInputInteger() {
		
		Integer inputLeitor = leitor.nextInt();
		return inputLeitor;	
	}
	
	
	public static String getInputLine() {
		String inputLeitor = leitor.nextLine();
		return inputLeitor;
	}
	
	
	public static String getInput() {
		String inputLeitor = leitor.next();
		return inputLeitor;
	}
	
	
	public static void clearInput() {
		leitor.nextLine();
	}
	
	
	public static void closeInput() {
			leitor.close();
	}

}
