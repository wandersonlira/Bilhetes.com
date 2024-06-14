package com.symplesweb.model.entities.enums;

public enum CategoriaStatus {
	
	FESTAS_E_SHOWS(1),
	CARNAVAL(2),
	TEATROS_E_ESPETÁCULOS(3),
	CONGRESSOS_E_PALETRAS(4),
	PESSEIOS_E_TOURS(5),
	INFANTIL(6),
	CURSOS_E_WORKSHOPS(7),
	ESPORTE(8),
	GASTRONOMIA(9),
	EVENTOS_ONLINE(10),
	STAND_UP_COMEDY(11),
	RELIGIAO_E_ESPIRITUALIDADE(12),
	PRICE(13),
	TECNILOGIA(14);
	
	private int code;
	
	

	private CategoriaStatus(int code) {
		this.code = code;
	}



	public int getCode() {
		return code;
	}
	
	
	
	public static CategoriaStatus valueOf(int code) {
		
		for (CategoriaStatus value : CategoriaStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid CategoriaStatus code");
	}
	

}
