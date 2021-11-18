package com.simonenogueira.os.controller.exception;

import java.io.Serializable;

public class FieldMessade implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String massage;

	public FieldMessade() {
		super();

	}

	public FieldMessade(String fieldName, String massage) {
		super();
		this.fieldName = fieldName;
		this.massage = massage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

}
