package com.nami.hamakko.dto;

import com.nami.hamakko.constance.EnPlaceKubun;

public class YoyakuDateTimeData {

	public YoyakuDateTimeData(String yoyakuDate, String yoyakuDay, String yoyakuTimme, EnPlaceKubun placeKubun){
		this.yoyakuDate  = yoyakuDate;
		this.yoyakuDay = yoyakuDay;
		this.yoyakuTime = yoyakuTimme;
		this.placeKubun = placeKubun;
	}
	/** 日付 */
	public String yoyakuDate;
	/** 日 */
	public String yoyakuDay;
	/** 時間 */
	public String yoyakuTime;
	/** 室場 */
	public EnPlaceKubun placeKubun;
	/** 室場 */
	public String getShitsujo(){
		if(EnPlaceKubun.HIRANUMA == placeKubun) {
			return placeKubun.getCode() + "2";
		} else {
			return placeKubun.getCode() + "0";
		}
	}
}
