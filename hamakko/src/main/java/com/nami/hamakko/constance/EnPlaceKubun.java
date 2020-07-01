package com.nami.hamakko.constance;

public enum EnPlaceKubun {
	/** 鶴見スポーツセンター */
	TSURUMI("130", "鶴見スポーツセンター"),
	/** 神奈川スポーツセンター */
	KANAGAWA("180", "神奈川スポーツセンター"),
	/** 平沼体育館ー */
	HIRANUMA("190", "平沼体育館"),
	/** 西スポーツセンター */
	NISHI("220", "西スポーツセンター"),
	/** 中スポーツセンター */
	NAKA("270", "中スポーツセンター"),
	/** 南スポーツセンター */
	MINAMI("330", "南スポーツセンター"),
	/** 港南スポーツセンター */
	KOUNAN("370", "港南スポーツセンター"),
	/** 保土ケ谷スポーツセンター */
	HODOGAYA("400", "保土ケ谷スポーツセンター"),
	/** 旭スポーツセンター */
	ASAHI("440", "旭スポーツセンター"),
	/** 磯子スポーツセンター */
	ISOGO("480", "磯子スポーツセンター"),
	/** 金沢スポーツセンター */
	KANAZAWA("540", "金沢スポーツセンター"),
	/** 港北スポーツセンター */
	KOUHOKU("580", "港北スポーツセンター"),
	/** 緑スポーツセンター */
	MIDORI("630", "緑スポーツセンター"),
	/** 青葉スポーツセンター */
	AOBA("670", "青葉スポーツセンター"),
	/** 都筑スポーツセンター */
	TUDUKI("710", "都筑スポーツセンター"),
	/** 戸塚スポーツセンター */
	TOTSUKA("760", "戸塚スポーツセンター"),
	/** 栄スポーツセンター */
	SAKAE("800", "栄スポーツセンター"),
	/** 泉スポーツセンター */
	IZUMI("830", "泉スポーツセンター"),
	/** 瀬谷スポーツセンター */
	SEYA("870", "瀬谷スポーツセンター"),
	/** 清水ケ丘公園体育館 */
	SHIMIZUGAOKA("", "清水ケ丘公園体育館");

	private String code;
	private String name;

	private EnPlaceKubun(String code, String name){
		this.code = code;
		this.name = name;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

}
