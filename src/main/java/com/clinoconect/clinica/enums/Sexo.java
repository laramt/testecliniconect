package com.clinoconect.clinica.enums;

public enum Sexo {

	FEMININO(0),
	MASCULINO(1);
	
	private int code;

	private Sexo(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Sexo valueOf(int code) {
		for (Sexo value : Sexo.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo invalido");
	}
	
}
