package com.nami.hamakko.dto;

import com.nami.hamakko.constance.EnPlaceKubun;
import com.nami.hamakko.constance.EnShisetsuShousai;
import com.nami.hamakko.constance.EnShisetsuShousaiKind;
import com.nami.hamakko.constance.EnTimeKubun;

public class YoyakuRegistInfo {

	public YoyakuRegistInfo(String yoyakuDate, String yoyakuDay, EnTimeKubun enTimeKubun, EnPlaceKubun placeKubun,
			EnShisetsuShousai enShisetsuShousai, EnShisetsuShousaiKind enShisetsuShousaiKind){
		this.yoyakuDate  = yoyakuDate;
		this.yoyakuDay = yoyakuDay;
		this.enTimeKubun = enTimeKubun;
		this.placeKubun = placeKubun;
		this.enShisetsuShousai = enShisetsuShousai;
		this.enShisetsuShousaiKind = enShisetsuShousaiKind;
	}
	/** 日付 */
	public String yoyakuDate;
	/** 日 */
	public String yoyakuDay;
	/** 時間 */
	public EnTimeKubun enTimeKubun;
	/** 室場 */
	public EnPlaceKubun placeKubun;
	/** 室場詳細 */
	public EnShisetsuShousai enShisetsuShousai;
	/** 室場詳細種類 */
	public EnShisetsuShousaiKind enShisetsuShousaiKind;

	/** 室場 */
	public String getShitsujo(){
		return placeKubun.getCode() + "0";
	}
	/** 室場詳細  */
	public String getShisetsuShousai(){
		switch(enShisetsuShousaiKind){
			case ALL:
				return enShisetsuShousai.getAllCort();
			case A:
				return enShisetsuShousai.getACort();
			case B:
				return enShisetsuShousai.getBCort();
			default:
				return null;
		}
	}

	public int getTimeKubunCode(){
		switch(placeKubun){
			case KANAGAWA:
				return enTimeKubun.getCode() - 1;
			default:
				return enTimeKubun.getCode();
		}
	}
}
