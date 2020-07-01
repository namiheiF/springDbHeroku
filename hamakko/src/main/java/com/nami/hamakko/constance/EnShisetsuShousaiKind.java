package com.nami.hamakko.constance;

public enum EnShisetsuShousaiKind {
	/** 全面*/
	ALL("0"),
	/** A面 */
	A("1"),
	/** B面 */
	B("2");

	private String code;

	private EnShisetsuShousaiKind(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

}
