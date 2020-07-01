package com.nami.hamakko.constance;

public enum EnTimeKubun {
	/** 0730-0900*/
	_0730("07300900", 1),
	/** 0900-1100 */
	_0900("09001100", 2),
	/** 1100-1300 */
	_1100("11001300", 3),
	/** 1300-1500 */
	_1300("13001500", 4),
	/** 1500-1700 */
	_1500("15001700", 5),
	/** 1700-1900*/
	_1700("17001900", 6),
	/** 1900-2100*/
	_1900("19002100", 7),
	/** 2100-2300 */
	_2100("21002300", 8),
	/** 0900-1200 */
	_0900_SHIMIZU("09001200", 1),
	/** 1200-1500 */
	_1200_SHIMIZU("12001500", 2),
	/** 1500-1800 */
	_1500_SHIMIZU("15001800", 3),
	/** 1800-2100 */
	_1800_SHIMIZU("18002100", 4);

	private String time;
	private int code;

	private EnTimeKubun(String time, int code){
		this.time = time;
		this.code = code;
	}

	public String getTime(){
		return time;
	}

	public int getCode(){
		return code;
	}

}
